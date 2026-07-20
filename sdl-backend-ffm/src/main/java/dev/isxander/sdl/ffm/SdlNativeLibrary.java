package dev.isxander.sdl.ffm;

import dev.isxander.sdl.ffm.internal.SdlFfmNative;

import java.lang.foreign.Arena;
import java.lang.foreign.SymbolLookup;
import java.nio.file.Path;

final class SdlNativeLibrary {
    static final String PATH_PROPERTY = "dev.isxander.sdl.library";
    static final String NAME_PROPERTY = "dev.isxander.sdl.libraryName";

    private static String configuredLibrary;

    private SdlNativeLibrary() { }

    static synchronized void configure() {
        String path = System.getProperty(PATH_PROPERTY);
        if (path != null && !path.isBlank()) {
            configure(Path.of(path));
            return;
        }

        String name = System.getProperty(NAME_PROPERTY, "SDL3");
        String description = "library name " + name;
        if (isConfigured(description)) {
            return;
        }
        System.loadLibrary(name);
        bind(description, SymbolLookup.loaderLookup().or(SdlFfmNative.LINKER.defaultLookup()));
    }

    static synchronized void configure(Path path) {
        Path normalizedPath = path.toAbsolutePath().normalize();
        String description = "library " + normalizedPath;
        if (isConfigured(description)) {
            return;
        }
        bind(description, SymbolLookup.libraryLookup(normalizedPath, Arena.global()));
    }

    private static boolean isConfigured(String description) {
        if (configuredLibrary != null) {
            if (!configuredLibrary.equals(description)) {
                throw new IllegalStateException(
                        "SDL FFM is already configured with " + configuredLibrary
                                + " and cannot use " + description);
            }
            return true;
        }
        return false;
    }

    private static void bind(String description, SymbolLookup symbolLookup) {
        SdlFfmNative.configure(symbolLookup, description);
        configuredLibrary = description;
    }
}
