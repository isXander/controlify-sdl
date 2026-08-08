package dev.isxander.sdl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import dev.isxander.sdl.SdlRefs.IntRef;
import dev.isxander.sdl.SdlRefs.ShortRef;

/// An enum of some common joystick types.
///
/// In some cases, SDL can identify a low-level joystick as being a certain
/// type of device, and will report it through SDL_GetJoystickType (or
/// SDL_GetJoystickTypeForID).
///
/// This is by no means a complete list of everything that can be plugged into
/// a computer.
///
/// You may refer to
/// [XInput Controller Types](https://learn.microsoft.com/en-us/windows/win32/xinput/xinput-and-controller-subtypes)
/// table for a general understanding of each joystick type.
///
/// @since This enum is available since SDL 3.2.0.
///
/// Possible connection states for a joystick device.
///
/// This is used by SDL_GetJoystickConnectionState to report how a device is
/// connected to the system.
///
/// @since This enum is available since SDL 3.2.0.
public interface SdlJoystick {
    int SDL_JOYSTICK_TYPE_UNKNOWN = 0;
    int SDL_JOYSTICK_TYPE_GAMEPAD = 1;
    int SDL_JOYSTICK_TYPE_WHEEL = 2;
    int SDL_JOYSTICK_TYPE_ARCADE_STICK = 3;
    int SDL_JOYSTICK_TYPE_FLIGHT_STICK = 4;
    int SDL_JOYSTICK_TYPE_DANCE_PAD = 5;
    int SDL_JOYSTICK_TYPE_GUITAR = 6;
    int SDL_JOYSTICK_TYPE_DRUM_KIT = 7;
    int SDL_JOYSTICK_TYPE_ARCADE_PAD = 8;
    int SDL_JOYSTICK_TYPE_THROTTLE = 9;
    int SDL_JOYSTICK_TYPE_COUNT = 10;

    int SDL_JOYSTICK_CONNECTION_INVALID = -1;
    int SDL_JOYSTICK_CONNECTION_UNKNOWN = 0;
    int SDL_JOYSTICK_CONNECTION_WIRED = 1;
    int SDL_JOYSTICK_CONNECTION_WIRELESS = 2;

    byte SDL_HAT_CENTERED = 0x00;
    byte SDL_HAT_UP = 0x01;
    byte SDL_HAT_RIGHT = 0x02;
    byte SDL_HAT_DOWN = 0x04;
    byte SDL_HAT_LEFT = 0x08;
    byte SDL_HAT_RIGHTUP = SDL_HAT_RIGHT | SDL_HAT_UP;
    byte SDL_HAT_RIGHTDOWN = SDL_HAT_RIGHT | SDL_HAT_DOWN;
    byte SDL_HAT_LEFTUP = SDL_HAT_LEFT | SDL_HAT_UP;
    byte SDL_HAT_LEFTDOWN = SDL_HAT_LEFT | SDL_HAT_DOWN;

    /// Locking for atomic access to the joystick API.
    ///
    /// The SDL joystick functions are thread-safe, however you can lock the
    /// joysticks while processing to guarantee that the joystick list won't change
    /// and joystick and gamepad events will not be delivered.
    void SDL_LockJoysticks();

    /// Unlocking for atomic access to the joystick API.
    void SDL_UnlockJoysticks();

    /// Return whether a joystick is currently connected.
    boolean SDL_HasJoystick();

    /// Get a list of currently connected joysticks.
    SdlJoystickId[] SDL_GetJoysticks();

    /// Get the implementation dependent name of a joystick.
    ///
    /// This can be called before any joysticks are opened.
    String SDL_GetJoystickNameForID(SdlJoystickId instanceId);

    /// Get the implementation dependent path of a joystick.
    ///
    /// This can be called before any joysticks are opened.
    String SDL_GetJoystickPathForID(SdlJoystickId instanceId);

    /// Get the player index of a joystick.
    ///
    /// This can be called before any joysticks are opened.
    int SDL_GetJoystickPlayerIndexForID(SdlJoystickId instanceId);

    /// Get the implementation-dependent GUID of a joystick.
    ///
    /// This can be called before any joysticks are opened.
    SdlGuid SDL_GetJoystickGUIDForID(SdlJoystickId instanceId);

    /// Get the USB vendor ID of a joystick, if available.
    ///
    /// This can be called before any joysticks are opened. If the vendor ID isn't
    /// available this function returns 0.
    short SDL_GetJoystickVendorForID(SdlJoystickId instanceId);

    /// Get the USB product ID of a joystick, if available.
    ///
    /// This can be called before any joysticks are opened. If the product ID isn't
    /// available this function returns 0.
    short SDL_GetJoystickProductForID(SdlJoystickId instanceId);

    /// Get the product version of a joystick, if available.
    ///
    /// This can be called before any joysticks are opened. If the product version
    /// isn't available this function returns 0.
    short SDL_GetJoystickProductVersionForID(SdlJoystickId instanceId);

    /// Get the type of a joystick, if available.
    ///
    /// This can be called before any joysticks are opened.
    int SDL_GetJoystickTypeForID(SdlJoystickId instanceId);

    /// Open a joystick for use.
    ///
    /// The joystick subsystem must be initialized before a joystick can be opened
    /// for use.
    SdlJoystickHandle SDL_OpenJoystick(SdlJoystickId instanceId);

    /// Get the SDL_Joystick associated with an instance ID, if it has been opened.
    SdlJoystickHandle SDL_GetJoystickFromID(SdlJoystickId instanceId);

    /// Get the SDL_Joystick associated with a player index.
    SdlJoystickHandle SDL_GetJoystickFromPlayerIndex(int playerIndex);

    /// Attach a new virtual joystick.
    ///
    /// Apps can create virtual joysticks that have program-supplied inputs but no
    /// directly backing hardware. Once attached, a virtual joystick looks like
    /// any other joystick that SDL can access.
    ///
    /// @param desc the joystick description.
    /// @return the joystick instance ID, or 0 on failure; call `SDL_GetError()`
    ///         for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlJoystickId SDL_AttachVirtualJoystick(SdlVirtualJoystickDesc desc);

    /// Detach a virtual joystick.
    ///
    /// @param instanceId the joystick instance ID returned from
    ///                   `SDL_AttachVirtualJoystick()`.
    /// @return true on success or false on failure; call `SDL_GetError()` for
    ///         more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_DetachVirtualJoystick(SdlJoystickId instanceId);

    /// Query whether a joystick is virtual.
    ///
    /// @param instanceId the joystick instance ID.
    /// @return true if the joystick is virtual, false otherwise.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_IsJoystickVirtual(SdlJoystickId instanceId);

    /// Set the state of an axis on an opened virtual joystick.
    ///
    /// Values set here are applied on the next call to `SDL_UpdateJoysticks()`.
    ///
    /// @param joystick the virtual joystick on which to set state.
    /// @param axis the index of the axis to update.
    /// @param value the new value for the axis.
    /// @return true on success or false on failure; call `SDL_GetError()` for
    ///         more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SetJoystickVirtualAxis(SdlJoystickHandle joystick, int axis, short value);

    /// Generate ball motion on an opened virtual joystick.
    ///
    /// Values set here are applied on the next call to `SDL_UpdateJoysticks()`.
    ///
    /// @param joystick the virtual joystick on which to set state.
    /// @param ball the index of the ball to update.
    /// @param xrel the relative motion on the X axis.
    /// @param yrel the relative motion on the Y axis.
    /// @return true on success or false on failure; call `SDL_GetError()` for
    ///         more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SetJoystickVirtualBall(SdlJoystickHandle joystick, int ball, short xrel, short yrel);

    /// Set the state of a button on an opened virtual joystick.
    ///
    /// Values set here are applied on the next call to `SDL_UpdateJoysticks()`.
    ///
    /// @param joystick the virtual joystick on which to set state.
    /// @param button the index of the button to update.
    /// @param down true if the button is pressed, false otherwise.
    /// @return true on success or false on failure; call `SDL_GetError()` for
    ///         more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SetJoystickVirtualButton(SdlJoystickHandle joystick, int button, boolean down);

    /// Set the state of a hat on an opened virtual joystick.
    ///
    /// Values set here are applied on the next call to `SDL_UpdateJoysticks()`.
    ///
    /// @param joystick the virtual joystick on which to set state.
    /// @param hat the index of the hat to update.
    /// @param value the new value for the hat, one of the `SDL_HAT_*` values.
    /// @return true on success or false on failure; call `SDL_GetError()` for
    ///         more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SetJoystickVirtualHat(SdlJoystickHandle joystick, int hat, byte value);

    /// Set touchpad finger state on an opened virtual joystick.
    ///
    /// Values set here are applied on the next call to `SDL_UpdateJoysticks()`.
    ///
    /// @param joystick the virtual joystick on which to set state.
    /// @param touchpad the index of the touchpad to update.
    /// @param finger the index of the finger to update.
    /// @param down true if the finger is pressed, false if it is released.
    /// @param x the finger's x coordinate, normalized from 0 to 1.
    /// @param y the finger's y coordinate, normalized from 0 to 1.
    /// @param pressure the pressure of the finger.
    /// @return true on success or false on failure; call `SDL_GetError()` for
    ///         more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SetJoystickVirtualTouchpad(SdlJoystickHandle joystick, int touchpad, int finger,
                                           boolean down, float x, float y, float pressure);

    /// Send a sensor update for an opened virtual joystick.
    ///
    /// Values set here are applied on the next call to `SDL_UpdateJoysticks()`.
    ///
    /// @param joystick the virtual joystick on which to set state.
    /// @param type the type of sensor to update.
    /// @param sensorTimestamp a timestamp in nanoseconds associated with the reading.
    /// @param data the data associated with the sensor reading.
    /// @return true on success or false on failure; call `SDL_GetError()` for
    ///         more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_SendJoystickVirtualSensorData(SdlJoystickHandle joystick, int type,
                                              long sensorTimestamp, FloatBuffer data);

    /// Get the properties associated with a joystick.
    ///
    /// The following read-only properties are provided by SDL:
    ///
    /// - `SDL_PROP_JOYSTICK_CAP_MONO_LED_BOOLEAN`: true if this joystick has an
    ///   LED that has adjustable brightness
    /// - `SDL_PROP_JOYSTICK_CAP_RGB_LED_BOOLEAN`: true if this joystick has an LED
    ///   that has adjustable color
    /// - `SDL_PROP_JOYSTICK_CAP_PLAYER_LED_BOOLEAN`: true if this joystick has a
    ///   player LED
    /// - `SDL_PROP_JOYSTICK_CAP_RUMBLE_BOOLEAN`: true if this joystick has
    ///   left/right rumble
    /// - `SDL_PROP_JOYSTICK_CAP_TRIGGER_RUMBLE_BOOLEAN`: true if this joystick has
    ///   simple trigger rumble
    SdlPropertiesId SDL_GetJoystickProperties(SdlJoystickHandle joystick);

    /// Get the implementation dependent name of a joystick.
    String SDL_GetJoystickName(SdlJoystickHandle joystick);

    /// Get the implementation dependent path of a joystick.
    String SDL_GetJoystickPath(SdlJoystickHandle joystick);

    /// Get the player index of an opened joystick.
    ///
    /// For XInput controllers this returns the XInput user index. Many joysticks
    /// will not be able to supply this information.
    int SDL_GetJoystickPlayerIndex(SdlJoystickHandle joystick);

    /// Set the player index of an opened joystick.
    boolean SDL_SetJoystickPlayerIndex(SdlJoystickHandle joystick, int playerIndex);

    /// Get the USB vendor ID of an opened joystick, if available.
    ///
    /// If the vendor ID isn't available this function returns 0.
    short SDL_GetJoystickVendor(SdlJoystickHandle joystick);

    /// Get the USB product ID of an opened joystick, if available.
    ///
    /// If the product ID isn't available this function returns 0.
    short SDL_GetJoystickProduct(SdlJoystickHandle joystick);

    /// Get the product version of an opened joystick, if available.
    ///
    /// If the product version isn't available this function returns 0.
    short SDL_GetJoystickProductVersion(SdlJoystickHandle joystick);

    /// Get the firmware version of an opened joystick, if available.
    ///
    /// If the firmware version isn't available this function returns 0.
    short SDL_GetJoystickFirmwareVersion(SdlJoystickHandle joystick);

    /// Get the serial number of an opened joystick, if available.
    ///
    /// Returns the serial number of the joystick, or NULL if it is not available.
    String SDL_GetJoystickSerial(SdlJoystickHandle joystick);

    /// Get the type of an opened joystick.
    int SDL_GetJoystickType(SdlJoystickHandle joystick);

    /// Get the device information encoded in a SDL_GUID structure.
    void SDL_GetJoystickGUIDInfo(SdlGuid guid, ShortRef vendor, ShortRef product, ShortRef version, ShortRef crc16);

    /// Get the status of a specified joystick.
    boolean SDL_JoystickConnected(SdlJoystickHandle joystick);

    /// Get the instance ID of an opened joystick.
    SdlJoystickId SDL_GetJoystickID(SdlJoystickHandle joystick);

    /// Get the number of general axis controls on a joystick.
    ///
    /// Often, the directional pad on a game controller will either look like 4
    /// separate buttons or a POV hat, and not axes, but all of this is up to the
    /// device and platform.
    int SDL_GetNumJoystickAxes(SdlJoystickHandle joystick);

    /// Get the number of POV hats on a joystick.
    int SDL_GetNumJoystickHats(SdlJoystickHandle joystick);

    /// Get the number of buttons on a joystick.
    int SDL_GetNumJoystickButtons(SdlJoystickHandle joystick);

    /// Set the state of joystick event processing.
    ///
    /// If joystick events are disabled, you must call SDL_UpdateJoysticks()
    /// yourself and check the state of the joystick when you want joystick
    /// information.
    void SDL_SetJoystickEventsEnabled(boolean enabled);

    /// Query the state of joystick event processing.
    ///
    /// If joystick events are disabled, you must call SDL_UpdateJoysticks()
    /// yourself and check the state of the joystick when you want joystick
    /// information.
    boolean SDL_JoystickEventsEnabled();

    /// Update the current state of the open joysticks.
    ///
    /// This is called automatically by the event loop if any joystick events are
    /// enabled.
    void SDL_UpdateJoysticks();

    /// Get the current state of an axis control on a joystick.
    ///
    /// SDL makes no promises about what part of the joystick any given axis refers
    /// to. Your game should have some sort of configuration UI to let users
    /// specify what each axis should be bound to. Alternately, SDL's higher-level
    /// Game Controller API makes a great effort to apply order to this lower-level
    /// interface, so you know that a specific axis is the "left thumb stick," etc.
    ///
    /// The value returned by SDL_GetJoystickAxis() is a signed integer (-32768 to
    /// 32767) representing the current position of the axis. It may be necessary
    /// to impose certain tolerances on these values to account for jitter.
    short SDL_GetJoystickAxis(SdlJoystickHandle joystick, int axis);

    /// Get the initial state of an axis control on a joystick.
    ///
    /// The state is a value ranging from -32768 to 32767.
    ///
    /// The axis indices start at index 0.
    boolean SDL_GetJoystickAxisInitialState(SdlJoystickHandle joystick, int axis, ShortRef state);

    /// Get the current state of a POV hat on a joystick.
    ///
    /// The returned value will be one of the `SDL_HAT_*` values.
    byte SDL_GetJoystickHat(SdlJoystickHandle joystick, int hat);

    /// Get the current state of a button on a joystick.
    boolean SDL_GetJoystickButton(SdlJoystickHandle joystick, int button);

    /// Start a rumble effect.
    ///
    /// Each call to this function cancels any previous rumble effect, and calling
    /// it with 0 intensity stops any rumbling.
    boolean SDL_RumbleJoystick(SdlJoystickHandle joystick, short lowFrequencyRumble,
                               short highFrequencyRumble, int durationMs);

    /// Start a rumble effect in the joystick's triggers.
    ///
    /// Each call to this function cancels any previous trigger rumble effect, and
    /// calling it with 0 intensity stops any rumbling.
    boolean SDL_RumbleJoystickTriggers(SdlJoystickHandle joystick, short leftRumble,
                                       short rightRumble, int durationMs);

    /// Update a joystick's LED color.
    ///
    /// An example of a joystick LED is the light on the back of a PlayStation 4's
    /// DualShock 4 controller.
    ///
    /// For joysticks with a single color LED, the maximum of the RGB values will
    /// be used as the LED brightness.
    boolean SDL_SetJoystickLED(SdlJoystickHandle joystick, byte red, byte green, byte blue);

    /// Send a joystick specific effect packet.
    boolean SDL_SendJoystickEffect(SdlJoystickHandle joystick, ByteBuffer data);

    /// Close a joystick previously opened with SDL_OpenJoystick().
    void SDL_CloseJoystick(SdlJoystickHandle joystick);

    /// Get the connection state of a joystick.
    int SDL_GetJoystickConnectionState(SdlJoystickHandle joystick);

    /// Get the battery state of a joystick.
    ///
    /// You should never take a battery status as absolute truth. Batteries
    /// (especially failing batteries) are delicate hardware, and the values
    /// reported here are best estimates based on what that hardware reports. It's
    /// not uncommon for older batteries to lose stored power much faster than it
    /// reports, or completely drain when reporting it has 20 percent left, etc.
    int SDL_GetJoystickPowerInfo(SdlJoystickHandle joystick, IntRef percent);
}
