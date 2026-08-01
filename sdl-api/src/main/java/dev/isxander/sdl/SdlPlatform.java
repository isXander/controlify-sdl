package dev.isxander.sdl;

public interface SdlPlatform {
    /// Get the name of the platform.
    ///
    /// @return the name of the platform. If the correct platform name is not
    ///         available, returns a string beginning with the text "Unknown".
    ///
    /// @since This function is available since SDL 3.2.0.
    String SDL_GetPlatform();
}
