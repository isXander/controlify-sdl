package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.IoCloseCallback;
import dev.isxander.sdl.SdlCallbacks.IoFlushCallback;
import dev.isxander.sdl.SdlCallbacks.IoReadCallback;
import dev.isxander.sdl.SdlCallbacks.IoSeekCallback;
import dev.isxander.sdl.SdlCallbacks.IoSizeCallback;
import dev.isxander.sdl.SdlCallbacks.IoWriteCallback;

/**
 * Callbacks used to create a custom SDL IO stream.
 */
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
