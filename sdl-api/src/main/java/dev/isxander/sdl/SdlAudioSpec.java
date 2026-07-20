package dev.isxander.sdl;

/**
 * Java value for {@code SDL_AudioSpec}.
 */
public record SdlAudioSpec(int frequency, int format, int channels) {
}
