# controlify-sdl

Backend-neutral Java bindings for the controller-oriented subset of SDL 3 that was exposed by
`libsdl4j-controlify`. The API targets SDL 3.4.12 and uses camel-case
Java type names. Native function names intentionally retain SDL's exact spelling.

## Modules

- `api`: native-backend-independent handles, values, callbacks, constants, and the `Sdl` interface.
- `ffm`: Java 25 FFM implementation, discovered with `ServiceLoader`.

The old JNA project and the supplied jextract distribution are local development inputs and are
not runtime dependencies.

## Usage

```java
import dev.isxander.sdl.Sdl;
import dev.isxander.sdl.SdlInit;
import dev.isxander.sdl.SdlLoader;
import java.util.ServiceLoader;

SdlLoader loader = ServiceLoader.load(SdlLoader.class)
        .stream()
        .map(ServiceLoader.Provider::get)
        .filter(candidate -> candidate.name().equals("ffm"))
        .findFirst()
        .orElseThrow();
Sdl sdl = loader.create();
if (!sdl.init().SDL_Init(SdlInit.SDL_INIT_GAMEPAD | SdlInit.SDL_INIT_JOYSTICK)) {
    throw new IllegalStateException(sdl.error().SDL_GetError());
}

try {
    var ids = sdl.gamepad().SDL_GetGamepads();
    // ...
} finally {
    sdl.init().SDL_Quit();
}
```

If SDL has already been extracted or opened by LWJGL, the same native can be
used by the FFM backend without adding an LWJGL dependency to `controlify-sdl`:

```java
import java.nio.file.Path;
import org.lwjgl.sdl.SDL;

String path = SDL.getLibrary().getPath();
Sdl sdl = path == null ? loader.create() : loader.create(Path.of(path));
```

Place SDL 3 on `java.library.path`, or select an exact native library with:

```text
-Ddev.isxander.sdl.library=/absolute/path/to/libSDL3.dylib
```

The library name used by `System.loadLibrary` defaults to `SDL3` and can be changed with
`-Ddev.isxander.sdl.libraryName=...`. Run Java with `--enable-native-access=ALL-UNNAMED` when using
the class path (or grant native access to the consuming named module).

## Building

The low-level FFM binding source was derived once from the supplied SDL headers, then pruned,
renamed, and checked in as regular internal source. Normal builds do not run jextract.
`./gradlew :api:build :ffm:build` compiles the API and FFM backend and runs native smoke tests when
the bundled macOS ARM64 reference library is usable.
