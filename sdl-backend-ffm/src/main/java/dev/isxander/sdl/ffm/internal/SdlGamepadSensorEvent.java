// FFM binding source for SDL 3.4.12.

package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.Arena;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.SequenceLayout;
import java.lang.foreign.ValueLayout.OfInt;
import java.lang.foreign.ValueLayout.OfLong;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;

import static java.lang.foreign.ValueLayout.JAVA_FLOAT;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
import static java.lang.foreign.MemoryLayout.PathElement.groupElement;
import static java.lang.foreign.MemoryLayout.PathElement.sequenceElement;

/**
 * {@snippet lang=c :
 * struct SdlGamepadSensorEvent {
 *     SDL_EventType type;
 *     Uint32 reserved;
 *     Uint64 timestamp;
 *     SDL_JoystickID which;
 *     Sint32 sensor;
 *     float data[3];
 *     Uint64 sensor_timestamp;
 * }
 * }
 */
public class SdlGamepadSensorEvent {

    SdlGamepadSensorEvent() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        JAVA_INT.withName("type"),
        JAVA_INT.withName("reserved"),
        JAVA_LONG.withName("timestamp"),
        JAVA_INT.withName("which"),
        JAVA_INT.withName("sensor"),
        MemoryLayout.sequenceLayout(3, JAVA_FLOAT).withName("data"),
        MemoryLayout.paddingLayout(4),
        JAVA_LONG.withName("sensor_timestamp")
    ).withName("SdlGamepadSensorEvent");

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

    private static final OfInt sensor$LAYOUT = (OfInt)$LAYOUT.select(groupElement("sensor"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Sint32 sensor
     * }
     */
    public static final OfInt sensor$layout() {
        return sensor$LAYOUT;
    }

    private static final long sensor$OFFSET = $LAYOUT.byteOffset(groupElement("sensor"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Sint32 sensor
     * }
     */
    public static final long sensor$offset() {
        return sensor$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Sint32 sensor
     * }
     */
    public static int sensor(MemorySegment struct) {
        return struct.get(sensor$LAYOUT, sensor$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Sint32 sensor
     * }
     */
    public static void sensor(MemorySegment struct, int fieldValue) {
        struct.set(sensor$LAYOUT, sensor$OFFSET, fieldValue);
    }

    private static final SequenceLayout data$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("data"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float data[3]
     * }
     */
    public static final SequenceLayout data$layout() {
        return data$LAYOUT;
    }

    private static final long data$OFFSET = $LAYOUT.byteOffset(groupElement("data"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float data[3]
     * }
     */
    public static final long data$offset() {
        return data$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float data[3]
     * }
     */
    public static MemorySegment data(MemorySegment struct) {
        return struct.asSlice(data$OFFSET, data$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float data[3]
     * }
     */
    public static void data(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, data$OFFSET, data$LAYOUT.byteSize());
    }

    private static long[] data$DIMS = { 3 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * float data[3]
     * }
     */
    public static long[] data$dimensions() {
        return data$DIMS;
    }
    private static final VarHandle data$ELEM_HANDLE = data$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * float data[3]
     * }
     */
    public static float data(MemorySegment struct, long index0) {
        return (float) data$ELEM_HANDLE.get(struct, data$OFFSET, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * float data[3]
     * }
     */
    public static void data(MemorySegment struct, long index0, float fieldValue) {
        data$ELEM_HANDLE.set(struct, data$OFFSET, index0, fieldValue);
    }

    private static final OfLong sensor_timestamp$LAYOUT = (OfLong)$LAYOUT.select(groupElement("sensor_timestamp"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint64 sensor_timestamp
     * }
     */
    public static final OfLong sensor_timestamp$layout() {
        return sensor_timestamp$LAYOUT;
    }

    private static final long sensor_timestamp$OFFSET = $LAYOUT.byteOffset(groupElement("sensor_timestamp"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint64 sensor_timestamp
     * }
     */
    public static final long sensor_timestamp$offset() {
        return sensor_timestamp$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint64 sensor_timestamp
     * }
     */
    public static long sensor_timestamp(MemorySegment struct) {
        return struct.get(sensor_timestamp$LAYOUT, sensor_timestamp$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint64 sensor_timestamp
     * }
     */
    public static void sensor_timestamp(MemorySegment struct, long fieldValue) {
        struct.set(sensor_timestamp$LAYOUT, sensor_timestamp$OFFSET, fieldValue);
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

