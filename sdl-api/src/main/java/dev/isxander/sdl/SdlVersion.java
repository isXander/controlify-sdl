package dev.isxander.sdl;

public interface SdlVersion {
    /// The current major version of SDL headers.
    ///
    /// If this were SDL version 3.2.1, this value would be 3.
    ///
    /// @since This macro is available since SDL 3.2.0.
    int SDL_MAJOR_VERSION = 3;

    /// The current minor version of the SDL headers.
    ///
    /// If this were SDL version 3.2.1, this value would be 2.
    ///
    /// @since This macro is available since SDL 3.2.0.
    int SDL_MINOR_VERSION = 4;

    /// The current micro (or patchlevel) version of the SDL headers.
    ///
    /// If this were SDL version 3.2.1, this value would be 1.
    ///
    /// @since This macro is available since SDL 3.2.0.
    int SDL_MICRO_VERSION = 12;

    /// Get the version of SDL that is linked against your program.
    ///
    /// @return the version of the linked library.
    ///
    /// @since This function is available since SDL 3.2.0.
    int SDL_GetVersion();

    default SdlVersionNumber SDL_GetJavaBindingsVersion() {
        return new SdlVersionNumber(SDL_MAJOR_VERSION, SDL_MINOR_VERSION, SDL_MICRO_VERSION);
    }

    record SdlVersionNumber(int major, int minor, int micro) {
        public static SdlVersionNumber fromPacked(int packed) {
            return new SdlVersionNumber(packed / 1_000_000, (packed / 1_000) % 1_000, packed % 1_000);
        }
    }
}
