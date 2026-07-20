package dev.isxander.sdl;


public interface SdlKeyboard {
    boolean SDL_HasKeyboard();

    boolean SDL_StartTextInput(SdlWindowHandle window);

    boolean SDL_TextInputActive(SdlWindowHandle window);

    boolean SDL_StopTextInput(SdlWindowHandle window);

    boolean SDL_ClearComposition(SdlWindowHandle window);

    boolean SDL_HasScreenKeyboardSupport();
}
