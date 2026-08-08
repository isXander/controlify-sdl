package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlAudioSpec;
import dev.isxander.sdl.SdlAudioStreamHandle;
import dev.isxander.sdl.SdlEvent;
import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlPropertiesId;
import dev.isxander.sdl.SdlCallbacks.AudioPostmixCallback;
import dev.isxander.sdl.SdlCallbacks.AudioStreamCallback;
import dev.isxander.sdl.SdlCallbacks.CleanupPropertyCallback;
import dev.isxander.sdl.SdlCallbacks.EnumeratePropertiesCallback;
import dev.isxander.sdl.SdlCallbacks.EventFilter;
import dev.isxander.sdl.SdlCallbacks.IoCloseCallback;
import dev.isxander.sdl.SdlCallbacks.IoFlushCallback;
import dev.isxander.sdl.SdlCallbacks.IoReadCallback;
import dev.isxander.sdl.SdlCallbacks.IoSeekCallback;
import dev.isxander.sdl.SdlCallbacks.IoSizeCallback;
import dev.isxander.sdl.SdlCallbacks.IoWriteCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickCleanupCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickRumbleCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickRumbleTriggersCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSendEffectCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSetLedCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSetPlayerIndexCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSetSensorsEnabledCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickUpdateCallback;
import dev.isxander.sdl.ffm.internal.SdlAudioSpecLayout;
import dev.isxander.sdl.ffm.internal.SdlIoStreamInterfaceLayout;
import dev.isxander.sdl.ffm.internal.SdlVirtualJoystickDescLayout;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.IdentityHashMap;
import java.util.Map;

final class SdlCallbackRegistry {
    private final Arena arena = Arena.ofShared();
    private final Map<Object, MemorySegment> callbacks = new IdentityHashMap<>();

    synchronized MemorySegment address(Object callback) {
        if (callback == null) return MemorySegment.NULL;
        return callbacks.computeIfAbsent(callback, this::allocate);
    }

    private MemorySegment allocate(Object callback) {
        if (callback instanceof EventFilter filter) {
            return dev.isxander.sdl.ffm.internal.SdlEventFilter.allocate((userdata, event) -> {
                SdlEvent value = new SdlEvent();
                SdlEventCodec.read(event, value);
                return filter.filter(pointer(userdata), value);
            }, arena);
        }
        if (callback instanceof CleanupPropertyCallback cleanup) {
            return dev.isxander.sdl.ffm.internal.SdlCleanupPropertyCallback.allocate((userdata, value) ->
                    cleanup.cleanup(pointer(userdata), pointer(value)), arena);
        }
        if (callback instanceof EnumeratePropertiesCallback enumerate) {
            return dev.isxander.sdl.ffm.internal.SdlEnumeratePropertiesCallback.allocate((userdata, props, name) ->
                    enumerate.accept(pointer(userdata), new SdlPropertiesId(props), cString(name)), arena);
        }
        if (callback instanceof AudioStreamCallback audio) {
            return dev.isxander.sdl.ffm.internal.SdlAudioStreamCallback.allocate((userdata, stream, additional, total) ->
                    audio.invoke(pointer(userdata), new SdlAudioStreamHandle(stream.address()), additional, total), arena);
        }
        if (callback instanceof AudioPostmixCallback postmix) {
            return dev.isxander.sdl.ffm.internal.SdlAudioPostmixCallback.allocate((userdata, spec, buffer, length) -> {
                MemorySegment value = spec.reinterpret(SdlAudioSpecLayout.sizeof());
                SdlAudioSpec audioSpec = new SdlAudioSpec(SdlAudioSpecLayout.freq(value),
                        SdlAudioSpecLayout.format(value), SdlAudioSpecLayout.channels(value));
                postmix.invoke(pointer(userdata), audioSpec,
                        buffer.reinterpret(Integer.toUnsignedLong(length)).asByteBuffer());
            }, arena);
        }
        if (callback instanceof IoSizeCallback io) {
            return SdlIoStreamInterfaceLayout.Size.allocate(userdata -> io.size(pointer(userdata)), arena);
        }
        if (callback instanceof IoSeekCallback io) {
            return SdlIoStreamInterfaceLayout.Seek.allocate((userdata, offset, whence) ->
                    io.seek(pointer(userdata), offset, whence), arena);
        }
        if (callback instanceof IoReadCallback io) {
            return SdlIoStreamInterfaceLayout.Read.allocate((userdata, destination, size, status) ->
                    io.read(pointer(userdata), destination.reinterpret(size).asByteBuffer(), status.address()), arena);
        }
        if (callback instanceof IoWriteCallback io) {
            return SdlIoStreamInterfaceLayout.Write.allocate((userdata, source, size, status) ->
                    io.write(pointer(userdata), source.reinterpret(size).asByteBuffer(), status.address()), arena);
        }
        if (callback instanceof IoFlushCallback io) {
            return SdlIoStreamInterfaceLayout.Flush.allocate((userdata, status) ->
                    io.flush(pointer(userdata), status.address()), arena);
        }
        if (callback instanceof IoCloseCallback io) {
            return SdlIoStreamInterfaceLayout.Close.allocate(userdata -> io.close(pointer(userdata)), arena);
        }
        if (callback instanceof VirtualJoystickUpdateCallback update) {
            return SdlVirtualJoystickDescLayout.Update.allocate(
                    userdata -> update.update(pointer(userdata)), arena);
        }
        if (callback instanceof VirtualJoystickSetPlayerIndexCallback setPlayerIndex) {
            return SdlVirtualJoystickDescLayout.SetPlayerIndex.allocate(
                    (userdata, playerIndex) -> setPlayerIndex.setPlayerIndex(pointer(userdata), playerIndex), arena);
        }
        if (callback instanceof VirtualJoystickRumbleCallback rumble) {
            return SdlVirtualJoystickDescLayout.Rumble.allocate(
                    (userdata, low, high) -> rumble.rumble(pointer(userdata), low, high), arena);
        }
        if (callback instanceof VirtualJoystickRumbleTriggersCallback rumbleTriggers) {
            return SdlVirtualJoystickDescLayout.RumbleTriggers.allocate(
                    (userdata, left, right) -> rumbleTriggers.rumbleTriggers(pointer(userdata), left, right), arena);
        }
        if (callback instanceof VirtualJoystickSetLedCallback setLed) {
            return SdlVirtualJoystickDescLayout.SetLed.allocate(
                    (userdata, red, green, blue) -> setLed.setLed(pointer(userdata), red, green, blue), arena);
        }
        if (callback instanceof VirtualJoystickSendEffectCallback sendEffect) {
            return SdlVirtualJoystickDescLayout.SendEffect.allocate((userdata, data, size) ->
                    sendEffect.sendEffect(pointer(userdata),
                            data.reinterpret(Integer.toUnsignedLong(size)).asByteBuffer()), arena);
        }
        if (callback instanceof VirtualJoystickSetSensorsEnabledCallback setSensorsEnabled) {
            return SdlVirtualJoystickDescLayout.SetSensorsEnabled.allocate(
                    (userdata, enabled) -> setSensorsEnabled.setSensorsEnabled(pointer(userdata), enabled), arena);
        }
        if (callback instanceof VirtualJoystickCleanupCallback cleanup) {
            return SdlVirtualJoystickDescLayout.Cleanup.allocate(
                    userdata -> cleanup.cleanup(pointer(userdata)), arena);
        }
        throw new UnsupportedOperationException("Unsupported SDL callback type: " + callback.getClass().getName());
    }

    private static SdlPointer pointer(MemorySegment segment) {
        return new SdlPointer(segment.address());
    }

    private static String cString(MemorySegment segment) {
        return segment.address() == 0 ? null : segment.reinterpret(Long.MAX_VALUE).getString(0);
    }
}
