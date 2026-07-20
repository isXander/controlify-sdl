package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlStdinc;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class SdlFfmStdinc implements SdlStdinc {
    private static final MethodHandle SDL_MALLOC_HANDLE = SdlFfmNative.downcall(
            "SDL_malloc",
            FunctionDescriptor.of(
                    SdlLayouts.VOID_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_CALLOC_HANDLE = SdlFfmNative.downcall(
            "SDL_calloc",
            FunctionDescriptor.of(
                    SdlLayouts.VOID_POINTER,
                    JAVA_LONG,
                    JAVA_LONG));
    private static final MethodHandle SDL_REALLOC_HANDLE = SdlFfmNative.downcall(
            "SDL_realloc",
            FunctionDescriptor.of(
                    SdlLayouts.VOID_POINTER,
                    SdlLayouts.VOID_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_FREE_HANDLE = SdlFfmNative.downcall(
            "SDL_free",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_GET_NUM_ALLOCATIONS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumAllocations",
            FunctionDescriptor.of(
                    JAVA_INT));

    @Override
    public int SDL_GetNumAllocations() {
        try {
            return (int) SDL_GET_NUM_ALLOCATIONS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPointer SDL_calloc(long nmemb, long size) {
        try {
            return new SdlPointer(((MemorySegment) SDL_CALLOC_HANDLE.invokeExact(nmemb, size)).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_free(SdlPointer memory) {
        try {
            SDL_FREE_HANDLE.invokeExact(SdlFfmSupport.segment(memory.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPointer SDL_malloc(long size) {
        try {
            return new SdlPointer(((MemorySegment) SDL_MALLOC_HANDLE.invokeExact(size)).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPointer SDL_realloc(SdlPointer memory, long size) {
        try {
            return new SdlPointer(((MemorySegment) SDL_REALLOC_HANDLE.invokeExact(
                    SdlFfmSupport.segment(memory.address()), size)).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static void SDL_free(MemorySegment memory) {
        try {
            SDL_FREE_HANDLE.invokeExact(memory);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
