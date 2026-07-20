package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlInit;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_INT;

final class SdlFfmInit implements SdlInit {
    private static final MethodHandle SDL_INIT_HANDLE = SdlFfmNative.downcall(
            "SDL_Init",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_INIT_SUB_SYSTEM_HANDLE = SdlFfmNative.downcall(
            "SDL_InitSubSystem",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_QUIT_SUB_SYSTEM_HANDLE = SdlFfmNative.downcall(
            "SDL_QuitSubSystem",
            FunctionDescriptor.ofVoid(
                    JAVA_INT));
    private static final MethodHandle SDL_WAS_INIT_HANDLE = SdlFfmNative.downcall(
            "SDL_WasInit",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_QUIT_HANDLE = SdlFfmNative.downcall(
            "SDL_Quit",
            FunctionDescriptor.ofVoid());

    @Override
    public boolean SDL_Init(int flags) {
        try {
            return (boolean) SDL_INIT_HANDLE.invokeExact(flags);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_InitSubSystem(int flags) {
        try {
            return (boolean) SDL_INIT_SUB_SYSTEM_HANDLE.invokeExact(flags);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_Quit() {
        try {
            SDL_QUIT_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_QuitSubSystem(int flags) {
        try {
            SDL_QUIT_SUB_SYSTEM_HANDLE.invokeExact(flags);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_WasInit(int flags) {
        try {
            return (int) SDL_WAS_INIT_HANDLE.invokeExact(flags);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
