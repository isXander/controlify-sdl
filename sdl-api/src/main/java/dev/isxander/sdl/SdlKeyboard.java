package dev.isxander.sdl;


public interface SdlKeyboard {
    /// Return whether a keyboard is currently connected.
    ///
    /// @return true if a keyboard is connected, false otherwise.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetKeyboards`.
    boolean SDL_HasKeyboard();

    /// Start accepting Unicode text input events in a window.
    ///
    /// This function will enable text input (SDL_EVENT_TEXT_INPUT and
    /// SDL_EVENT_TEXT_EDITING events) in the specified window. Please use this
    /// function paired with SDL_StopTextInput().
    ///
    /// @param window the window to enable text input.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///          information.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_SetTextInputArea`.
    /// See `SDL_StartTextInputWithProperties`.
    /// See `SDL_StopTextInput`.
    /// See `SDL_TextInputActive`.
    boolean SDL_StartTextInput(SdlWindowHandle window);

    /// Check whether or not Unicode text input events are enabled for a window.
    ///
    /// @param window the window to check.
    /// @return true if text input events are enabled else false.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_StartTextInput`.
    boolean SDL_TextInputActive(SdlWindowHandle window);

    /// Stop receiving any text input events in a window.
    ///
    /// If SDL_StartTextInput() showed the screen keyboard, this function will hide
    /// it.
    ///
    /// @param window the window to disable text input.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///          information.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_StartTextInput`.
    boolean SDL_StopTextInput(SdlWindowHandle window);

    /// Dismiss the composition window/IME without disabling the subsystem.
    ///
    /// @param window the window to affect.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///          information.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_StartTextInput`.
    /// See `SDL_StopTextInput`.
    boolean SDL_ClearComposition(SdlWindowHandle window);

    /// Check whether the platform has screen keyboard support.
    ///
    /// @return true if the platform has some screen keyboard support or false if
    ///          not.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_StartTextInput`.
    /// See `SDL_ScreenKeyboardShown`.
    boolean SDL_HasScreenKeyboardSupport();
}
