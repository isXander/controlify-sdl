package dev.isxander.sdl;

public interface SdlVersion {
    int SDL_MAJOR_VERSION = 3;
    int SDL_MINOR_VERSION = 4;
    int SDL_MICRO_VERSION = 12;

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
