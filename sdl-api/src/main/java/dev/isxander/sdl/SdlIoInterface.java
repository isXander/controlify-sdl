package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.IoCloseCallback;
import dev.isxander.sdl.SdlCallbacks.IoFlushCallback;
import dev.isxander.sdl.SdlCallbacks.IoReadCallback;
import dev.isxander.sdl.SdlCallbacks.IoSeekCallback;
import dev.isxander.sdl.SdlCallbacks.IoSizeCallback;
import dev.isxander.sdl.SdlCallbacks.IoWriteCallback;

/// The function pointers that drive an SDL_IOStream.
///
/// Applications can provide this struct to SDL_OpenIO() to create their own
/// implementation of SDL_IOStream. This is not necessarily required, as SDL
/// already offers several common types of I/O streams, via functions like
/// SDL_IOFromFile() and SDL_IOFromMem().
///
/// This structure should be initialized using SDL_INIT_INTERFACE()
///
/// @since This struct is available since SDL 3.2.0.
///
/// See `SDL_INIT_INTERFACE`.
public record SdlIoInterface(
        int version,
        IoSizeCallback size,
        IoSeekCallback seek,
        IoReadCallback read,
        IoWriteCallback write,
        IoFlushCallback flush,
        IoCloseCallback close
) {
}
