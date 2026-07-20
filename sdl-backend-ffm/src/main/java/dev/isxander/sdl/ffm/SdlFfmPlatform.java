package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlPlatform;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

final class SdlFfmPlatform implements SdlPlatform {
    private static final MethodHandle SDL_GET_PLATFORM_HANDLE = SdlFfmNative.downcall(
            "SDL_GetPlatform",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING));

    @Override
    public String SDL_GetPlatform() {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_PLATFORM_HANDLE.invokeExact());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
