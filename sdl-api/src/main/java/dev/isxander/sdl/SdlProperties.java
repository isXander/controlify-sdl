package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.CleanupPropertyCallback;
import dev.isxander.sdl.SdlCallbacks.EnumeratePropertiesCallback;

public interface SdlProperties {
    String SDL_PROP_JOYSTICK_CAP_MONO_LED_BOOLEAN = "SDL.joystick.cap.mono_led";
    String SDL_PROP_JOYSTICK_CAP_RGB_LED_BOOLEAN = "SDL.joystick.cap.rgb_led";
    String SDL_PROP_JOYSTICK_CAP_PLAYER_LED_BOOLEAN = "SDL.joystick.cap.player_led";
    String SDL_PROP_JOYSTICK_CAP_RUMBLE_BOOLEAN = "SDL.joystick.cap.rumble";
    String SDL_PROP_JOYSTICK_CAP_TRIGGER_RUMBLE_BOOLEAN = "SDL.joystick.cap.trigger_rumble";
    String SDL_PROP_GAMEPAD_CAP_MONO_LED_BOOLEAN = SDL_PROP_JOYSTICK_CAP_MONO_LED_BOOLEAN;
    String SDL_PROP_GAMEPAD_CAP_RGB_LED_BOOLEAN = SDL_PROP_JOYSTICK_CAP_RGB_LED_BOOLEAN;
    String SDL_PROP_GAMEPAD_CAP_PLAYER_LED_BOOLEAN = SDL_PROP_JOYSTICK_CAP_PLAYER_LED_BOOLEAN;
    String SDL_PROP_GAMEPAD_CAP_RUMBLE_BOOLEAN = SDL_PROP_JOYSTICK_CAP_RUMBLE_BOOLEAN;
    String SDL_PROP_GAMEPAD_CAP_TRIGGER_RUMBLE_BOOLEAN = SDL_PROP_JOYSTICK_CAP_TRIGGER_RUMBLE_BOOLEAN;
    String SDL_PROP_IOSTREAM_DYNAMIC_MEMORY_POINTER = "SDL.iostream.dynamic.memory";
    String SDL_PROP_IOSTREAM_DYNAMIC_CHUNKSIZE_NUMBER = "SDL.iostream.dynamic.chunksize";
    String SDL_PROP_IOSTREAM_WINDOWS_HANDLE_POINTER = "SDL.iostream.windows.handle";
    String SDL_PROP_IOSTREAM_STDIO_FILE_POINTER = "SDL.iostream.stdio.file";
    String SDL_PROP_IOSTREAM_ANDROID_AASSET_POINTER = "SDL.iostream.android.aasset";

    int SDL_PROPERTY_TYPE_INVALID = 0;
    int SDL_PROPERTY_TYPE_POINTER = 1;
    int SDL_PROPERTY_TYPE_STRING = 2;
    int SDL_PROPERTY_TYPE_NUMBER = 3;
    int SDL_PROPERTY_TYPE_FLOAT = 4;
    int SDL_PROPERTY_TYPE_BOOLEAN = 5;

    SdlPropertiesId SDL_GetGlobalProperties();

    SdlPropertiesId SDL_CreateProperties();

    boolean SDL_CopyProperties(SdlPropertiesId src, SdlPropertiesId dst);

    boolean SDL_LockProperties(SdlPropertiesId props);

    void SDL_UnlockProperties(SdlPropertiesId props);

    boolean SDL_SetPointerPropertyWithCleanup(SdlPropertiesId props, String name, SdlPointer value,
                                              CleanupPropertyCallback cleanup, SdlPointer userdata);

    boolean SDL_SetPointerProperty(SdlPropertiesId props, String name, SdlPointer value);

    boolean SDL_SetStringProperty(SdlPropertiesId props, String name, String value);

    boolean SDL_SetNumberProperty(SdlPropertiesId props, String name, long value);

    boolean SDL_SetFloatProperty(SdlPropertiesId props, String name, float value);

    boolean SDL_SetBooleanProperty(SdlPropertiesId props, String name, boolean value);

    boolean SDL_HasProperty(SdlPropertiesId props, String name);

    int SDL_GetPropertyType(SdlPropertiesId props, String name);

    SdlPointer SDL_GetPointerProperty(SdlPropertiesId props, String name, SdlPointer defaultValue);

    String SDL_GetStringProperty(SdlPropertiesId props, String name, String defaultValue);

    long SDL_GetNumberProperty(SdlPropertiesId props, String name, long defaultValue);

    float SDL_GetFloatProperty(SdlPropertiesId props, String name, float defaultValue);

    boolean SDL_GetBooleanProperty(SdlPropertiesId props, String name, boolean defaultValue);

    boolean SDL_ClearProperty(SdlPropertiesId props, String name);

    boolean SDL_EnumerateProperties(SdlPropertiesId props, EnumeratePropertiesCallback callback, SdlPointer userdata);

    void SDL_DestroyProperties(SdlPropertiesId props);
}
