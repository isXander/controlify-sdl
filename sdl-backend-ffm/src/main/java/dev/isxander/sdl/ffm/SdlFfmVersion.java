package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlVersion;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;

final class SdlFfmVersion implements SdlVersion {
    private static final MethodHandle SDL_GET_VERSION_HANDLE = SdlFfmNative.downcall(
            "SDL_GetVersion",
            FunctionDescriptor.of(
                    JAVA_INT));

    @Override
    public int SDL_GetVersion() {
        try {
            return (int) SDL_GET_VERSION_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
