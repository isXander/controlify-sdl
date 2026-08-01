package dev.isxander.sdl;

public interface SdlInit {
    int SDL_INIT_TIMER = 0x00000001;
    /// `SDL_INIT_AUDIO` implies `SDL_INIT_EVENTS`
    int SDL_INIT_AUDIO = 0x00000010;
    /// `SDL_INIT_VIDEO` implies `SDL_INIT_EVENTS`, should be initialized on the main thread
    int SDL_INIT_VIDEO = 0x00000020;
    /// `SDL_INIT_JOYSTICK` implies `SDL_INIT_EVENTS`
    int SDL_INIT_JOYSTICK = 0x00000200;
    int SDL_INIT_HAPTIC = 0x00001000;
    /// `SDL_INIT_GAMEPAD` implies `SDL_INIT_JOYSTICK`
    int SDL_INIT_GAMEPAD = 0x00002000;
    int SDL_INIT_EVENTS = 0x00004000;
    /// `SDL_INIT_SENSOR` implies `SDL_INIT_EVENTS`
    int SDL_INIT_SENSOR = 0x00008000;
    /// `SDL_INIT_CAMERA` implies `SDL_INIT_EVENTS`
    int SDL_INIT_CAMERA = 0x00010000;

    /// Initialize the SDL library.
    ///
    /// SDL_Init() simply forwards to calling SDL_InitSubSystem(). Therefore, the
    /// two may be used interchangeably. Though for readability of your code
    /// SDL_InitSubSystem() might be preferred.
    ///
    /// @param flags subsystem initialization flags.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///          information.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_SetAppMetadata`.
    /// See `SDL_SetAppMetadataProperty`.
    /// See `SDL_InitSubSystem`.
    /// See `SDL_Quit`.
    /// See `SDL_SetMainReady`.
    /// See `SDL_WasInit`.
    boolean SDL_Init(int flags);

    /// Compatibility function to initialize the SDL library.
    ///
    /// This function and SDL_Init() are interchangeable.
    ///
    /// @param flags any of the flags used by SDL_Init(); see SDL_Init for details.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///          information.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_Init`.
    /// See `SDL_Quit`.
    /// See `SDL_QuitSubSystem`.
    boolean SDL_InitSubSystem(int flags);

    /// Shut down specific SDL subsystems.
    ///
    /// You still need to call SDL_Quit() even if you close all open subsystems
    /// with SDL_QuitSubSystem().
    ///
    /// @param flags any of the flags used by SDL_Init(); see SDL_Init for details.
    ///
    /// Thread safety: This function is not thread safe.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_InitSubSystem`.
    /// See `SDL_Quit`.
    void SDL_QuitSubSystem(int flags);

    /// Get a mask of the specified subsystems which are currently initialized.
    ///
    /// @param flags any of the flags used by SDL_Init(); see SDL_Init for details.
    /// @return a mask of all initialized subsystems if `flags` is 0, otherwise it
    ///          returns the initialization status of the specified subsystems.
    ///
    /// Thread safety: This function is not thread safe.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_Init`.
    /// See `SDL_InitSubSystem`.
    int SDL_WasInit(int flags);

    /// Clean up all initialized subsystems.
    ///
    /// You should call this function even if you have already shutdown each
    /// initialized subsystem with SDL_QuitSubSystem(). It is safe to call this
    /// function even in the case of errors in initialization.
    ///
    /// You can use this function with atexit() to ensure that it is run when your
    /// application is shutdown, but it is not wise to do this from a library or
    /// other dynamically loaded code.
    ///
    /// Thread safety: This function should only be called on the main thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_Init`.
    /// See `SDL_QuitSubSystem`.
    void SDL_Quit();
}
