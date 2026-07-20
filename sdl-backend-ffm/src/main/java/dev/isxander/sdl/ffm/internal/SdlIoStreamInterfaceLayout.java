// FFM binding source for SDL 3.4.12.

package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.AddressLayout;
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout.OfInt;
import java.lang.invoke.MethodHandle;
import java.util.function.Consumer;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
import static java.lang.foreign.MemoryLayout.PathElement.groupElement;

/**
 * {@snippet lang=c :
 * struct SdlIoStreamInterfaceLayout {
 *     Uint32 version;
 *     Sint64 (*size)(void *);
 *     Sint64 (*seek)(void *, Sint64, SDL_IOWhence);
 *     size_t (*read)(void *, void *, size_t, SDL_IOStatus *);
 *     size_t (*write)(void *, const void *, size_t, SDL_IOStatus *);
 *     bool (*flush)(void *, SDL_IOStatus *);
 *     bool (*close)(void *);
 * }
 * }
 */
public class SdlIoStreamInterfaceLayout {

    SdlIoStreamInterfaceLayout() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        JAVA_INT.withName("version"),
        MemoryLayout.paddingLayout(4),
        SdlLayouts.SDL_IO_SIZE_CALLBACK.withName("size"),
        SdlLayouts.SDL_IO_SEEK_CALLBACK.withName("seek"),
        SdlLayouts.SDL_IO_READ_CALLBACK.withName("read"),
        SdlLayouts.SDL_IO_WRITE_CALLBACK.withName("write"),
        SdlLayouts.SDL_IO_FLUSH_CALLBACK.withName("flush"),
        SdlLayouts.SDL_IO_CLOSE_CALLBACK.withName("close")
    ).withName("SdlIoStreamInterfaceLayout");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt version$LAYOUT = (OfInt)$LAYOUT.select(groupElement("version"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Uint32 version
     * }
     */
    public static final OfInt version$layout() {
        return version$LAYOUT;
    }

    private static final long version$OFFSET = $LAYOUT.byteOffset(groupElement("version"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Uint32 version
     * }
     */
    public static final long version$offset() {
        return version$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Uint32 version
     * }
     */
    public static int version(MemorySegment struct) {
        return struct.get(version$LAYOUT, version$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Uint32 version
     * }
     */
    public static void version(MemorySegment struct, int fieldValue) {
        struct.set(version$LAYOUT, version$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * Sint64 (*size)(void *)
     * }
     */
    public final static class Size {

        private Size() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            long apply(MemorySegment userdata);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            JAVA_LONG,
            SdlLayouts.VOID_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(Size.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(Size.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static long invoke(MemorySegment funcPtr, MemorySegment userdata) {
            try {
                return (long) DOWN$MH.invokeExact(funcPtr, userdata);
            } catch (Error | RuntimeException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new AssertionError("Unexpected exception from SDL call", throwable);
            }
        }
    }

    private static final AddressLayout size$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("size"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Sint64 (*size)(void *)
     * }
     */
    public static final AddressLayout size$layout() {
        return size$LAYOUT;
    }

    private static final long size$OFFSET = $LAYOUT.byteOffset(groupElement("size"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Sint64 (*size)(void *)
     * }
     */
    public static final long size$offset() {
        return size$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Sint64 (*size)(void *)
     * }
     */
    public static MemorySegment size(MemorySegment struct) {
        return struct.get(size$LAYOUT, size$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Sint64 (*size)(void *)
     * }
     */
    public static void size(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(size$LAYOUT, size$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * Sint64 (*seek)(void *, Sint64, SDL_IOWhence)
     * }
     */
    public final static class Seek {

        private Seek() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            long apply(MemorySegment userdata, long offset, int whence);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            JAVA_LONG,
            SdlLayouts.VOID_POINTER,
            JAVA_LONG,
            JAVA_INT
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(Seek.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(Seek.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static long invoke(MemorySegment funcPtr, MemorySegment userdata, long offset, int whence) {
            try {
                return (long) DOWN$MH.invokeExact(funcPtr, userdata, offset, whence);
            } catch (Error | RuntimeException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new AssertionError("Unexpected exception from SDL call", throwable);
            }
        }
    }

    private static final AddressLayout seek$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("seek"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Sint64 (*seek)(void *, Sint64, SDL_IOWhence)
     * }
     */
    public static final AddressLayout seek$layout() {
        return seek$LAYOUT;
    }

    private static final long seek$OFFSET = $LAYOUT.byteOffset(groupElement("seek"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Sint64 (*seek)(void *, Sint64, SDL_IOWhence)
     * }
     */
    public static final long seek$offset() {
        return seek$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Sint64 (*seek)(void *, Sint64, SDL_IOWhence)
     * }
     */
    public static MemorySegment seek(MemorySegment struct) {
        return struct.get(seek$LAYOUT, seek$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Sint64 (*seek)(void *, Sint64, SDL_IOWhence)
     * }
     */
    public static void seek(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(seek$LAYOUT, seek$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * size_t (*read)(void *, void *, size_t, SDL_IOStatus *)
     * }
     */
    public final static class Read {

        private Read() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            long apply(MemorySegment userdata, MemorySegment destination, long size, MemorySegment status);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            JAVA_LONG,
            SdlLayouts.VOID_POINTER,
            SdlLayouts.VOID_POINTER,
            JAVA_LONG,
            SdlLayouts.SDL_IO_STATUS_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(Read.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(Read.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static long invoke(MemorySegment funcPtr, MemorySegment userdata, MemorySegment destination, long size, MemorySegment status) {
            try {
                return (long) DOWN$MH.invokeExact(funcPtr, userdata, destination, size, status);
            } catch (Error | RuntimeException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new AssertionError("Unexpected exception from SDL call", throwable);
            }
        }
    }

    private static final AddressLayout read$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("read"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * size_t (*read)(void *, void *, size_t, SDL_IOStatus *)
     * }
     */
    public static final AddressLayout read$layout() {
        return read$LAYOUT;
    }

    private static final long read$OFFSET = $LAYOUT.byteOffset(groupElement("read"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * size_t (*read)(void *, void *, size_t, SDL_IOStatus *)
     * }
     */
    public static final long read$offset() {
        return read$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * size_t (*read)(void *, void *, size_t, SDL_IOStatus *)
     * }
     */
    public static MemorySegment read(MemorySegment struct) {
        return struct.get(read$LAYOUT, read$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * size_t (*read)(void *, void *, size_t, SDL_IOStatus *)
     * }
     */
    public static void read(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(read$LAYOUT, read$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * size_t (*write)(void *, const void *, size_t, SDL_IOStatus *)
     * }
     */
    public final static class Write {

        private Write() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            long apply(MemorySegment userdata, MemorySegment source, long size, MemorySegment status);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            JAVA_LONG,
            SdlLayouts.VOID_POINTER,
            SdlLayouts.VOID_POINTER,
            JAVA_LONG,
            SdlLayouts.SDL_IO_STATUS_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(Write.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(Write.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static long invoke(MemorySegment funcPtr, MemorySegment userdata, MemorySegment source, long size, MemorySegment status) {
            try {
                return (long) DOWN$MH.invokeExact(funcPtr, userdata, source, size, status);
            } catch (Error | RuntimeException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new AssertionError("Unexpected exception from SDL call", throwable);
            }
        }
    }

    private static final AddressLayout write$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("write"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * size_t (*write)(void *, const void *, size_t, SDL_IOStatus *)
     * }
     */
    public static final AddressLayout write$layout() {
        return write$LAYOUT;
    }

    private static final long write$OFFSET = $LAYOUT.byteOffset(groupElement("write"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * size_t (*write)(void *, const void *, size_t, SDL_IOStatus *)
     * }
     */
    public static final long write$offset() {
        return write$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * size_t (*write)(void *, const void *, size_t, SDL_IOStatus *)
     * }
     */
    public static MemorySegment write(MemorySegment struct) {
        return struct.get(write$LAYOUT, write$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * size_t (*write)(void *, const void *, size_t, SDL_IOStatus *)
     * }
     */
    public static void write(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(write$LAYOUT, write$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * bool (*flush)(void *, SDL_IOStatus *)
     * }
     */
    public final static class Flush {

        private Flush() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            boolean apply(MemorySegment userdata, MemorySegment status);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            JAVA_BOOLEAN,
            SdlLayouts.VOID_POINTER,
            SdlLayouts.SDL_IO_STATUS_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(Flush.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(Flush.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static boolean invoke(MemorySegment funcPtr, MemorySegment userdata, MemorySegment status) {
            try {
                return (boolean) DOWN$MH.invokeExact(funcPtr, userdata, status);
            } catch (Error | RuntimeException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new AssertionError("Unexpected exception from SDL call", throwable);
            }
        }
    }

    private static final AddressLayout flush$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("flush"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * bool (*flush)(void *, SDL_IOStatus *)
     * }
     */
    public static final AddressLayout flush$layout() {
        return flush$LAYOUT;
    }

    private static final long flush$OFFSET = $LAYOUT.byteOffset(groupElement("flush"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * bool (*flush)(void *, SDL_IOStatus *)
     * }
     */
    public static final long flush$offset() {
        return flush$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * bool (*flush)(void *, SDL_IOStatus *)
     * }
     */
    public static MemorySegment flush(MemorySegment struct) {
        return struct.get(flush$LAYOUT, flush$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * bool (*flush)(void *, SDL_IOStatus *)
     * }
     */
    public static void flush(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(flush$LAYOUT, flush$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * bool (*close)(void *)
     * }
     */
    public final static class Close {

        private Close() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            boolean apply(MemorySegment userdata);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            JAVA_BOOLEAN,
            SdlLayouts.VOID_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(Close.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(Close.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static boolean invoke(MemorySegment funcPtr, MemorySegment userdata) {
            try {
                return (boolean) DOWN$MH.invokeExact(funcPtr, userdata);
            } catch (Error | RuntimeException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new AssertionError("Unexpected exception from SDL call", throwable);
            }
        }
    }

    private static final AddressLayout close$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("close"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * bool (*close)(void *)
     * }
     */
    public static final AddressLayout close$layout() {
        return close$LAYOUT;
    }

    private static final long close$OFFSET = $LAYOUT.byteOffset(groupElement("close"));

    /**
     * Offset for field:
     * {@snippet lang=c :
     * bool (*close)(void *)
     * }
     */
    public static final long close$offset() {
        return close$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * bool (*close)(void *)
     * }
     */
    public static MemorySegment close(MemorySegment struct) {
        return struct.get(close$LAYOUT, close$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * bool (*close)(void *)
     * }
     */
    public static void close(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(close$LAYOUT, close$OFFSET, fieldValue);
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
