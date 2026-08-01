package dev.isxander.sdl;

public interface SdlGuidApi {
    /// Get an ASCII string representation for a given SDL_GUID.
    ///
    /// Thread safety: It is safe to call this function from any thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_StringToGUID`.
    String SDL_GUIDToString(SdlGuid guid);

    /// Convert a GUID string into a SDL_GUID structure.
    ///
    /// Performs no error checking. If this function is given a string containing
    /// an invalid GUID, the function will silently succeed, but the GUID generated
    /// will not be useful.
    ///
    /// @param guid string containing an ASCII representation of a GUID.
    /// @return a SDL_GUID structure.
    ///
    /// Thread safety: It is safe to call this function from any thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GUIDToString`.
    SdlGuid SDL_StringToGUID(String guid);
}
