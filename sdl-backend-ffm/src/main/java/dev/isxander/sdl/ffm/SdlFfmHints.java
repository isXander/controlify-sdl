package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlHints;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_INT;

final class SdlFfmHints implements SdlHints {
    private static final MethodHandle SDL_SET_HINT_WITH_PRIORITY_HANDLE = SdlFfmNative.downcall(
            "SDL_SetHintWithPriority",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_SET_HINT_HANDLE = SdlFfmNative.downcall(
            "SDL_SetHint",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_RESET_HINT_HANDLE = SdlFfmNative.downcall(
            "SDL_ResetHint",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_RESET_HINTS_HANDLE = SdlFfmNative.downcall(
            "SDL_ResetHints",
            FunctionDescriptor.ofVoid());
    private static final MethodHandle SDL_GET_HINT_HANDLE = SdlFfmNative.downcall(
            "SDL_GetHint",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_HINT_BOOLEAN_HANDLE = SdlFfmNative.downcall(
            "SDL_GetHintBoolean",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.UTF8_STRING,
                    JAVA_BOOLEAN));

    @Override
    public String SDL_GetHint(String name) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.string(
                (MemorySegment) SDL_GET_HINT_HANDLE.invokeExact(SdlFfmSupport.utf8(name, arena))
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetHintBoolean(String name, boolean defaultValue) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_GET_HINT_BOOLEAN_HANDLE.invokeExact(
                SdlFfmSupport.utf8(name, arena), defaultValue
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_ResetHint(String name) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_RESET_HINT_HANDLE.invokeExact(SdlFfmSupport.utf8(name, arena));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_ResetHints() {
        try {
            SDL_RESET_HINTS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetHint(String name, String value) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_HINT_HANDLE.invokeExact(
                SdlFfmSupport.utf8(name, arena),
                SdlFfmSupport.utf8(value, arena)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetHintWithPriority(String name, String value, int priority) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_HINT_WITH_PRIORITY_HANDLE.invokeExact(
                SdlFfmSupport.utf8(name, arena),
                SdlFfmSupport.utf8(value, arena),
                priority
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
