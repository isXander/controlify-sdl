package dev.isxander.sdl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import dev.isxander.sdl.SdlRefs.ByteRef;
import dev.isxander.sdl.SdlRefs.FloatRef;
import dev.isxander.sdl.SdlRefs.IntRef;

/// Standard gamepad types.
///
/// The list of buttons available on a gamepad
///
/// The set of gamepad button labels
///
/// The list of axes available on a gamepad
///
/// Types of gamepad control bindings.
public interface SdlGamepad {
    int SDL_GAMEPAD_TYPE_UNKNOWN = 0;
    int SDL_GAMEPAD_TYPE_STANDARD = 1;
    int SDL_GAMEPAD_TYPE_XBOX360 = 2;
    int SDL_GAMEPAD_TYPE_XBOXONE = 3;
    int SDL_GAMEPAD_TYPE_PS3 = 4;
    int SDL_GAMEPAD_TYPE_PS4 = 5;
    int SDL_GAMEPAD_TYPE_PS5 = 6;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_PRO = 7;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_LEFT = 8;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_RIGHT = 9;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_PAIR = 10;
    int SDL_GAMEPAD_TYPE_GAMECUBE = 11;
    int SDL_GAMEPAD_TYPE_COUNT = 12;

    int SDL_GAMEPAD_AXIS_INVALID = -1;
    int SDL_GAMEPAD_AXIS_LEFTX = 0;
    int SDL_GAMEPAD_AXIS_LEFTY = 1;
    int SDL_GAMEPAD_AXIS_RIGHTX = 2;
    int SDL_GAMEPAD_AXIS_RIGHTY = 3;
    int SDL_GAMEPAD_AXIS_LEFT_TRIGGER = 4;
    int SDL_GAMEPAD_AXIS_RIGHT_TRIGGER = 5;
    int SDL_GAMEPAD_AXIS_COUNT = 6;

    int SDL_GAMEPAD_BUTTON_INVALID = -1;
    /// Bottom face button (e.g. Xbox A button)
    int SDL_GAMEPAD_BUTTON_SOUTH = 0;
    /// Right face button (e.g. Xbox B button)
    int SDL_GAMEPAD_BUTTON_EAST = 1;
    /// Left face button (e.g. Xbox X button)
    int SDL_GAMEPAD_BUTTON_WEST = 2;
    /// Top face button (e.g. Xbox Y button)
    int SDL_GAMEPAD_BUTTON_NORTH = 3;
    int SDL_GAMEPAD_BUTTON_BACK = 4;
    int SDL_GAMEPAD_BUTTON_GUIDE = 5;
    int SDL_GAMEPAD_BUTTON_START = 6;
    int SDL_GAMEPAD_BUTTON_LEFT_STICK = 7;
    int SDL_GAMEPAD_BUTTON_RIGHT_STICK = 8;
    int SDL_GAMEPAD_BUTTON_LEFT_SHOULDER = 9;
    int SDL_GAMEPAD_BUTTON_RIGHT_SHOULDER = 10;
    int SDL_GAMEPAD_BUTTON_DPAD_UP = 11;
    int SDL_GAMEPAD_BUTTON_DPAD_DOWN = 12;
    int SDL_GAMEPAD_BUTTON_DPAD_LEFT = 13;
    int SDL_GAMEPAD_BUTTON_DPAD_RIGHT = 14;
    /// Additional button (e.g. Xbox Series X share button, PS5 microphone button, Nintendo Switch Pro capture button, Steam Controller QAM button, Amazon Luna microphone button, Google Stadia capture button)
    int SDL_GAMEPAD_BUTTON_MISC1 = 15;
    /// Upper or primary paddle, under your right hand (e.g. Xbox Elite paddle P1, DualSense Edge RB button, Right Joy-Con SR button, Steam Controller R4 button)
    int SDL_GAMEPAD_BUTTON_RIGHT_PADDLE1 = 16;
    /// Upper or primary paddle, under your left hand (e.g. Xbox Elite paddle P3, DualSense Edge LB button, Left Joy-Con SL button, Steam Controller L4 button)
    int SDL_GAMEPAD_BUTTON_LEFT_PADDLE1 = 17;
    /// Lower or secondary paddle, under your right hand (e.g. Xbox Elite paddle P2, DualSense Edge right Fn button, Right Joy-Con SL button, Steam Controller R5 button)
    int SDL_GAMEPAD_BUTTON_RIGHT_PADDLE2 = 18;
    /// Lower or secondary paddle, under your left hand (e.g. Xbox Elite paddle P4, DualSense Edge left Fn button, Left Joy-Con SR button, Steam Controller L5 button)
    int SDL_GAMEPAD_BUTTON_LEFT_PADDLE2 = 19;
    /// PS4/PS5 touchpad button
    int SDL_GAMEPAD_BUTTON_TOUCHPAD = 20;
    /// Additional button
    int SDL_GAMEPAD_BUTTON_MISC2 = 21;
    /// Additional button (e.g. Nintendo GameCube left trigger click)
    int SDL_GAMEPAD_BUTTON_MISC3 = 22;
    /// Additional button (e.g. Nintendo GameCube right trigger click)
    int SDL_GAMEPAD_BUTTON_MISC4 = 23;
    /// Additional button
    int SDL_GAMEPAD_BUTTON_MISC5 = 24;
    /// Additional button
    int SDL_GAMEPAD_BUTTON_MISC6 = 25;
    int SDL_GAMEPAD_BUTTON_COUNT = 26;

    int SDL_GAMEPAD_BUTTON_LABEL_UNKNOWN = 0;
    int SDL_GAMEPAD_BUTTON_LABEL_A = 1;
    int SDL_GAMEPAD_BUTTON_LABEL_B = 2;
    int SDL_GAMEPAD_BUTTON_LABEL_X = 3;
    int SDL_GAMEPAD_BUTTON_LABEL_Y = 4;
    int SDL_GAMEPAD_BUTTON_LABEL_CROSS = 5;
    int SDL_GAMEPAD_BUTTON_LABEL_CIRCLE = 6;
    int SDL_GAMEPAD_BUTTON_LABEL_SQUARE = 7;
    int SDL_GAMEPAD_BUTTON_LABEL_TRIANGLE = 8;

    int SDL_GAMEPAD_BINDTYPE_NONE = 0;
    int SDL_GAMEPAD_BINDTYPE_BUTTON = 1;
    int SDL_GAMEPAD_BINDTYPE_AXIS = 2;
    int SDL_GAMEPAD_BINDTYPE_HAT = 3;

    /// Returned for an invalid sensor
    int SDL_SENSOR_INVALID = -1;
    /// Unknown sensor type
    int SDL_SENSOR_UNKNOWN = 0;
    /// Accelerometer
    int SDL_SENSOR_ACCEL = 1;
    /// Gyroscope
    int SDL_SENSOR_GYRO = 2;
    /// Accelerometer for left Joy-Con controller and Wii nunchuk
    int SDL_SENSOR_ACCEL_L = 3;
    /// Gyroscope for left Joy-Con controller
    int SDL_SENSOR_GYRO_L = 4;
    /// Accelerometer for right Joy-Con controller
    int SDL_SENSOR_ACCEL_R = 5;
    /// Gyroscope for right Joy-Con controller
    int SDL_SENSOR_GYRO_R = 6;
    int SDL_SENSOR_COUNT = 7;
    /// A constant to represent standard gravity for accelerometer sensors.
    ///
    /// The accelerometer returns the current acceleration in SI meters per second
    /// squared. This measurement includes the force of gravity, so a device at
    /// rest will have an value of SDL_STANDARD_GRAVITY away from the center of the
    /// earth, which is a positive Y value.
    ///
    /// @since This macro is available since SDL 3.2.0.
    float SDL_STANDARD_GRAVITY = 9.80665f;

    /// error determining power status
    int SDL_POWERSTATE_ERROR = -1;
    /// cannot determine power status
    int SDL_POWERSTATE_UNKNOWN = 0;
    /// Not plugged in, running on the battery
    int SDL_POWERSTATE_ON_BATTERY = 1;
    /// Plugged in, no battery available
    int SDL_POWERSTATE_NO_BATTERY = 2;
    /// Plugged in, charging battery
    int SDL_POWERSTATE_CHARGING = 3;
    /// Plugged in, battery charged
    int SDL_POWERSTATE_CHARGED = 4;

    /// Add support for gamepads that SDL is unaware of or change the binding of an
    /// existing gamepad.
    ///
    /// The mapping string has the format "GUID,name,mapping", where GUID is the
    /// string value from SDL_GUIDToString(), name is the human readable string for
    /// the device and mappings are gamepad mappings to joystick ones. Under
    /// Windows there is a reserved GUID of "xinput" that covers all XInput
    /// devices. The mapping format for joystick is:
    ///
    /// - `bX`: a joystick button, index X
    /// - `hX.Y`: hat X with value Y
    /// - `aX`: axis X of the joystick
    ///
    /// Buttons can be used as a gamepad axes and vice versa.
    ///
    /// If a device with this GUID is already plugged in, SDL will generate an
    /// SDL_EVENT_GAMEPAD_ADDED event.
    ///
    /// This string shows an example of a valid mapping for a gamepad:
    ///
    /// ```c
    /// "341a3608000000000000504944564944,Afterglow PS3 Controller,a:b1,b:b2,y:b3,x:b0,start:b9,guide:b12,back:b8,dpup:h0.1,dpleft:h0.8,dpdown:h0.4,dpright:h0.2,leftshoulder:b4,rightshoulder:b5,leftstick:b10,rightstick:b11,leftx:a0,lefty:a1,rightx:a2,righty:a3,lefttrigger:b6,righttrigger:b7"
    /// ```
    int SDL_AddGamepadMapping(String mapping);

    /// Load a set of gamepad mappings from an SDL_IOStream.
    ///
    /// You can call this function several times, if needed, to load different
    /// database files.
    ///
    /// If a new mapping is loaded for an already known gamepad GUID, the later
    /// version will overwrite the one currently loaded.
    ///
    /// Any new mappings for already plugged in controllers will generate
    /// SDL_EVENT_GAMEPAD_ADDED events.
    ///
    /// Mappings not belonging to the current platform or with no platform field
    /// specified will be ignored (i.e. mappings for Linux will be ignored in
    /// Windows, etc).
    ///
    /// This function will load the text database entirely in memory before
    /// processing it, so take this into consideration if you are in a memory
    /// constrained environment.
    int SDL_AddGamepadMappingsFromIO(SdlIoStreamHandle src, boolean closeio);

    /// Load a set of gamepad mappings from a file.
    ///
    /// You can call this function several times, if needed, to load different
    /// database files.
    ///
    /// If a new mapping is loaded for an already known gamepad GUID, the later
    /// version will overwrite the one currently loaded.
    ///
    /// Any new mappings for already plugged in controllers will generate
    /// SDL_EVENT_GAMEPAD_ADDED events.
    ///
    /// Mappings not belonging to the current platform or with no platform field
    /// specified will be ignored (i.e. mappings for Linux will be ignored in
    /// Windows, etc).
    int SDL_AddGamepadMappingsFromFile(String file);

    /// Reinitialize the SDL mapping database to its initial state.
    ///
    /// This will generate gamepad events as needed if device mappings change.
    boolean SDL_ReloadGamepadMappings();

    /// Get the gamepad mapping string for a given GUID.
    String SDL_GetGamepadMappingForGUID(SdlGuid guid);

    /// Get the current mapping of a gamepad.
    ///
    /// Details about mappings are discussed with SDL_AddGamepadMapping().
    String SDL_GetGamepadMapping(SdlGamepadHandle gamepad);

    /// Set the current mapping of a joystick or gamepad.
    ///
    /// Details about mappings are discussed with SDL_AddGamepadMapping().
    boolean SDL_SetGamepadMapping(SdlJoystickId instanceId, String mapping);

    /// Return whether a gamepad is currently connected.
    boolean SDL_HasGamepad();

    /// Get a list of currently connected gamepads.
    SdlJoystickId[] SDL_GetGamepads();

    /// Check if the given joystick is supported by the gamepad interface.
    boolean SDL_IsGamepad(SdlJoystickId instanceId);

    /// Get the implementation dependent name of a gamepad.
    ///
    /// This can be called before any gamepads are opened.
    String SDL_GetGamepadNameForID(SdlJoystickId instanceId);

    /// Get the implementation dependent path of a gamepad.
    ///
    /// This can be called before any gamepads are opened.
    String SDL_GetGamepadPathForID(SdlJoystickId instanceId);

    /// Get the player index of a gamepad.
    ///
    /// This can be called before any gamepads are opened.
    int SDL_GetGamepadPlayerIndexForID(SdlJoystickId instanceId);

    /// Get the implementation-dependent GUID of a gamepad.
    ///
    /// This can be called before any gamepads are opened.
    SdlGuid SDL_GetGamepadGUIDForID(SdlJoystickId instanceId);

    /// Get the USB vendor ID of a gamepad, if available.
    ///
    /// This can be called before any gamepads are opened. If the vendor ID isn't
    /// available this function returns 0.
    short SDL_GetGamepadVendorForID(SdlJoystickId instanceId);

    /// Get the USB product ID of a gamepad, if available.
    ///
    /// This can be called before any gamepads are opened. If the product ID isn't
    /// available this function returns 0.
    short SDL_GetGamepadProductForID(SdlJoystickId instanceId);

    /// Get the product version of a gamepad, if available.
    ///
    /// This can be called before any gamepads are opened. If the product version
    /// isn't available this function returns 0.
    short SDL_GetGamepadProductVersionForID(SdlJoystickId instanceId);

    /// Get the type of a gamepad.
    ///
    /// This can be called before any gamepads are opened.
    int SDL_GetGamepadTypeForID(SdlJoystickId instanceId);

    /// Get the type of a gamepad, ignoring any mapping override.
    ///
    /// This can be called before any gamepads are opened.
    int SDL_GetRealGamepadTypeForID(SdlJoystickId instanceId);

    /// Get the mapping of a gamepad.
    ///
    /// This can be called before any gamepads are opened.
    String SDL_GetGamepadMappingForID(SdlJoystickId instanceId);

    /// Open a gamepad for use.
    SdlGamepadHandle SDL_OpenGamepad(SdlJoystickId instanceId);

    /// Get the SDL_Gamepad associated with a joystick instance ID, if it has been
    /// opened.
    SdlGamepadHandle SDL_GetGamepadFromID(SdlJoystickId instanceId);

    /// Get the SDL_Gamepad associated with a player index.
    SdlGamepadHandle SDL_GetGamepadFromPlayerIndex(int playerIndex);

    /// Get the properties associated with an opened gamepad.
    ///
    /// These properties are shared with the underlying joystick object.
    ///
    /// The following read-only properties are provided by SDL:
    ///
    /// - `SDL_PROP_GAMEPAD_CAP_MONO_LED_BOOLEAN`: true if this gamepad has an LED
    ///   that has adjustable brightness
    /// - `SDL_PROP_GAMEPAD_CAP_RGB_LED_BOOLEAN`: true if this gamepad has an LED
    ///   that has adjustable color
    /// - `SDL_PROP_GAMEPAD_CAP_PLAYER_LED_BOOLEAN`: true if this gamepad has a
    ///   player LED
    /// - `SDL_PROP_GAMEPAD_CAP_RUMBLE_BOOLEAN`: true if this gamepad has
    ///   left/right rumble
    /// - `SDL_PROP_GAMEPAD_CAP_TRIGGER_RUMBLE_BOOLEAN`: true if this gamepad has
    ///   simple trigger rumble
    SdlPropertiesId SDL_GetGamepadProperties(SdlGamepadHandle gamepad);

    /// Get the instance ID of an opened gamepad.
    SdlJoystickId SDL_GetGamepadID(SdlGamepadHandle gamepad);

    /// Get the implementation-dependent name for an opened gamepad.
    String SDL_GetGamepadName(SdlGamepadHandle gamepad);

    /// Get the implementation-dependent path for an opened gamepad.
    String SDL_GetGamepadPath(SdlGamepadHandle gamepad);

    /// Get the type of an opened gamepad.
    int SDL_GetGamepadType(SdlGamepadHandle gamepad);

    /// Get the type of an opened gamepad, ignoring any mapping override.
    int SDL_GetRealGamepadType(SdlGamepadHandle gamepad);

    /// Get the player index of an opened gamepad.
    ///
    /// For XInput gamepads this returns the XInput user index.
    int SDL_GetGamepadPlayerIndex(SdlGamepadHandle gamepad);

    /// Set the player index of an opened gamepad.
    boolean SDL_SetGamepadPlayerIndex(SdlGamepadHandle gamepad, int playerIndex);

    /// Get the USB vendor ID of an opened gamepad, if available.
    ///
    /// If the vendor ID isn't available this function returns 0.
    short SDL_GetGamepadVendor(SdlGamepadHandle gamepad);

    /// Get the USB product ID of an opened gamepad, if available.
    ///
    /// If the product ID isn't available this function returns 0.
    short SDL_GetGamepadProduct(SdlGamepadHandle gamepad);

    /// Get the product version of an opened gamepad, if available.
    ///
    /// If the product version isn't available this function returns 0.
    short SDL_GetGamepadProductVersion(SdlGamepadHandle gamepad);

    /// Get the firmware version of an opened gamepad, if available.
    ///
    /// If the firmware version isn't available this function returns 0.
    short SDL_GetGamepadFirmwareVersion(SdlGamepadHandle gamepad);

    /// Get the serial number of an opened gamepad, if available.
    ///
    /// Returns the serial number of the gamepad, or NULL if it is not available.
    String SDL_GetGamepadSerial(SdlGamepadHandle gamepad);

    /// Get the Steam Input handle of an opened gamepad, if available.
    ///
    /// Returns an InputHandle_t for the gamepad that can be used with Steam Input
    /// API: https://partner.steamgames.com/doc/api/ISteamInput
    long SDL_GetGamepadSteamHandle(SdlGamepadHandle gamepad);

    /// Get the connection state of a gamepad.
    int SDL_GetGamepadConnectionState(SdlGamepadHandle gamepad);

    /// Get the battery state of a gamepad.
    ///
    /// You should never take a battery status as absolute truth. Batteries
    /// (especially failing batteries) are delicate hardware, and the values
    /// reported here are best estimates based on what that hardware reports. It's
    /// not uncommon for older batteries to lose stored power much faster than it
    /// reports, or completely drain when reporting it has 20 percent left, etc.
    int SDL_GetGamepadPowerInfo(SdlGamepadHandle gamepad, IntRef percent);

    /// Check if a gamepad has been opened and is currently connected.
    boolean SDL_GamepadConnected(SdlGamepadHandle gamepad);

    /// Get the underlying joystick from a gamepad.
    ///
    /// This function will give you a SDL_Joystick object, which allows you to use
    /// the SDL_Joystick functions with a SDL_Gamepad object. This would be useful
    /// for getting a joystick's position at any given time, even if it hasn't
    /// moved (moving it would produce an event, which would have the axis' value).
    ///
    /// The pointer returned is owned by the SDL_Gamepad. You should not call
    /// SDL_CloseJoystick() on it, for example, since doing so will likely cause
    /// SDL to crash.
    SdlJoystickHandle SDL_GetGamepadJoystick(SdlGamepadHandle gamepad);

    /// Set the state of gamepad event processing.
    ///
    /// If gamepad events are disabled, you must call SDL_UpdateGamepads() yourself
    /// and check the state of the gamepad when you want gamepad information.
    void SDL_SetGamepadEventsEnabled(boolean enabled);

    /// Query the state of gamepad event processing.
    ///
    /// If gamepad events are disabled, you must call SDL_UpdateGamepads() yourself
    /// and check the state of the gamepad when you want gamepad information.
    boolean SDL_GamepadEventsEnabled();

    /// Get the SDL joystick layer bindings for a gamepad.
    SdlGamepadBinding[] SDL_GetGamepadBindings(SdlGamepadHandle gamepad);

    /// Manually pump gamepad updates if not using the loop.
    ///
    /// This function is called automatically by the event loop if events are
    /// enabled. Under such circumstances, it will not be necessary to call this
    /// function.
    void SDL_UpdateGamepads();

    /// Convert a string into SDL_GamepadType enum.
    ///
    /// This function is called internally to translate SDL_Gamepad mapping strings
    /// for the underlying joystick device into the consistent SDL_Gamepad mapping.
    /// You do not normally need to call this function unless you are parsing
    /// SDL_Gamepad mappings in your own code.
    int SDL_GetGamepadTypeFromString(String value);

    /// Convert from an SDL_GamepadType enum to a string.
    String SDL_GetGamepadStringForType(int type);

    /// Convert a string into SDL_GamepadAxis enum.
    ///
    /// This function is called internally to translate SDL_Gamepad mapping strings
    /// for the underlying joystick device into the consistent SDL_Gamepad mapping.
    /// You do not normally need to call this function unless you are parsing
    /// SDL_Gamepad mappings in your own code.
    ///
    /// Note specially that "righttrigger" and "lefttrigger" map to
    /// `SDL_GAMEPAD_AXIS_RIGHT_TRIGGER` and `SDL_GAMEPAD_AXIS_LEFT_TRIGGER`,
    /// respectively.
    int SDL_GetGamepadAxisFromString(String value);

    /// Convert from an SDL_GamepadAxis enum to a string.
    String SDL_GetGamepadStringForAxis(int axis);

    /// Query whether a gamepad has a given axis.
    ///
    /// This merely reports whether the gamepad's mapping defined this axis, as
    /// that is all the information SDL has about the physical device.
    boolean SDL_GamepadHasAxis(SdlGamepadHandle gamepad, int axis);

    /// Get the current state of an axis control on a gamepad.
    ///
    /// The axis indices start at index 0.
    ///
    /// For thumbsticks, the state is a value ranging from -32768 (up/left) to
    /// 32767 (down/right).
    ///
    /// Triggers range from 0 when released to 32767 when fully pressed, and never
    /// return a negative value. Note that this differs from the value reported by
    /// the lower-level SDL_GetJoystickAxis(), which normally uses the full range.
    ///
    /// Note that for invalid gamepads or axes, this will return 0. Zero is also a
    /// valid value in normal operation; usually it means a centered axis.
    short SDL_GetGamepadAxis(SdlGamepadHandle gamepad, int axis);

    /// Convert a string into an SDL_GamepadButton enum.
    ///
    /// This function is called internally to translate SDL_Gamepad mapping strings
    /// for the underlying joystick device into the consistent SDL_Gamepad mapping.
    /// You do not normally need to call this function unless you are parsing
    /// SDL_Gamepad mappings in your own code.
    int SDL_GetGamepadButtonFromString(String value);

    /// Convert from an SDL_GamepadButton enum to a string.
    String SDL_GetGamepadStringForButton(int button);

    /// Query whether a gamepad has a given button.
    ///
    /// This merely reports whether the gamepad's mapping defined this button, as
    /// that is all the information SDL has about the physical device.
    boolean SDL_GamepadHasButton(SdlGamepadHandle gamepad, int button);

    /// Get the current state of a button on a gamepad.
    boolean SDL_GetGamepadButton(SdlGamepadHandle gamepad, int button);

    /// Get the label of a button on a gamepad.
    int SDL_GetGamepadButtonLabelForType(int type, int button);

    /// Get the label of a button on a gamepad.
    int SDL_GetGamepadButtonLabel(SdlGamepadHandle gamepad, int button);

    /// Get the number of touchpads on a gamepad.
    int SDL_GetNumGamepadTouchpads(SdlGamepadHandle gamepad);

    /// Get the number of supported simultaneous fingers on a touchpad on a game
    /// gamepad.
    int SDL_GetNumGamepadTouchpadFingers(SdlGamepadHandle gamepad, int touchpad);

    /// Get the current state of a finger on a touchpad on a gamepad.
    boolean SDL_GetGamepadTouchpadFinger(SdlGamepadHandle gamepad, int touchpad, int finger,
                                         ByteRef down, FloatRef x, FloatRef y, FloatRef pressure);

    /// Return whether a gamepad has a particular sensor.
    boolean SDL_GamepadHasSensor(SdlGamepadHandle gamepad, int type);

    /// Set whether data reporting for a gamepad sensor is enabled.
    boolean SDL_SetGamepadSensorEnabled(SdlGamepadHandle gamepad, int type, boolean enabled);

    /// Query whether sensor data reporting is enabled for a gamepad.
    boolean SDL_GamepadSensorEnabled(SdlGamepadHandle gamepad, int type);

    /// Get the data rate (number of events per second) of a gamepad sensor.
    float SDL_GetGamepadSensorDataRate(SdlGamepadHandle gamepad, int type);

    /// Get the current state of a gamepad sensor.
    ///
    /// The number of values and interpretation of the data is sensor dependent.
    /// See the remarks in SDL_SensorType for details for each type of sensor.
    boolean SDL_GetGamepadSensorData(SdlGamepadHandle gamepad, int type, FloatBuffer data);

    /// Start a rumble effect on a gamepad.
    ///
    /// Each call to this function cancels any previous rumble effect, and calling
    /// it with 0 intensity stops any rumbling.
    boolean SDL_RumbleGamepad(SdlGamepadHandle gamepad, short lowFrequencyRumble,
                              short highFrequencyRumble, int durationMs);

    /// Start a rumble effect in the gamepad's triggers.
    ///
    /// Each call to this function cancels any previous trigger rumble effect, and
    /// calling it with 0 intensity stops any rumbling.
    boolean SDL_RumbleGamepadTriggers(SdlGamepadHandle gamepad, short leftRumble,
                                      short rightRumble, int durationMs);

    /// Update a gamepad's LED color.
    ///
    /// An example of a joystick LED is the light on the back of a PlayStation 4's
    /// DualShock 4 controller.
    ///
    /// For gamepads with a single color LED, the maximum of the RGB values will be
    /// used as the LED brightness.
    boolean SDL_SetGamepadLED(SdlGamepadHandle gamepad, byte red, byte green, byte blue);

    /// Send a gamepad specific effect packet.
    boolean SDL_SendGamepadEffect(SdlGamepadHandle gamepad, ByteBuffer data);

    /// Close a gamepad previously opened with SDL_OpenGamepad().
    void SDL_CloseGamepad(SdlGamepadHandle gamepad);

    /// Return the sfSymbolsName for a given button on a gamepad on Apple
    /// platforms.
    String SDL_GetGamepadAppleSFSymbolsNameForButton(SdlGamepadHandle gamepad, int button);

    /// Return the sfSymbolsName for a given axis on a gamepad on Apple platforms.
    String SDL_GetGamepadAppleSFSymbolsNameForAxis(SdlGamepadHandle gamepad, int axis);
}
