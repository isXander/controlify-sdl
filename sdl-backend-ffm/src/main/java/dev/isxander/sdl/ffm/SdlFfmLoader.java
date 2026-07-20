package dev.isxander.sdl.ffm;

import dev.isxander.sdl.Sdl;
import dev.isxander.sdl.SdlLoader;

import java.nio.file.Path;

public final class SdlFfmLoader implements SdlLoader {

    @Override
    public String name() {
        return "ffm";
    }

    @Override
    public Sdl create() {
        SdlNativeLibrary.configure();
        return new SdlFfm();
    }

    @Override
    public Sdl create(Path nativeLibrary) {
        SdlNativeLibrary.configure(nativeLibrary);
        return new SdlFfm();
    }
}
