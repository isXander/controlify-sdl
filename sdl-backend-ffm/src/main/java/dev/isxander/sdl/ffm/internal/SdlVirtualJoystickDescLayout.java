package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.util.ArrayList;
import java.util.List;

import static java.lang.foreign.MemoryLayout.PathElement.groupElement;
import static java.lang.foreign.ValueLayout.*;

/** FFM layouts and callback signatures for {@code SDL_VirtualJoystickDesc}. */
public final class SdlVirtualJoystickDescLayout {
    private static final GroupLayout LAYOUT = createLayout();

    private SdlVirtualJoystickDescLayout() {
    }

    private static GroupLayout createLayout() {
        List<MemoryLayout> members = new ArrayList<>();
        members.add(JAVA_INT.withName("version"));
        members.add(JAVA_SHORT.withName("type"));
        members.add(JAVA_SHORT.withName("padding"));
        members.add(JAVA_SHORT.withName("vendor_id"));
        members.add(JAVA_SHORT.withName("product_id"));
        members.add(JAVA_SHORT.withName("naxes"));
        members.add(JAVA_SHORT.withName("nbuttons"));
        members.add(JAVA_SHORT.withName("nballs"));
        members.add(JAVA_SHORT.withName("nhats"));
        members.add(JAVA_SHORT.withName("ntouchpads"));
        members.add(JAVA_SHORT.withName("nsensors"));
        members.add(MemoryLayout.sequenceLayout(2, JAVA_SHORT).withName("padding2"));
        members.add(JAVA_INT.withName("button_mask"));
        members.add(JAVA_INT.withName("axis_mask"));
        if (ADDRESS.byteSize() == 8) {
            members.add(MemoryLayout.paddingLayout(4));
        }
        members.add(SdlLayouts.UTF8_STRING.withName("name"));
        members.add(SdlLayouts.SDL_VIRTUAL_JOYSTICK_TOUCHPAD_DESC.withName("touchpads"));
        members.add(SdlLayouts.SDL_VIRTUAL_JOYSTICK_SENSOR_DESC.withName("sensors"));
        members.add(SdlLayouts.VOID_POINTER.withName("userdata"));
        members.add(ADDRESS.withName("Update"));
        members.add(ADDRESS.withName("SetPlayerIndex"));
        members.add(ADDRESS.withName("Rumble"));
        members.add(ADDRESS.withName("RumbleTriggers"));
        members.add(ADDRESS.withName("SetLED"));
        members.add(ADDRESS.withName("SendEffect"));
        members.add(ADDRESS.withName("SetSensorsEnabled"));
        members.add(ADDRESS.withName("Cleanup"));
        return MemoryLayout.structLayout(members.toArray(MemoryLayout[]::new))
                .withName("SDL_VirtualJoystickDesc");
    }

    public static GroupLayout layout() {
        return LAYOUT;
    }

    public static long sizeof() {
        return LAYOUT.byteSize();
    }

    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(LAYOUT);
    }

    public static void version(MemorySegment struct, int value) {
        struct.set(JAVA_INT, offset("version"), value);
    }

    public static void type(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("type"), value);
    }

    public static void vendorId(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("vendor_id"), value);
    }

    public static void productId(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("product_id"), value);
    }

    public static void numAxes(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("naxes"), value);
    }

    public static void numButtons(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("nbuttons"), value);
    }

    public static void numBalls(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("nballs"), value);
    }

    public static void numHats(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("nhats"), value);
    }

    public static void numTouchpads(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("ntouchpads"), value);
    }

    public static void numSensors(MemorySegment struct, short value) {
        struct.set(JAVA_SHORT, offset("nsensors"), value);
    }

    public static void buttonMask(MemorySegment struct, int value) {
        struct.set(JAVA_INT, offset("button_mask"), value);
    }

    public static void axisMask(MemorySegment struct, int value) {
        struct.set(JAVA_INT, offset("axis_mask"), value);
    }

    public static void name(MemorySegment struct, MemorySegment value) {
        address(struct, "name", value);
    }

    public static void touchpads(MemorySegment struct, MemorySegment value) {
        address(struct, "touchpads", value);
    }

    public static void sensors(MemorySegment struct, MemorySegment value) {
        address(struct, "sensors", value);
    }

    public static void userdata(MemorySegment struct, MemorySegment value) {
        address(struct, "userdata", value);
    }

    public static void update(MemorySegment struct, MemorySegment value) {
        address(struct, "Update", value);
    }

    public static void setPlayerIndex(MemorySegment struct, MemorySegment value) {
        address(struct, "SetPlayerIndex", value);
    }

    public static void rumble(MemorySegment struct, MemorySegment value) {
        address(struct, "Rumble", value);
    }

    public static void rumbleTriggers(MemorySegment struct, MemorySegment value) {
        address(struct, "RumbleTriggers", value);
    }

    public static void setLed(MemorySegment struct, MemorySegment value) {
        address(struct, "SetLED", value);
    }

    public static void sendEffect(MemorySegment struct, MemorySegment value) {
        address(struct, "SendEffect", value);
    }

    public static void setSensorsEnabled(MemorySegment struct, MemorySegment value) {
        address(struct, "SetSensorsEnabled", value);
    }

    public static void cleanup(MemorySegment struct, MemorySegment value) {
        address(struct, "Cleanup", value);
    }

    private static long offset(String field) {
        return LAYOUT.byteOffset(groupElement(field));
    }

    private static void address(MemorySegment struct, String field, MemorySegment value) {
        struct.set(ADDRESS, offset(field), value);
    }

    public static final class Touchpad {
        private static final GroupLayout LAYOUT = MemoryLayout.structLayout(
                JAVA_SHORT.withName("nfingers"),
                MemoryLayout.sequenceLayout(3, JAVA_SHORT).withName("padding")
        ).withName("SDL_VirtualJoystickTouchpadDesc");

        private Touchpad() {
        }

        public static MemorySegment allocateArray(long count, SegmentAllocator allocator) {
            return allocator.allocate(MemoryLayout.sequenceLayout(count, LAYOUT));
        }

        public static MemorySegment asSlice(MemorySegment array, long index) {
            return array.asSlice(index * LAYOUT.byteSize(), LAYOUT);
        }

        public static void numFingers(MemorySegment struct, short value) {
            struct.set(JAVA_SHORT, 0, value);
        }
    }

    public static final class Sensor {
        private static final GroupLayout LAYOUT = MemoryLayout.structLayout(
                JAVA_INT.withName("type"),
                JAVA_FLOAT.withName("rate")
        ).withName("SDL_VirtualJoystickSensorDesc");

        private Sensor() {
        }

        public static MemorySegment allocateArray(long count, SegmentAllocator allocator) {
            return allocator.allocate(MemoryLayout.sequenceLayout(count, LAYOUT));
        }

        public static MemorySegment asSlice(MemorySegment array, long index) {
            return array.asSlice(index * LAYOUT.byteSize(), LAYOUT);
        }

        public static void type(MemorySegment struct, int value) {
            struct.set(JAVA_INT, 0, value);
        }

        public static void rate(MemorySegment struct, float value) {
            struct.set(JAVA_FLOAT, JAVA_INT.byteSize(), value);
        }
    }

    public static final class Update {
        public interface Function {
            void invoke(MemorySegment userdata);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(SdlLayouts.VOID_POINTER);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    public static final class SetPlayerIndex {
        public interface Function {
            void invoke(MemorySegment userdata, int playerIndex);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
                SdlLayouts.VOID_POINTER, JAVA_INT);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    public static final class Rumble {
        public interface Function {
            boolean invoke(MemorySegment userdata, short lowFrequency, short highFrequency);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.of(
                JAVA_BOOLEAN, SdlLayouts.VOID_POINTER, JAVA_SHORT, JAVA_SHORT);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    public static final class RumbleTriggers {
        public interface Function {
            boolean invoke(MemorySegment userdata, short left, short right);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.of(
                JAVA_BOOLEAN, SdlLayouts.VOID_POINTER, JAVA_SHORT, JAVA_SHORT);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    public static final class SetLed {
        public interface Function {
            boolean invoke(MemorySegment userdata, byte red, byte green, byte blue);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.of(
                JAVA_BOOLEAN, SdlLayouts.VOID_POINTER, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    public static final class SendEffect {
        public interface Function {
            boolean invoke(MemorySegment userdata, MemorySegment data, int size);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.of(
                JAVA_BOOLEAN, SdlLayouts.VOID_POINTER, SdlLayouts.VOID_POINTER, JAVA_INT);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    public static final class SetSensorsEnabled {
        public interface Function {
            boolean invoke(MemorySegment userdata, boolean enabled);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.of(
                JAVA_BOOLEAN, SdlLayouts.VOID_POINTER, JAVA_BOOLEAN);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    public static final class Cleanup {
        public interface Function {
            void invoke(MemorySegment userdata);
        }

        private static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(SdlLayouts.VOID_POINTER);

        public static MemorySegment allocate(Function function, Arena arena) {
            return upcall(Function.class, "invoke", DESC, function, arena);
        }
    }

    private static MemorySegment upcall(Class<?> type, String method, FunctionDescriptor descriptor,
                                        Object function, Arena arena) {
        return Linker.nativeLinker().upcallStub(
                SdlFfmNative.upcallHandle(type, method, descriptor).bindTo(function), descriptor, arena);
    }
}
