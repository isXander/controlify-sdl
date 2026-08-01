package dev.isxander.sdl;

/// Format specifier for audio data.
///
/// @since This struct is available since SDL 3.2.0.
///
/// See `SDL_AudioFormat`.
public record SdlAudioSpec(int frequency, int format, int channels) {
}
