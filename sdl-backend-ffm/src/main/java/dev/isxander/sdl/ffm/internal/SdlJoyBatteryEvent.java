// FFM binding source for SDL 3.4.12.

package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.Arena;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout.OfInt;
import java.lang.foreign.ValueLayout.OfLong;
import java.util.function.Consumer;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
import static java.lang.foreign.MemoryLayout.PathElement.groupElement;

/**
 * {@snippet lang=c :
 * struct SdlJoyBatteryEvent {
 *     SDL_EventType type;
 *     Uint32 reserved;
 *     Uint64 timestamp;
 *     SDL_JoystickID which;
 *     SDL_PowerState state;
 *     int percent;
 * }
 * }
 */
public class SdlJoyBatteryEvent {

    SdlJoyBatteryEvent() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        JAVA_INT.withName("type"),
        JAVA_INT.withName("reserved"),
        JAVA_LONG.withName("timestamp"),
        JAVA_INT.withName("which"),
        JAVA_INT.withName("state"),
        JAVA_INT.withName("percent"),
        MemoryLayout.paddingLayout(4)
    ).withName("SdlJoyBatteryEvent");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt type$LAYOUT = (OfInt)$LAYOUT.select(groupElement("type"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * SDL_EventType type
     * }
     */
    public static final OfInt type$layout() {
        return type$LAYOUT;
    }

    private static final long type$OFFSET = $LAYOUT.byteOffset(groupElement("type"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * SDL_EventType type
     * }
     */
    public static final long type$offset() {
        return type$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * SDL_EventType type
     * }
     */
    public static int type(MemorySegment struct) {
        return struct.get(type$LAYOUT, type$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * SDL_EventType type
     * }
     */
    public static void type(MemorySegment struct, int fieldValue) {
        struct.set(type$LAYOUT, type$OFFSET, fieldValue);
    }

    private static final OfInt reserved$LAYOUT = (OfInt)$LAYOUT.select(groupElement("reserved"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint32 reserved
     * }
     */
    public static final OfInt reserved$layout() {
        return reserved$LAYOUT;
    }

    private static final long reserved$OFFSET = $LAYOUT.byteOffset(groupElement("reserved"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint32 reserved
     * }
     */
    public static final long reserved$offset() {
        return reserved$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint32 reserved
     * }
     */
    public static int reserved(MemorySegment struct) {
        return struct.get(reserved$LAYOUT, reserved$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint32 reserved
     * }
     */
    public static void reserved(MemorySegment struct, int fieldValue) {
        struct.set(reserved$LAYOUT, reserved$OFFSET, fieldValue);
    }

    private static final OfLong timestamp$LAYOUT = (OfLong)$LAYOUT.select(groupElement("timestamp"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint64 timestamp
     * }
     */
    public static final OfLong timestamp$layout() {
        return timestamp$LAYOUT;
    }

    private static final long timestamp$OFFSET = $LAYOUT.byteOffset(groupElement("timestamp"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint64 timestamp
     * }
     */
    public static final long timestamp$offset() {
        return timestamp$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint64 timestamp
     * }
     */
    public static long timestamp(MemorySegment struct) {
        return struct.get(timestamp$LAYOUT, timestamp$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint64 timestamp
     * }
     */
    public static void timestamp(MemorySegment struct, long fieldValue) {
        struct.set(timestamp$LAYOUT, timestamp$OFFSET, fieldValue);
    }

    private static final OfInt which$LAYOUT = (OfInt)$LAYOUT.select(groupElement("which"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * SDL_JoystickID which
     * }
     */
    public static final OfInt which$layout() {
        return which$LAYOUT;
    }

    private static final long which$OFFSET = $LAYOUT.byteOffset(groupElement("which"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * SDL_JoystickID which
     * }
     */
    public static final long which$offset() {
        return which$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * SDL_JoystickID which
     * }
     */
    public static int which(MemorySegment struct) {
        return struct.get(which$LAYOUT, which$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * SDL_JoystickID which
     * }
     */
    public static void which(MemorySegment struct, int fieldValue) {
        struct.set(which$LAYOUT, which$OFFSET, fieldValue);
    }

    private static final OfInt state$LAYOUT = (OfInt)$LAYOUT.select(groupElement("state"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * SDL_PowerState state
     * }
     */
    public static final OfInt state$layout() {
        return state$LAYOUT;
    }

    private static final long state$OFFSET = $LAYOUT.byteOffset(groupElement("state"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * SDL_PowerState state
     * }
     */
    public static final long state$offset() {
        return state$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * SDL_PowerState state
     * }
     */
    public static int state(MemorySegment struct) {
        return struct.get(state$LAYOUT, state$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * SDL_PowerState state
     * }
     */
    public static void state(MemorySegment struct, int fieldValue) {
        struct.set(state$LAYOUT, state$OFFSET, fieldValue);
    }

    private static final OfInt percent$LAYOUT = (OfInt)$LAYOUT.select(groupElement("percent"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int percent
     * }
     */
    public static final OfInt percent$layout() {
        return percent$LAYOUT;
    }

    private static final long percent$OFFSET = $LAYOUT.byteOffset(groupElement("percent"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int percent
     * }
     */
    public static final long percent$offset() {
        return percent$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int percent
     * }
     */
    public static int percent(MemorySegment struct) {
        return struct.get(percent$LAYOUT, percent$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int percent
     * }
     */
    public static void percent(MemorySegment struct, int fieldValue) {
        struct.set(percent$LAYOUT, percent$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

