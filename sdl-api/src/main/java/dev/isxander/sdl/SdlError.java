package dev.isxander.sdl;

public interface SdlError {
    String SDL_GetError();

    boolean SDL_ClearError();
}
