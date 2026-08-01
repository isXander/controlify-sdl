package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.CleanupPropertyCallback;
import dev.isxander.sdl.SdlCallbacks.EnumeratePropertiesCallback;

/// SDL property type
///
/// @since This enum is available since SDL 3.2.0.
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

    /// Get the global SDL properties.
    ///
    /// @return a valid property ID on success or 0 on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlPropertiesId SDL_GetGlobalProperties();

    /// Create a group of properties.
    ///
    /// @return an ID for a new group of properties, or 0 on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_DestroyProperties`.
    SdlPropertiesId SDL_CreateProperties();

    /// Copy a group of properties.
    ///
    /// @param src the properties to copy.
    /// @param dst the destination properties.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_CopyProperties(SdlPropertiesId src, SdlPropertiesId dst);

    /// Lock a group of properties.
    ///
    /// @param props the properties to lock.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_UnlockProperties`.
    boolean SDL_LockProperties(SdlPropertiesId props);

    /// Unlock a group of properties.
    ///
    /// @param props the properties to unlock.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_LockProperties`.
    void SDL_UnlockProperties(SdlPropertiesId props);

    /// Set a pointer property with a cleanup function.
    ///
    /// @param props the properties to modify.
    /// @param name the name of the property to modify.
    /// @param value the new value of the property, or NULL to delete the property.
    /// @param cleanup the function to call when this property is deleted, or NULL
    ///                if no cleanup is necessary.
    /// @param userdata a pointer that is passed to the cleanup function.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SetPointerPropertyWithCleanup(SdlPropertiesId props, String name, SdlPointer value,
                                              CleanupPropertyCallback cleanup, SdlPointer userdata);

    /// Set a pointer property in a group of properties.
    ///
    /// @param props the properties to modify.
    /// @param name the name of the property to modify.
    /// @param value the new value of the property, or NULL to delete the property.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SetPointerProperty(SdlPropertiesId props, String name, SdlPointer value);

    /// Set a string property in a group of properties.
    ///
    /// @param props the properties to modify.
    /// @param name the name of the property to modify.
    /// @param value the new value of the property, or NULL to delete the property.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetStringProperty`.
    boolean SDL_SetStringProperty(SdlPropertiesId props, String name, String value);

    /// Set an integer property in a group of properties.
    ///
    /// @param props the properties to modify.
    /// @param name the name of the property to modify.
    /// @param value the new value of the property.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetNumberProperty`.
    boolean SDL_SetNumberProperty(SdlPropertiesId props, String name, long value);

    /// Set a floating point property in a group of properties.
    ///
    /// @param props the properties to modify.
    /// @param name the name of the property to modify.
    /// @param value the new value of the property.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetFloatProperty`.
    boolean SDL_SetFloatProperty(SdlPropertiesId props, String name, float value);

    /// Set a boolean property in a group of properties.
    ///
    /// @param props the properties to modify.
    /// @param name the name of the property to modify.
    /// @param value the new value of the property.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetBooleanProperty`.
    boolean SDL_SetBooleanProperty(SdlPropertiesId props, String name, boolean value);

    /// Return whether a property exists in a group of properties.
    ///
    /// @param props the properties to query.
    /// @param name the name of the property to query.
    /// @return true if the property exists, or false if it doesn't.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetPropertyType`.
    boolean SDL_HasProperty(SdlPropertiesId props, String name);

    /// Get the type of a property in a group of properties.
    ///
    /// @param props the properties to query.
    /// @param name the name of the property to query.
    /// @return the type of the property, or SDL_PROPERTY_TYPE_INVALID if it is
    ///         not set.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_HasProperty`.
    int SDL_GetPropertyType(SdlPropertiesId props, String name);

    /// Get a pointer property from a group of properties.
    ///
    /// @param props the properties to query.
    /// @param name the name of the property to query.
    /// @param defaultValue the default value of the property.
    /// @return the value of the property, or `default_value` if it is not set or
    ///         not a pointer property.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetBooleanProperty`.
    /// See `SDL_GetFloatProperty`.
    /// See `SDL_GetNumberProperty`.
    /// See `SDL_GetPropertyType`.
    /// See `SDL_GetStringProperty`.
    /// See `SDL_HasProperty`.
    /// See `SDL_SetPointerProperty`.
    SdlPointer SDL_GetPointerProperty(SdlPropertiesId props, String name, SdlPointer defaultValue);

    /// Get a string property from a group of properties.
    ///
    /// @param props the properties to query.
    /// @param name the name of the property to query.
    /// @param defaultValue the default value of the property.
    /// @return the value of the property, or `default_value` if it is not set or
    ///         not a string property.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetPropertyType`.
    /// See `SDL_HasProperty`.
    /// See `SDL_SetStringProperty`.
    String SDL_GetStringProperty(SdlPropertiesId props, String name, String defaultValue);

    /// Get a number property from a group of properties.
    ///
    /// @param props the properties to query.
    /// @param name the name of the property to query.
    /// @param defaultValue the default value of the property.
    /// @return the value of the property, or `default_value` if it is not set or
    ///         not a number property.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetPropertyType`.
    /// See `SDL_HasProperty`.
    /// See `SDL_SetNumberProperty`.
    long SDL_GetNumberProperty(SdlPropertiesId props, String name, long defaultValue);

    /// Get a floating point property from a group of properties.
    ///
    /// @param props the properties to query.
    /// @param name the name of the property to query.
    /// @param defaultValue the default value of the property.
    /// @return the value of the property, or `default_value` if it is not set or
    ///         not a float property.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetPropertyType`.
    /// See `SDL_HasProperty`.
    /// See `SDL_SetFloatProperty`.
    float SDL_GetFloatProperty(SdlPropertiesId props, String name, float defaultValue);

    /// Get a boolean property from a group of properties.
    ///
    /// @param props the properties to query.
    /// @param name the name of the property to query.
    /// @param defaultValue the default value of the property.
    /// @return the value of the property, or `default_value` if it is not set or
    ///         not a boolean property.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetPropertyType`.
    /// See `SDL_HasProperty`.
    /// See `SDL_SetBooleanProperty`.
    boolean SDL_GetBooleanProperty(SdlPropertiesId props, String name, boolean defaultValue);

    /// Clear a property from a group of properties.
    ///
    /// @param props the properties to modify.
    /// @param name the name of the property to clear.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_ClearProperty(SdlPropertiesId props, String name);

    /// Enumerate the properties contained in a group of properties.
    ///
    /// @param props the properties to query.
    /// @param callback the function to call for each property.
    /// @param userdata a pointer that is passed to `callback`.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_EnumerateProperties(SdlPropertiesId props, EnumeratePropertiesCallback callback, SdlPointer userdata);

    /// Destroy a group of properties.
    ///
    /// @param props the properties to destroy.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_CreateProperties`.
    void SDL_DestroyProperties(SdlPropertiesId props);
}
