// FFM binding source for SDL 3.4.12.

package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.Arena;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout.OfByte;
import java.lang.foreign.ValueLayout.OfInt;
import java.lang.foreign.ValueLayout.OfLong;
import java.util.function.Consumer;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
import static java.lang.foreign.MemoryLayout.PathElement.groupElement;

/**
 * {@snippet lang=c :
 * struct SdlJoyHatEvent {
 *     SDL_EventType type;
 *     Uint32 reserved;
 *     Uint64 timestamp;
 *     SDL_JoystickID which;
 *     Uint8 hat;
 *     Uint8 value;
 *     Uint8 padding1;
 *     Uint8 padding2;
 * }
 * }
 */
public class SdlJoyHatEvent {

    SdlJoyHatEvent() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        JAVA_INT.withName("type"),
        JAVA_INT.withName("reserved"),
        JAVA_LONG.withName("timestamp"),
        JAVA_INT.withName("which"),
        JAVA_BYTE.withName("hat"),
        JAVA_BYTE.withName("value"),
        JAVA_BYTE.withName("padding1"),
        JAVA_BYTE.withName("padding2")
    ).withName("SdlJoyHatEvent");

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

    private static final OfByte hat$LAYOUT = (OfByte)$LAYOUT.select(groupElement("hat"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint8 hat
     * }
     */
    public static final OfByte hat$layout() {
        return hat$LAYOUT;
    }

    private static final long hat$OFFSET = $LAYOUT.byteOffset(groupElement("hat"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint8 hat
     * }
     */
    public static final long hat$offset() {
        return hat$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint8 hat
     * }
     */
    public static byte hat(MemorySegment struct) {
        return struct.get(hat$LAYOUT, hat$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint8 hat
     * }
     */
    public static void hat(MemorySegment struct, byte fieldValue) {
        struct.set(hat$LAYOUT, hat$OFFSET, fieldValue);
    }

    private static final OfByte value$LAYOUT = (OfByte)$LAYOUT.select(groupElement("value"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint8 value
     * }
     */
    public static final OfByte value$layout() {
        return value$LAYOUT;
    }

    private static final long value$OFFSET = $LAYOUT.byteOffset(groupElement("value"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint8 value
     * }
     */
    public static final long value$offset() {
        return value$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint8 value
     * }
     */
    public static byte value(MemorySegment struct) {
        return struct.get(value$LAYOUT, value$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint8 value
     * }
     */
    public static void value(MemorySegment struct, byte fieldValue) {
        struct.set(value$LAYOUT, value$OFFSET, fieldValue);
    }

    private static final OfByte padding1$LAYOUT = (OfByte)$LAYOUT.select(groupElement("padding1"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint8 padding1
     * }
     */
    public static final OfByte padding1$layout() {
        return padding1$LAYOUT;
    }

    private static final long padding1$OFFSET = $LAYOUT.byteOffset(groupElement("padding1"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint8 padding1
     * }
     */
    public static final long padding1$offset() {
        return padding1$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint8 padding1
     * }
     */
    public static byte padding1(MemorySegment struct) {
        return struct.get(padding1$LAYOUT, padding1$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint8 padding1
     * }
     */
    public static void padding1(MemorySegment struct, byte fieldValue) {
        struct.set(padding1$LAYOUT, padding1$OFFSET, fieldValue);
    }

    private static final OfByte padding2$LAYOUT = (OfByte)$LAYOUT.select(groupElement("padding2"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint8 padding2
     * }
     */
    public static final OfByte padding2$layout() {
        return padding2$LAYOUT;
    }

    private static final long padding2$OFFSET = $LAYOUT.byteOffset(groupElement("padding2"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint8 padding2
     * }
     */
    public static final long padding2$offset() {
        return padding2$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint8 padding2
     * }
     */
    public static byte padding2(MemorySegment struct) {
        return struct.get(padding2$LAYOUT, padding2$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint8 padding2
     * }
     */
    public static void padding2(MemorySegment struct, byte fieldValue) {
        struct.set(padding2$LAYOUT, padding2$OFFSET, fieldValue);
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

