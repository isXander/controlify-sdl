package dev.isxander.sdl;

import java.nio.file.Path;

public interface SdlLoader {
    String name();

    Sdl create();

    default Sdl create(Path nativeLibrary) {
        throw new UnsupportedOperationException(
                "SDL loader '" + name() + "' does not support an explicit native library");
    }
}
