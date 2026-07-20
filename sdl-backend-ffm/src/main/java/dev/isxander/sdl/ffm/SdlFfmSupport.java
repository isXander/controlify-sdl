package dev.isxander.sdl.ffm;

import dev.isxander.sdl.NativeHandle;
import dev.isxander.sdl.SdlAudioDeviceId;
import dev.isxander.sdl.SdlAudioSpec;
import dev.isxander.sdl.SdlEvent;
import dev.isxander.sdl.SdlGamepadBinding;
import dev.isxander.sdl.SdlGamepadHandle;
import dev.isxander.sdl.SdlGuid;
import dev.isxander.sdl.SdlHidDeviceHandle;
import dev.isxander.sdl.SdlHidDeviceInfo;
import dev.isxander.sdl.SdlIoInterface;
import dev.isxander.sdl.SdlIoStreamHandle;
import dev.isxander.sdl.SdlJoystickId;
import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlCallbacks.EventFilter;
import dev.isxander.sdl.SdlEvents.SdlEventFilterRegistration;
import dev.isxander.sdl.SdlGamepadBinding.Axis;
import dev.isxander.sdl.SdlGamepadBinding.BindingValue;
import dev.isxander.sdl.SdlGamepadBinding.Button;
import dev.isxander.sdl.SdlGamepadBinding.Hat;
import dev.isxander.sdl.ffm.internal.SdlAudioSpecLayout;
import dev.isxander.sdl.ffm.internal.SdlEventFilter;
import dev.isxander.sdl.ffm.internal.SdlEventLayout;
import dev.isxander.sdl.ffm.internal.SdlGamepadBindingLayout;
import dev.isxander.sdl.ffm.internal.SdlGuidLayout;
import dev.isxander.sdl.ffm.internal.SdlHidDeviceInfoLayout;
import dev.isxander.sdl.ffm.internal.SdlIoStreamInterfaceLayout;
import dev.isxander.sdl.ffm.internal.SdlLayouts;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class SdlFfmSupport {
    private static final SdlCallbackRegistry CALLBACKS = new SdlCallbackRegistry();

    static MemorySegment utf8(String value, SegmentAllocator allocator) {
        return value == null ? MemorySegment.NULL : allocator.allocateFrom(value);
    }

    static SdlEventFilterRegistration getEventFilter(Arena arena) {
        MemorySegment filter = arena.allocate(SdlLayouts.SDL_EVENT_FILTER);
        MemorySegment userdata = arena.allocate(SdlLayouts.VOID_POINTER);
        if (!SdlFfmEvents.SDL_GetEventFilter(filter, userdata)) {
            return null;
        }

        MemorySegment function = filter.get(SdlLayouts.SDL_EVENT_FILTER, 0L);
        EventFilter callback = (user, event) -> {
            try (Arena callArena = Arena.ofConfined()) {
                return SdlEventFilter.invoke(function, segment(user.address()), SdlEventCodec.allocate(event, callArena));
            }
        };
        return new SdlEventFilterRegistration(callback, new SdlPointer(userdata.get(SdlLayouts.VOID_POINTER, 0L).address()));
    }

    static SdlIoStreamHandle openIo(SdlIoInterface io, SdlPointer userdata, Arena arena) {
        MemorySegment nativeInterface = SdlIoStreamInterfaceLayout.allocate(arena);
        SdlIoStreamInterfaceLayout.version(nativeInterface, io.version());
        SdlIoStreamInterfaceLayout.size(nativeInterface, CALLBACKS.address(io.size()));
        SdlIoStreamInterfaceLayout.seek(nativeInterface, CALLBACKS.address(io.seek()));
        SdlIoStreamInterfaceLayout.read(nativeInterface, CALLBACKS.address(io.read()));
        SdlIoStreamInterfaceLayout.write(nativeInterface, CALLBACKS.address(io.write()));
        SdlIoStreamInterfaceLayout.flush(nativeInterface, CALLBACKS.address(io.flush()));
        SdlIoStreamInterfaceLayout.close(nativeInterface, CALLBACKS.address(io.close()));
        return new SdlIoStreamHandle(SdlFfmIoStream.SDL_OpenIO(nativeInterface, segment(userdata.address())).address());
    }

    static boolean readOneEvent(SdlEvent target, Arena arena, Function<MemorySegment, Boolean> operation) {
        MemorySegment event = SdlEventLayout.allocate(arena);
        boolean result = operation.apply(event);
        if (result) {
            SdlEventCodec.read(event, target);
        }

        return result;
    }

    static boolean readOneEventTimeout(SdlEvent target, int timeout, Arena arena) {
        MemorySegment event = SdlEventLayout.allocate(arena);
        boolean result = SdlFfmEvents.SDL_WaitEventTimeout(event, timeout);
        if (result) {
            SdlEventCodec.read(event, target);
        }

        return result;
    }

    static int peepEvents(SdlEvent[] events, int action, int minType, int maxType, Arena arena) {
        MemorySegment nativeEvents = SdlEventLayout.allocateArray(events.length, arena);

        for (int i = 0; i < events.length; i++) {
            SdlEventCodec.write(events[i], SdlEventLayout.asSlice(nativeEvents, i));
        }

        int count = SdlFfmEvents.SDL_PeepEvents(nativeEvents, events.length, action, minType, maxType);
        if (count > 0) {
            for (int i = 0; i < Math.min(count, events.length); i++) {
                SdlEventCodec.read(SdlEventLayout.asSlice(nativeEvents, i), events[i]);
            }
        }

        return count;
    }

    static SdlJoystickId[] idArray(Arena arena, Function<MemorySegment, MemorySegment> function) {
        MemorySegment count = arena.allocate(ValueLayout.JAVA_INT);
        MemorySegment pointer = function.apply(count);
        int length = count.get(ValueLayout.JAVA_INT, 0L);
        if (pointer.address() != 0L && length > 0) {
            try {
                MemorySegment values = pointer.reinterpret(length * ValueLayout.JAVA_INT.byteSize());
                SdlJoystickId[] result = new SdlJoystickId[length];

                for (int i = 0; i < length; i++) {
                    result[i] = new SdlJoystickId(values.getAtIndex(ValueLayout.JAVA_INT, i));
                }

                return result;
            } finally {
                SdlFfmStdinc.SDL_free(pointer);
            }
        } else {
            return new SdlJoystickId[0];
        }
    }

    static SdlAudioDeviceId[] audioDeviceArray(Arena arena, Function<MemorySegment, MemorySegment> function) {
        MemorySegment count = arena.allocate(ValueLayout.JAVA_INT);
        MemorySegment pointer = function.apply(count);
        int length = count.get(ValueLayout.JAVA_INT, 0L);
        if (pointer.address() != 0L && length > 0) {
            try {
                MemorySegment values = pointer.reinterpret(length * ValueLayout.JAVA_INT.byteSize());
                SdlAudioDeviceId[] result = new SdlAudioDeviceId[length];

                for (int i = 0; i < length; i++) {
                    result[i] = new SdlAudioDeviceId(values.getAtIndex(ValueLayout.JAVA_INT, i));
                }

                return result;
            } finally {
                SdlFfmStdinc.SDL_free(pointer);
            }
        } else {
            return new SdlAudioDeviceId[0];
        }
    }

    static int[] intArray(MemorySegment pointer, int length) {
        if (pointer.address() == 0L) {
            return null;
        }

        try {
            return pointer.reinterpret(length * ValueLayout.JAVA_INT.byteSize()).toArray(ValueLayout.JAVA_INT);
        } finally {
            SdlFfmStdinc.SDL_free(pointer);
        }
    }

    static SdlGamepadBinding[] gamepadBindings(SdlGamepadHandle gamepad, Arena arena) {
        MemorySegment count = arena.allocate(ValueLayout.JAVA_INT);
        MemorySegment pointer = SdlFfmGamepad.SDL_GetGamepadBindings(segment(gamepad.address()), count);
        int length = count.get(ValueLayout.JAVA_INT, 0L);
        if (pointer.address() != 0L && length > 0) {
            try {
                MemorySegment array = pointer.reinterpret(length * SdlGamepadBindingLayout.sizeof());
                SdlGamepadBinding[] result = new SdlGamepadBinding[length];

                for (int i = 0; i < length; i++) {
                    MemorySegment binding = SdlGamepadBindingLayout.asSlice(array, i);
                    int inputType = SdlGamepadBindingLayout.input_type(binding);
                    int outputType = SdlGamepadBindingLayout.output_type(binding);
                    result[i] = new SdlGamepadBinding(
                        inputType,
                        bindingValue(inputType, SdlGamepadBindingLayout.input(binding), true),
                        outputType,
                        bindingValue(outputType, SdlGamepadBindingLayout.output(binding), false)
                    );
                }

                return result;
            } finally {
                SdlFfmStdinc.SDL_free(pointer);
            }
        } else {
            return new SdlGamepadBinding[0];
        }
    }

    static BindingValue bindingValue(int type, MemorySegment value, boolean input) {
        return switch (type) {
            case 1 -> new Button(input ? SdlGamepadBindingLayout.Input.button(value) : SdlGamepadBindingLayout.Output.button(value));
            case 2 -> {
                MemorySegment axis = input ? SdlGamepadBindingLayout.Input.axis(value) : SdlGamepadBindingLayout.Output.axis(value);
                int index = input ? SdlGamepadBindingLayout.Input.Axis.axis(axis) : SdlGamepadBindingLayout.Output.Axis.axis(axis);
                int min = input ? SdlGamepadBindingLayout.Input.Axis.axis_min(axis) : SdlGamepadBindingLayout.Output.Axis.axis_min(axis);
                int max = input ? SdlGamepadBindingLayout.Input.Axis.axis_max(axis) : SdlGamepadBindingLayout.Output.Axis.axis_max(axis);
                yield new Axis(index, min, max);
            }
            case 3 -> {
                MemorySegment hat = SdlGamepadBindingLayout.Input.hat(value);
                yield new Hat(SdlGamepadBindingLayout.Input.Hat.hat(hat), SdlGamepadBindingLayout.Input.Hat.hat_mask(hat));
            }
            default -> new Button(-1);
        };
    }

    static String guidToString(SdlGuid guid, Arena arena) {
        MemorySegment nativeGuid = guid(guid, arena);
        MemorySegment text = arena.allocate(33L);
        SdlFfmGuidApi.SDL_GUIDToString(nativeGuid, text, 33);
        return text.getString(0L);
    }

    static List<SdlHidDeviceInfo> hidEnumerate(int vendorId, int productId, Arena arena) {
        MemorySegment head = SdlFfmHidApi.SDL_hid_enumerate((short) vendorId, (short) productId);
        if (head.address() == 0L) {
            return List.of();
        }

        List<SdlHidDeviceInfo> result = new ArrayList<>();

        try {
            MemorySegment current = head;

            while (current.address() != 0L) {
                MemorySegment info = current.reinterpret(SdlHidDeviceInfoLayout.sizeof());
                result.add(hidInfo(info, arena));
                current = SdlHidDeviceInfoLayout.next(info);
            }
        } finally {
            SdlFfmHidApi.SDL_hid_free_enumeration(head);
        }

        return List.copyOf(result);
    }

    static SdlHidDeviceInfo hidInfo(MemorySegment pointer, Arena arena) {
        if (pointer.address() == 0L) {
            return null;
        }

        MemorySegment info = pointer.byteSize() == 0L ? pointer.reinterpret(SdlHidDeviceInfoLayout.sizeof()) : pointer;
        return new SdlHidDeviceInfo(
            string(SdlHidDeviceInfoLayout.path(info)),
            Short.toUnsignedInt(SdlHidDeviceInfoLayout.vendor_id(info)),
            Short.toUnsignedInt(SdlHidDeviceInfoLayout.product_id(info)),
            wideString(SdlHidDeviceInfoLayout.serial_number(info)),
            Short.toUnsignedInt(SdlHidDeviceInfoLayout.release_number(info)),
            wideString(SdlHidDeviceInfoLayout.manufacturer_string(info)),
            wideString(SdlHidDeviceInfoLayout.product_string(info)),
            Short.toUnsignedInt(SdlHidDeviceInfoLayout.usage_page(info)),
            Short.toUnsignedInt(SdlHidDeviceInfoLayout.usage(info)),
            SdlHidDeviceInfoLayout.interface_number(info),
            SdlHidDeviceInfoLayout.interface_class(info),
            SdlHidDeviceInfoLayout.interface_subclass(info),
            SdlHidDeviceInfoLayout.interface_protocol(info),
            SdlHidDeviceInfoLayout.bus_type(info)
        );
    }

    static String hidManufacturerString(SdlHidDeviceHandle device, int maxLength, Arena arena) {
        return hidString(device, maxLength, arena, SdlFfmHidApi::SDL_hid_get_manufacturer_string);
    }

    static String hidProductString(SdlHidDeviceHandle device, int maxLength, Arena arena) {
        return hidString(device, maxLength, arena, SdlFfmHidApi::SDL_hid_get_product_string);
    }

    static String hidSerialNumberString(SdlHidDeviceHandle device, int maxLength, Arena arena) {
        return hidString(device, maxLength, arena, SdlFfmHidApi::SDL_hid_get_serial_number_string);
    }

    static String hidIndexedString(SdlHidDeviceHandle device, int index, int maxLength, Arena arena) {
        MemorySegment output = arena.allocate((long) maxLength * wcharSize(), wcharSize());
        int status = SdlFfmHidApi.SDL_hid_get_indexed_string(segment(device.address()), index, output, maxLength);
        return status < 0 ? null : wideString(output);
    }

    private static String hidString(SdlHidDeviceHandle device, int maxLength, Arena arena, SdlFfmSupport.HidStringFunction function) {
        MemorySegment output = arena.allocate((long) maxLength * wcharSize(), wcharSize());
        int status = function.invoke(segment(device.address()), output, maxLength);
        return status < 0 ? null : wideString(output);
    }

    static SdlHidDeviceHandle hidOpen(int vendorId, int productId, String serial, Arena arena) {
        MemorySegment wide = serial == null ? MemorySegment.NULL : allocateWideString(serial, arena);
        return new SdlHidDeviceHandle(SdlFfmHidApi.SDL_hid_open((short) vendorId, (short) productId, wide).address());
    }

    static MemorySegment allocateWideString(String text, Arena arena) {
        int size = wcharSize();
        int[] points = text.codePoints().toArray();
        MemorySegment result = arena.allocate((long) (points.length + 1) * size, size);

        for (int i = 0; i < points.length; i++) {
            if (size == 2) {
                result.setAtIndex(ValueLayout.JAVA_CHAR, i, (char)points[i]);
            } else {
                result.setAtIndex(ValueLayout.JAVA_INT, i, points[i]);
            }
        }

        return result;
    }

    static String wideString(MemorySegment pointer) {
        if (pointer.address() == 0L) {
            return null;
        }

        MemorySegment value = pointer.reinterpret(1073741824L);
        StringBuilder result = new StringBuilder();
        long i = 0L;

        while (true) {
            int codePoint = wcharSize() == 2 ? value.getAtIndex(ValueLayout.JAVA_CHAR, i) : value.getAtIndex(ValueLayout.JAVA_INT, i);
            if (codePoint == 0) {
                return result.toString();
            }

            result.appendCodePoint(codePoint);
            i++;
        }
    }

    static int wcharSize() {
        return System.getProperty("os.name", "").startsWith("Windows") ? 2 : 4;
    }

    static MemorySegment audioSpec(SdlAudioSpec spec, Arena arena) {
        MemorySegment value = SdlAudioSpecLayout.allocate(arena);
        writeAudioSpec(value, spec);
        return value;
    }

    static void writeAudioSpec(MemorySegment value, SdlAudioSpec spec) {
        SdlAudioSpecLayout.freq(value, spec.frequency());
        SdlAudioSpecLayout.format(value, spec.format());
        SdlAudioSpecLayout.channels(value, spec.channels());
    }

    static SdlAudioSpec readAudioSpec(MemorySegment value) {
        return new SdlAudioSpec(SdlAudioSpecLayout.freq(value), SdlAudioSpecLayout.format(value), SdlAudioSpecLayout.channels(value));
    }

    static MemorySegment segment(long address) {
        return address == 0L ? MemorySegment.NULL : MemorySegment.ofAddress(address);
    }

    static String string(MemorySegment value) {
        return value.address() == 0L ? null : value.reinterpret(Long.MAX_VALUE).getString(0L);
    }

    static MemorySegment callback(Object callback) {
        return CALLBACKS.address(callback);
    }

    static MemorySegment guid(SdlGuid guid, Arena arena) {
        MemorySegment value = SdlGuidLayout.allocate(arena);
        value.copyFrom(MemorySegment.ofArray(guid.data()));
        return value;
    }

    static SdlGuid guid(MemorySegment value) {
        return new SdlGuid(value.asSlice(0L, 16L).toArray(ValueLayout.JAVA_BYTE));
    }

    static MemorySegment ints(int[] values, Arena arena) {
        MemorySegment array = arena.allocate(ValueLayout.JAVA_INT, values.length);
        MemorySegment.copy(values, 0, array, ValueLayout.JAVA_INT, 0L, values.length);
        return array;
    }

    static MemorySegment handles(NativeHandle[] handles, Arena arena) {
        MemorySegment array = arena.allocate(SdlLayouts.SDL_AUDIO_STREAM, handles.length);

        for (int i = 0; i < handles.length; i++) {
            array.setAtIndex(SdlLayouts.SDL_AUDIO_STREAM, i, segment(handles[i].address()));
        }

        return array;
    }

    @FunctionalInterface
    private interface HidStringFunction {
        int invoke(MemorySegment device, MemorySegment buffer, long maxLength);
    }
}
