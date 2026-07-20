package dev.isxander.sdl;

/**
 * Backend-neutral entry point to the portion of SDL mapped by controlify-sdl.
 *
 * <p>Native SDL function names deliberately retain SDL's spelling. Java type
 * names use normal camel case.</p>
 */
public interface Sdl {
    SdlInit init();

    SdlError error();

    SdlVersion version();

    SdlPlatform platform();

    SdlHints hints();

    SdlProperties properties();

    SdlEvents events();

    SdlJoystick joystick();

    SdlGamepad gamepad();

    SdlAudio audio();

    SdlHidApi hidApi();

    SdlIoStream ioStream();

    SdlKeyboard keyboard();

    SdlStdinc stdinc();

    SdlGuidApi guid();
}
