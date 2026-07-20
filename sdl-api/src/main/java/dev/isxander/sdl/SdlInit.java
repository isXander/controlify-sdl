package dev.isxander.sdl;

public interface SdlInit {
    int SDL_INIT_TIMER = 0x00000001;
    int SDL_INIT_AUDIO = 0x00000010;
    int SDL_INIT_VIDEO = 0x00000020;
    int SDL_INIT_JOYSTICK = 0x00000200;
    int SDL_INIT_HAPTIC = 0x00001000;
    int SDL_INIT_GAMEPAD = 0x00002000;
    int SDL_INIT_EVENTS = 0x00004000;
    int SDL_INIT_SENSOR = 0x00008000;
    int SDL_INIT_CAMERA = 0x00010000;

    boolean SDL_Init(int flags);

    boolean SDL_InitSubSystem(int flags);

    void SDL_QuitSubSystem(int flags);

    int SDL_WasInit(int flags);

    void SDL_Quit();
}
