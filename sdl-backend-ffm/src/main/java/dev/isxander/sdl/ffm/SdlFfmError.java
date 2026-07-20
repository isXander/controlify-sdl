package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlError;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;

final class SdlFfmError implements SdlError {
    private static final MethodHandle SDL_GET_ERROR_HANDLE = SdlFfmNative.downcall(
            "SDL_GetError",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_CLEAR_ERROR_HANDLE = SdlFfmNative.downcall(
            "SDL_ClearError",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));

    @Override
    public boolean SDL_ClearError() {
        try {
            return (boolean) SDL_CLEAR_ERROR_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetError() {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_ERROR_HANDLE.invokeExact());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
