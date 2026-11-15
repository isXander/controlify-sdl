package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.CleanupPropertyCallback;
import dev.isxander.sdl.SdlCallbacks.EnumeratePropertiesCallback;

/// SDL property type
///
/// @since This enum is available since SDL 3.2.0.
public interface SdlProperties {
    /// A string identifying the
    /// container for this joystick, if available.
    String SDL_PROP_JOYSTICK_CONTAINER_ID_STRING = "SDL.joystick.container_id";

    /// True if this joystick has an
    /// LED that has adjustable brightness
    String SDL_PROP_JOYSTICK_CAP_MONO_LED_BOOLEAN = "SDL.joystick.cap.mono_led";

    /// True if this joystick has an LED
    /// that has adjustable color
    String SDL_PROP_JOYSTICK_CAP_RGB_LED_BOOLEAN = "SDL.joystick.cap.rgb_led";

    /// True if this joystick has a
    /// player LED
    String SDL_PROP_JOYSTICK_CAP_PLAYER_LED_BOOLEAN = "SDL.joystick.cap.player_led";

    /// True if this joystick has
    /// left/right rumble
    String SDL_PROP_JOYSTICK_CAP_RUMBLE_BOOLEAN = "SDL.joystick.cap.rumble";

    /// True if this joystick has
    /// simple trigger rumble
    String SDL_PROP_JOYSTICK_CAP_TRIGGER_RUMBLE_BOOLEAN = "SDL.joystick.cap.trigger_rumble";

    /// A string identifying the
    /// container for this gamepad, if available. This can be compared with
    /// SDL_PROP_AUDIO_DEVICE_CONTAINER_ID_STRING to find associated audio
    /// devices.
    String SDL_PROP_GAMEPAD_CONTAINER_ID_STRING = SDL_PROP_JOYSTICK_CONTAINER_ID_STRING;

    /// True if this gamepad has an LED
    /// that has adjustable brightness
    String SDL_PROP_GAMEPAD_CAP_MONO_LED_BOOLEAN = SDL_PROP_JOYSTICK_CAP_MONO_LED_BOOLEAN;

    /// True if this gamepad has an LED
    /// that has adjustable color
    String SDL_PROP_GAMEPAD_CAP_RGB_LED_BOOLEAN = SDL_PROP_JOYSTICK_CAP_RGB_LED_BOOLEAN;

    /// True if this gamepad has a
    /// player LED
    String SDL_PROP_GAMEPAD_CAP_PLAYER_LED_BOOLEAN = SDL_PROP_JOYSTICK_CAP_PLAYER_LED_BOOLEAN;

    /// True if this gamepad has
    /// left/right rumble
    String SDL_PROP_GAMEPAD_CAP_RUMBLE_BOOLEAN = SDL_PROP_JOYSTICK_CAP_RUMBLE_BOOLEAN;

    /// True if this gamepad has
    /// simple trigger rumble
    String SDL_PROP_GAMEPAD_CAP_TRIGGER_RUMBLE_BOOLEAN = SDL_PROP_JOYSTICK_CAP_TRIGGER_RUMBLE_BOOLEAN;

    /// A pointer, that can be cast
    /// to a win32 `HANDLE`, that this SDL_IOStream is using to access the
    /// filesystem. If the program isn't running on Windows, or SDL used some
    /// other method to access the filesystem, this property will not be set.
    String SDL_PROP_IOSTREAM_WINDOWS_HANDLE_POINTER = "SDL.iostream.windows.handle";

    /// A pointer, that can be cast to a
    /// stdio `FILE *`, that this SDL_IOStream is using to access the filesystem.
    /// If SDL used some other method to access the filesystem, this property
    /// will not be set. PLEASE NOTE that if SDL is using a different C runtime
    /// than your app, trying to use this pointer will almost certainly result in
    /// a crash! This is mostly a problem on Windows; make sure you build SDL and
    /// your app with the same compiler and settings to avoid it.
    String SDL_PROP_IOSTREAM_STDIO_FILE_POINTER = "SDL.iostream.stdio.file";

    /// A file descriptor that this
    /// SDL_IOStream is using to access the filesystem.
    String SDL_PROP_IOSTREAM_FILE_DESCRIPTOR_NUMBER = "SDL.iostream.file_descriptor";

    /// A pointer, that can be cast
    /// to an Android NDK `AAsset *`, that this SDL_IOStream is using to access
    /// the filesystem. If SDL used some other method to access the filesystem,
    /// this property will not be set.
    String SDL_PROP_IOSTREAM_ANDROID_AASSET_POINTER = "SDL.iostream.android.aasset";

    /// A pointer, that can be
    /// cast to an OpenHarmony/HarmonyOS `RawFile64 *`, that this SDL_IOStream is
    /// using to access the filesystem. If SDL used some other method to access
    /// the filesystem, this property will not be set.
    String SDL_PROP_IOSTREAM_OPENHARMONY_RAWFILE64_POINTER = "SDL.iostream.openharmony.rawfile64";

    /// This will be the `mem` parameter that
    /// was passed to this function.
    String SDL_PROP_IOSTREAM_MEMORY_POINTER = "SDL.iostream.memory.base";

    /// This will be the `size` parameter
    /// that was passed to this function.
    String SDL_PROP_IOSTREAM_MEMORY_SIZE_NUMBER = "SDL.iostream.memory.size";

    /// If this property is set to
    /// a non-NULL value it will be interpreted as a function of SDL_free_func
    /// type and called with the passed `mem` pointer when closing the stream. By
    /// default it is unset, i.e., the memory will not be freed.
    String SDL_PROP_IOSTREAM_MEMORY_FREE_FUNC_POINTER = "SDL.iostream.memory.free";

    /// A pointer to the internal
    /// memory of the stream. This can be set to NULL to transfer ownership of
    /// the memory to the application, which should free the memory with
    /// SDL_free(). If this is done, the next operation on the stream must be
    /// SDL_CloseIO().
    String SDL_PROP_IOSTREAM_DYNAMIC_MEMORY_POINTER = "SDL.iostream.dynamic.memory";

    /// Memory will be allocated in
    /// multiples of this size, defaulting to 1024.
    String SDL_PROP_IOSTREAM_DYNAMIC_CHUNKSIZE_NUMBER = "SDL.iostream.dynamic.chunksize";

    /// A string identifying the
    /// container for this audio device, if available. This can be compared with
    /// SDL_PROP_GAMEPAD_CONTAINER_ID_STRING to find associated controllers.
    String SDL_PROP_AUDIO_DEVICE_CONTAINER_ID_STRING = "SDL.audio.device.container_id";

    /// This identifier can be used to
    /// locate a specific device. In optimal conditions, this identifier will not
    /// change between runs of an app, hardware disconnection, and system
    /// reboots. However, depending on the hardware, operating system, and other
    /// circumstances, a device's identifier may change, so if the app cannot
    /// find a device with a previously queried identifier, the user should be
    /// prompted to choose a new device (possibly the same device, now with a new
    /// identifier). Device identifier strings have no specific format, the
    /// format may change in the future without warning, and are likely different
    /// between different operating systems on the same hardware. If the system
    /// cannot reasonably provide a unique identifier, this property will not be
    /// set. Note that property is useful for finding specific hardware again on
    /// a later run of the app, but often times it's better to just open the
    /// default device (SDL_AUDIO_DEVICE_DEFAULT_PLAYBACK or
    /// SDL_AUDIO_DEVICE_DEFAULT_RECORDING), and let the user set this up
    /// globally on their platform.
    String SDL_PROP_AUDIO_DEVICE_UNIQUE_ID_STRING = "SDL.audio.device.unique_id";

    /// If true (the default), the
    /// stream be automatically cleaned up when the audio subsystem quits. If set
    /// to false, the streams will persist beyond that. This property is ignored
    /// for streams created through SDL_OpenAudioDeviceStream(), and will always
    /// be cleaned up. Streams that are not cleaned up will still be unbound from
    /// devices when the audio subsystem quits. This property was added in SDL
    /// 3.4.0.
    String SDL_PROP_AUDIOSTREAM_AUTO_CLEANUP_BOOLEAN = "SDL.audiostream.auto_cleanup";

    /// An SDL_TextInputType value that
    /// describes text being input, defaults to SDL_TEXTINPUT_TYPE_TEXT.
    String SDL_PROP_TEXTINPUT_TYPE_NUMBER = "SDL.textinput.type";

    /// An SDL_Capitalization value
    /// that describes how text should be capitalized, defaults to
    /// SDL_CAPITALIZE_SENTENCES for normal text entry, SDL_CAPITALIZE_WORDS for
    /// SDL_TEXTINPUT_TYPE_TEXT_NAME, and SDL_CAPITALIZE_NONE for e-mail
    /// addresses, usernames, and passwords.
    String SDL_PROP_TEXTINPUT_CAPITALIZATION_NUMBER = "SDL.textinput.capitalization";

    /// True to enable auto completion
    /// and auto correction, defaults to true.
    String SDL_PROP_TEXTINPUT_AUTOCORRECT_BOOLEAN = "SDL.textinput.autocorrect";

    /// True if multiple lines of text
    /// are allowed. This defaults to true if SDL_HINT_RETURN_KEY_HIDES_IME is
    /// "0" or is not set, and defaults to false if SDL_HINT_RETURN_KEY_HIDES_IME
    /// is "1".
    String SDL_PROP_TEXTINPUT_MULTILINE_BOOLEAN = "SDL.textinput.multiline";

    /// A title for the top of the on-screen
    /// keyboard window, if it has one.
    String SDL_PROP_TEXTINPUT_TITLE_STRING = "SDL.textinput.title";

    /// The placeholder shown before
    /// the user starts typing, when the field is empty.
    String SDL_PROP_TEXTINPUT_PLACEHOLDER_STRING = "SDL.textinput.placeholder";

    /// Text to prefill the text field
    /// with.
    String SDL_PROP_TEXTINPUT_DEFAULT_TEXT_STRING = "SDL.textinput.default_text";

    /// Maximum length for the text
    /// field, in characters (not bytes).
    String SDL_PROP_TEXTINPUT_MAX_LENGTH_NUMBER = "SDL.textinput.max_length";

    /// The text input type to
    /// use, overriding other properties. This is documented at
    /// https://developer.android.com/reference/android/text/InputType
    String SDL_PROP_TEXTINPUT_ANDROID_INPUTTYPE_NUMBER = "SDL.textinput.android.inputtype";

    /// The text input type
    /// to use, overriding other properties. This is documented at
    /// https://developer.android.com/reference/android/text/InputType
    String SDL_PROP_TEXTINPUT_OPENHARMONY_INPUTTYPE_NUMBER = "SDL.textinput.openharmony.inputtype";

    /// The human-readable name of the
    /// application, like "My Game 2: Bad Guy's Revenge!". This will show up
    /// anywhere the OS shows the name of the application separately from window
    /// titles, such as volume control applets, etc. If not provided by the app,
    /// SDL will attempt to pick a reasonable default (the app's binary's name if
    /// the platform can provide it, or "SDL Application" if all else fails).
    String SDL_PROP_APP_METADATA_NAME_STRING = "SDL.app.metadata.name";

    /// The version of the app that is
    /// running; there are no rules on format, so "1.0.3beta2" and "April 22nd,
    /// 2024" and a git hash are all valid options. This has no default.
    String SDL_PROP_APP_METADATA_VERSION_STRING = "SDL.app.metadata.version";

    /// A unique string that
    /// identifies this app. This must be in reverse-domain format, like
    /// "com.example.mygame2". This string is used by desktop compositors to
    /// identify and group windows together, as well as match applications with
    /// associated desktop settings and icons. If you plan to package your
    /// application in a container such as Flatpak, the app ID should match the
    /// name of your Flatpak container as well. This has no default.
    String SDL_PROP_APP_METADATA_IDENTIFIER_STRING = "SDL.app.metadata.identifier";

    /// The human-readable name of the
    /// creator/developer/maker of this app, like "MojoWorkshop, LLC"
    String SDL_PROP_APP_METADATA_CREATOR_STRING = "SDL.app.metadata.creator";

    /// The human-readable copyright
    /// notice, like "Copyright (c) 2024 MojoWorkshop, LLC" or whatnot. Keep this
    /// to one line, don't paste a copy of a whole software license in here. This
    /// has no default.
    String SDL_PROP_APP_METADATA_COPYRIGHT_STRING = "SDL.app.metadata.copyright";

    /// A URL to the app on the web. Maybe a
    /// product page, or a storefront, or even a GitHub repository, for user's
    /// further information This has no default.
    String SDL_PROP_APP_METADATA_URL_STRING = "SDL.app.metadata.url";

    /// The type of application this is.
    /// Currently this string can be "game" for a video game, "mediaplayer" for a
    /// media player, or generically "application" if nothing else applies.
    /// Future versions of SDL might add new types. This defaults to
    /// "application".
    String SDL_PROP_APP_METADATA_TYPE_STRING = "SDL.app.metadata.type";

    /// The libusb_device_handle
    /// associated with the device, if it was opened using libusb.
    String SDL_PROP_HIDAPI_LIBUSB_DEVICE_HANDLE_POINTER = "SDL.hidapi.libusb.device.handle";

    /// A generic property for naming things.
    ///
    /// This property is intended to be added to any SDL_PropertiesID that needs a
    /// generic name associated with the property set. It is not guaranteed that
    /// any property set will include this key, but it is convenient to have a
    /// standard key that any piece of code could reasonably agree to use.
    ///
    /// For example, the properties associated with an SDL_Texture might have a
    /// name string of "player sprites", or an SDL_AudioStream might have
    /// "background music", etc. This might also be useful for an SDL_IOStream to
    /// list the path to its asset.
    ///
    /// There is no format for the value set with this key; it is expected to be
    /// human-readable and informational in nature, possibly for logging or
    /// debugging purposes.
    ///
    /// SDL does not currently set this property on any objects it creates, but
    /// this may change in later versions; it is currently expected that apps and
    /// external libraries will take advantage of it, when appropriate.
    ///
    /// @since This macro is available since SDL 3.4.0.
    String SDL_PROP_NAME_STRING = "SDL.name";

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

    /// Get the current number of items in a group of properties.
    ///
    /// For an invalid SDL_PropertiesID, this returns zero and does not set an
    /// error message.
    ///
    /// @param props the properties to query.
    /// @return the number of property items available.
    ///
    /// Thread safety: It is safe to call this function from any thread.
    ///
    /// @since This function is available since SDL 3.6.0.
    int SDL_GetNumProperties(SdlPropertiesId props);

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
