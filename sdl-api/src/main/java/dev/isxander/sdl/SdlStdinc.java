package dev.isxander.sdl;

import java.nio.ByteOrder;

public interface SdlStdinc {
    int SDL_LIL_ENDIAN = 1234;
    int SDL_BIG_ENDIAN = 4321;
    int SDL_BYTEORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN
            ? SDL_BIG_ENDIAN : SDL_LIL_ENDIAN;

    SdlPointer SDL_malloc(long size);

    SdlPointer SDL_calloc(long nmemb, long size);

    SdlPointer SDL_realloc(SdlPointer memory, long size);

    void SDL_free(SdlPointer memory);

    int SDL_GetNumAllocations();
}
