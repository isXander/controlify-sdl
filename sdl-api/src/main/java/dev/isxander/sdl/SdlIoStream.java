package dev.isxander.sdl;

import java.nio.ByteBuffer;

import dev.isxander.sdl.SdlRefs.LongRef;

public interface SdlIoStream {
    int SDL_IO_STATUS_READY = 0;
    int SDL_IO_STATUS_ERROR = 1;
    int SDL_IO_STATUS_EOF = 2;
    int SDL_IO_STATUS_NOT_READY = 3;
    int SDL_IO_STATUS_READONLY = 4;
    int SDL_IO_STATUS_WRITEONLY = 5;
    int SDL_IO_SEEK_SET = 0;
    int SDL_IO_SEEK_CUR = 1;
    int SDL_IO_SEEK_END = 2;

    SdlIoStreamHandle SDL_IOFromFile(String file, String mode);

    SdlIoStreamHandle SDL_IOFromMem(ByteBuffer memory);

    SdlIoStreamHandle SDL_IOFromConstMem(ByteBuffer memory);

    SdlIoStreamHandle SDL_IOFromDynamicMem();

    SdlIoStreamHandle SDL_OpenIO(SdlIoInterface ioInterface, SdlPointer userdata);

    boolean SDL_CloseIO(SdlIoStreamHandle context);

    SdlPropertiesId SDL_GetIOProperties(SdlIoStreamHandle context);

    int SDL_GetIOStatus(SdlIoStreamHandle context);

    long SDL_GetIOSize(SdlIoStreamHandle context);

    long SDL_SeekIO(SdlIoStreamHandle context, long offset, int whence);

    long SDL_TellIO(SdlIoStreamHandle context);

    long SDL_ReadIO(SdlIoStreamHandle context, ByteBuffer destination);

    long SDL_WriteIO(SdlIoStreamHandle context, ByteBuffer source);

    SdlPointer SDL_LoadFile_IO(SdlIoStreamHandle src, LongRef dataSize, boolean closeio);

    SdlPointer SDL_LoadFile(String file, LongRef dataSize);
}
