package dev.isxander.sdl;

public interface SdlGuidApi {
    String SDL_GUIDToString(SdlGuid guid);

    SdlGuid SDL_StringToGUID(String guid);
}
