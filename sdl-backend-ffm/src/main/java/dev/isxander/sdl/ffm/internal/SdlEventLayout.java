package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_INT;




/**
 * Pruned jextract layout for the controller event variants exposed by the API.
 * SDL guarantees that the event union is 128 bytes.
 */
public final class SdlEventLayout {
    private static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
            SdlCommonEvent.layout().withName("common"),
            SdlJoyDeviceEvent.layout().withName("jdevice"),
            SdlJoyAxisEvent.layout().withName("jaxis"),
            SdlJoyBallEvent.layout().withName("jball"),
            SdlJoyHatEvent.layout().withName("jhat"),
            SdlJoyButtonEvent.layout().withName("jbutton"),
            SdlJoyBatteryEvent.layout().withName("jbattery"),
            SdlGamepadDeviceEvent.layout().withName("gdevice"),
            SdlGamepadAxisEvent.layout().withName("gaxis"),
            SdlGamepadButtonEvent.layout().withName("gbutton"),
            SdlGamepadTouchpadEvent.layout().withName("gtouchpad"),
            SdlGamepadSensorEvent.layout().withName("gsensor"),
            MemoryLayout.sequenceLayout(128, JAVA_BYTE).withName("padding")
    ).withName("SdlEventLayout");

    private SdlEventLayout() { }

    public static MemoryLayout layout() { return LAYOUT; }
    public static long sizeof() { return LAYOUT.byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate(LAYOUT); }
    public static MemorySegment allocateArray(long count, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(count, LAYOUT));
    }
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(index * sizeof(), LAYOUT);
    }

    public static int type(MemorySegment event) { return event.get(JAVA_INT, 0); }
    public static void type(MemorySegment event, int value) { event.set(JAVA_INT, 0, value); }
    public static MemorySegment common(MemorySegment event) { return slice(event, SdlCommonEvent.layout()); }
    public static MemorySegment jdevice(MemorySegment event) { return slice(event, SdlJoyDeviceEvent.layout()); }
    public static MemorySegment jaxis(MemorySegment event) { return slice(event, SdlJoyAxisEvent.layout()); }
    public static MemorySegment jball(MemorySegment event) { return slice(event, SdlJoyBallEvent.layout()); }
    public static MemorySegment jhat(MemorySegment event) { return slice(event, SdlJoyHatEvent.layout()); }
    public static MemorySegment jbutton(MemorySegment event) { return slice(event, SdlJoyButtonEvent.layout()); }
    public static MemorySegment jbattery(MemorySegment event) { return slice(event, SdlJoyBatteryEvent.layout()); }
    public static MemorySegment gdevice(MemorySegment event) { return slice(event, SdlGamepadDeviceEvent.layout()); }
    public static MemorySegment gaxis(MemorySegment event) { return slice(event, SdlGamepadAxisEvent.layout()); }
    public static MemorySegment gbutton(MemorySegment event) { return slice(event, SdlGamepadButtonEvent.layout()); }
    public static MemorySegment gtouchpad(MemorySegment event) { return slice(event, SdlGamepadTouchpadEvent.layout()); }
    public static MemorySegment gsensor(MemorySegment event) { return slice(event, SdlGamepadSensorEvent.layout()); }

    private static MemorySegment slice(MemorySegment event, MemoryLayout layout) {
        return event.asSlice(0, layout);
    }
}
