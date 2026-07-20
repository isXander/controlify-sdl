package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlIoInterface;
import dev.isxander.sdl.SdlIoStream;
import dev.isxander.sdl.SdlIoStreamHandle;
import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlPropertiesId;
import dev.isxander.sdl.SdlRefs.LongRef;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.nio.ByteBuffer;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class SdlFfmIoStream implements SdlIoStream {
    private static final MethodHandle SDL_IO_FROM_FILE_HANDLE = SdlFfmNative.downcall(
            "SDL_IOFromFile",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_IO_STREAM,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_IO_FROM_MEM_HANDLE = SdlFfmNative.downcall(
            "SDL_IOFromMem",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_IO_STREAM,
                    SdlLayouts.VOID_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_IO_FROM_CONST_MEM_HANDLE = SdlFfmNative.downcall(
            "SDL_IOFromConstMem",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_IO_STREAM,
                    SdlLayouts.VOID_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_IO_FROM_DYNAMIC_MEM_HANDLE = SdlFfmNative.downcall(
            "SDL_IOFromDynamicMem",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_IO_STREAM));
    private static final MethodHandle SDL_OPEN_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_OpenIO",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_IO_STREAM,
                    SdlLayouts.SDL_IO_STREAM_INTERFACE,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_CLOSE_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_CloseIO",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_IO_STREAM));
    private static final MethodHandle SDL_GET_IO_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetIOProperties",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_IO_STREAM));
    private static final MethodHandle SDL_GET_IO_STATUS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetIOStatus",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_IO_STREAM));
    private static final MethodHandle SDL_GET_IO_SIZE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetIOSize",
            FunctionDescriptor.of(
                    JAVA_LONG,
                    SdlLayouts.SDL_IO_STREAM));
    private static final MethodHandle SDL_SEEK_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_SeekIO",
            FunctionDescriptor.of(
                    JAVA_LONG,
                    SdlLayouts.SDL_IO_STREAM,
                    JAVA_LONG,
                    JAVA_INT));
    private static final MethodHandle SDL_TELL_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_TellIO",
            FunctionDescriptor.of(
                    JAVA_LONG,
                    SdlLayouts.SDL_IO_STREAM));
    private static final MethodHandle SDL_READ_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_ReadIO",
            FunctionDescriptor.of(
                    JAVA_LONG,
                    SdlLayouts.SDL_IO_STREAM,
                    SdlLayouts.VOID_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_WRITE_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_WriteIO",
            FunctionDescriptor.of(
                    JAVA_LONG,
                    SdlLayouts.SDL_IO_STREAM,
                    SdlLayouts.VOID_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_LOAD_FILE_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_LoadFile_IO",
            FunctionDescriptor.of(
                    SdlLayouts.VOID_POINTER,
                    SdlLayouts.SDL_IO_STREAM,
                    SdlLayouts.SIZE_POINTER,
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_LOAD_FILE_HANDLE = SdlFfmNative.downcall(
            "SDL_LoadFile",
            FunctionDescriptor.of(
                    SdlLayouts.VOID_POINTER,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SIZE_POINTER));

    @Override
    public boolean SDL_CloseIO(SdlIoStreamHandle context) {
        try {
            return (boolean) SDL_CLOSE_IO_HANDLE.invokeExact(SdlFfmSupport.segment(context.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPropertiesId SDL_GetIOProperties(SdlIoStreamHandle context) {
        try {
            return new SdlPropertiesId((int) SDL_GET_IO_PROPERTIES_HANDLE.invokeExact(SdlFfmSupport.segment(context.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_GetIOSize(SdlIoStreamHandle context) {
        try {
            return (long) SDL_GET_IO_SIZE_HANDLE.invokeExact(SdlFfmSupport.segment(context.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetIOStatus(SdlIoStreamHandle context) {
        try {
            return (int) SDL_GET_IO_STATUS_HANDLE.invokeExact(SdlFfmSupport.segment(context.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlIoStreamHandle SDL_IOFromConstMem(ByteBuffer memory) {
        try {
            return new SdlIoStreamHandle(
                    ((MemorySegment) SDL_IO_FROM_CONST_MEM_HANDLE.invokeExact(
                            MemorySegment.ofBuffer(memory), (long) memory.remaining())).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlIoStreamHandle SDL_IOFromDynamicMem() {
        try {
            return new SdlIoStreamHandle(((MemorySegment) SDL_IO_FROM_DYNAMIC_MEM_HANDLE.invokeExact()).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlIoStreamHandle SDL_IOFromFile(String file, String mode) {
        try (Arena arena = Arena.ofConfined()) {
            return new SdlIoStreamHandle(
                ((MemorySegment) SDL_IO_FROM_FILE_HANDLE.invokeExact(
                        SdlFfmSupport.utf8(file, arena),
                        SdlFfmSupport.utf8(mode, arena)
                    )).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlIoStreamHandle SDL_IOFromMem(ByteBuffer memory) {
        try {
            return new SdlIoStreamHandle(
                    ((MemorySegment) SDL_IO_FROM_MEM_HANDLE.invokeExact(
                            MemorySegment.ofBuffer(memory), (long) memory.remaining())).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPointer SDL_LoadFile(String file, LongRef dataSize) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeDataSize = arena.allocate(ValueLayout.JAVA_LONG);
            nativeDataSize.set(ValueLayout.JAVA_LONG, 0L, dataSize.value);
            MemorySegment data = (MemorySegment) SDL_LOAD_FILE_HANDLE.invokeExact(
                SdlFfmSupport.utf8(file, arena), nativeDataSize
            );
            dataSize.value = nativeDataSize.get(ValueLayout.JAVA_LONG, 0L);
            return new SdlPointer(data.address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPointer SDL_LoadFile_IO(SdlIoStreamHandle src, LongRef dataSize, boolean closeio) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeDataSize = arena.allocate(ValueLayout.JAVA_LONG);
            nativeDataSize.set(ValueLayout.JAVA_LONG, 0L, dataSize.value);
            MemorySegment data = (MemorySegment) SDL_LOAD_FILE_IO_HANDLE.invokeExact(
                SdlFfmSupport.segment(src.address()), nativeDataSize, closeio
            );
            dataSize.value = nativeDataSize.get(ValueLayout.JAVA_LONG, 0L);
            return new SdlPointer(data.address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlIoStreamHandle SDL_OpenIO(SdlIoInterface ioInterface, SdlPointer userdata) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.openIo(ioInterface, userdata, arena);
        }
    }

    @Override
    public long SDL_ReadIO(SdlIoStreamHandle context, ByteBuffer destination) {
        try {
            return (long) SDL_READ_IO_HANDLE.invokeExact(
                SdlFfmSupport.segment(context.address()), MemorySegment.ofBuffer(destination), (long) destination.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_SeekIO(SdlIoStreamHandle context, long offset, int whence) {
        try {
            return (long) SDL_SEEK_IO_HANDLE.invokeExact(SdlFfmSupport.segment(context.address()), offset, whence);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_TellIO(SdlIoStreamHandle context) {
        try {
            return (long) SDL_TELL_IO_HANDLE.invokeExact(SdlFfmSupport.segment(context.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_WriteIO(SdlIoStreamHandle context, ByteBuffer source) {
        try {
            return (long) SDL_WRITE_IO_HANDLE.invokeExact(
                SdlFfmSupport.segment(context.address()), MemorySegment.ofBuffer(source), (long) source.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_OpenIO(MemorySegment ioInterface, MemorySegment userdata) {
        try {
            return (MemorySegment) SDL_OPEN_IO_HANDLE.invokeExact(ioInterface, userdata);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
