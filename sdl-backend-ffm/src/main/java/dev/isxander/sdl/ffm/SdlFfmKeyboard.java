package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlKeyboard;
import dev.isxander.sdl.SdlWindowHandle;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;

final class SdlFfmKeyboard implements SdlKeyboard {
    private static final MethodHandle SDL_HAS_KEYBOARD_HANDLE = SdlFfmNative.downcall(
            "SDL_HasKeyboard",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_START_TEXT_INPUT_HANDLE = SdlFfmNative.downcall(
            "SDL_StartTextInput",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_WINDOW));
    private static final MethodHandle SDL_TEXT_INPUT_ACTIVE_HANDLE = SdlFfmNative.downcall(
            "SDL_TextInputActive",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_WINDOW));
    private static final MethodHandle SDL_STOP_TEXT_INPUT_HANDLE = SdlFfmNative.downcall(
            "SDL_StopTextInput",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_WINDOW));
    private static final MethodHandle SDL_CLEAR_COMPOSITION_HANDLE = SdlFfmNative.downcall(
            "SDL_ClearComposition",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_WINDOW));
    private static final MethodHandle SDL_HAS_SCREEN_KEYBOARD_SUPPORT_HANDLE = SdlFfmNative.downcall(
            "SDL_HasScreenKeyboardSupport",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));

    @Override
    public boolean SDL_ClearComposition(SdlWindowHandle window) {
        try {
            return (boolean) SDL_CLEAR_COMPOSITION_HANDLE.invokeExact(SdlFfmSupport.segment(window.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_HasKeyboard() {
        try {
            return (boolean) SDL_HAS_KEYBOARD_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_HasScreenKeyboardSupport() {
        try {
            return (boolean) SDL_HAS_SCREEN_KEYBOARD_SUPPORT_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_StartTextInput(SdlWindowHandle window) {
        try {
            return (boolean) SDL_START_TEXT_INPUT_HANDLE.invokeExact(SdlFfmSupport.segment(window.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_StopTextInput(SdlWindowHandle window) {
        try {
            return (boolean) SDL_STOP_TEXT_INPUT_HANDLE.invokeExact(SdlFfmSupport.segment(window.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_TextInputActive(SdlWindowHandle window) {
        try {
            return (boolean) SDL_TEXT_INPUT_ACTIVE_HANDLE.invokeExact(SdlFfmSupport.segment(window.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
