package dev.isxander.sdl;

public interface SdlError {
    /// Retrieve a message about the last error that occurred on the current
    /// thread.
    ///
    /// It is possible for multiple errors to occur before calling SDL_GetError().
    /// Only the last error is returned.
    ///
    /// The message is only applicable when an SDL function has signaled an error.
    /// You must check the return values of SDL function calls to determine when to
    /// appropriately call SDL_GetError(). You should *not* use the results of
    /// SDL_GetError() to decide if an error has occurred! Sometimes SDL will set
    /// an error string even when reporting success.
    ///
    /// @return a message with information about the specific error that occurred,
    ///          or an empty string if there hasn't been an error message set since
    ///          the last call to SDL_ClearError().
    ///
    /// Thread safety: It is safe to call this function from any thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_ClearError`.
    /// See `SDL_SetError`.
    String SDL_GetError();

    /// Clear any previous error message for this thread.
    ///
    /// @return true.
    ///
    /// Thread safety: It is safe to call this function from any thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetError`.
    /// See `SDL_SetError`.
    boolean SDL_ClearError();
}
