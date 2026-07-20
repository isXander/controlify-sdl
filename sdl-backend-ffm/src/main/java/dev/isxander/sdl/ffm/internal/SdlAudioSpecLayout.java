// FFM binding source for SDL 3.4.12.

package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.Arena;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout.OfInt;
import java.util.function.Consumer;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.MemoryLayout.PathElement.groupElement;

/**
 * {@snippet lang=c :
 * struct SdlAudioSpecLayout {
 *     SDL_AudioFormat format;
 *     int channels;
 *     int freq;
 * }
 * }
 */
public class SdlAudioSpecLayout {

    SdlAudioSpecLayout() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        JAVA_INT.withName("format"),
        JAVA_INT.withName("channels"),
        JAVA_INT.withName("freq")
    ).withName("SdlAudioSpecLayout");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt format$LAYOUT = (OfInt)$LAYOUT.select(groupElement("format"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * SDL_AudioFormat format
     * }
     */
    public static final OfInt format$layout() {
        return format$LAYOUT;
    }

    private static final long format$OFFSET = $LAYOUT.byteOffset(groupElement("format"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * SDL_AudioFormat format
     * }
     */
    public static final long format$offset() {
        return format$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * SDL_AudioFormat format
     * }
     */
    public static int format(MemorySegment struct) {
        return struct.get(format$LAYOUT, format$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * SDL_AudioFormat format
     * }
     */
    public static void format(MemorySegment struct, int fieldValue) {
        struct.set(format$LAYOUT, format$OFFSET, fieldValue);
    }

    private static final OfInt channels$LAYOUT = (OfInt)$LAYOUT.select(groupElement("channels"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int channels
     * }
     */
    public static final OfInt channels$layout() {
        return channels$LAYOUT;
    }

    private static final long channels$OFFSET = $LAYOUT.byteOffset(groupElement("channels"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int channels
     * }
     */
    public static final long channels$offset() {
        return channels$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int channels
     * }
     */
    public static int channels(MemorySegment struct) {
        return struct.get(channels$LAYOUT, channels$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int channels
     * }
     */
    public static void channels(MemorySegment struct, int fieldValue) {
        struct.set(channels$LAYOUT, channels$OFFSET, fieldValue);
    }

    private static final OfInt freq$LAYOUT = (OfInt)$LAYOUT.select(groupElement("freq"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int freq
     * }
     */
    public static final OfInt freq$layout() {
        return freq$LAYOUT;
    }

    private static final long freq$OFFSET = $LAYOUT.byteOffset(groupElement("freq"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int freq
     * }
     */
    public static final long freq$offset() {
        return freq$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int freq
     * }
     */
    public static int freq(MemorySegment struct) {
        return struct.get(freq$LAYOUT, freq$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int freq
     * }
     */
    public static void freq(MemorySegment struct, int fieldValue) {
        struct.set(freq$LAYOUT, freq$OFFSET, fieldValue);
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

