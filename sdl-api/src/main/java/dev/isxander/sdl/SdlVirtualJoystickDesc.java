package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.VirtualJoystickCleanupCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickRumbleCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickRumbleTriggersCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSendEffectCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSetLedCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSetPlayerIndexCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickSetSensorsEnabledCallback;
import dev.isxander.sdl.SdlCallbacks.VirtualJoystickUpdateCallback;

/// Describes a virtual joystick.
///
/// All elements of this structure are optional. The native backend initializes
/// the interface version as required by `SDL_INIT_INTERFACE`.
///
/// @param type the joystick type, one of the `SDL_JOYSTICK_TYPE_*` values.
/// @param vendorId the USB vendor ID of this joystick.
/// @param productId the USB product ID of this joystick.
/// @param numAxes the number of axes on this joystick.
/// @param numButtons the number of buttons on this joystick.
/// @param numBalls the number of balls on this joystick.
/// @param numHats the number of hats on this joystick.
/// @param buttonMask a mask of the gamepad buttons that are valid.
/// @param axisMask a mask of the gamepad axes that are valid.
/// @param name the name of the joystick, or `null` to use SDL's default.
/// @param touchpads the touchpad descriptions, or `null` for no touchpads.
/// @param sensors the sensor descriptions, or `null` for no sensors.
/// @param userdata user data passed to callbacks, or `null` for a null pointer.
/// @param update called when the joystick state should be updated.
/// @param setPlayerIndex called when the player index is set.
/// @param rumble implements `SDL_RumbleJoystick()`.
/// @param rumbleTriggers implements `SDL_RumbleJoystickTriggers()`.
/// @param setLed implements `SDL_SetJoystickLED()`.
/// @param sendEffect implements `SDL_SendJoystickEffect()`.
/// @param setSensorsEnabled implements `SDL_SetGamepadSensorEnabled()`.
/// @param cleanup cleans up userdata when the joystick is detached.
///
/// @since This struct is available since SDL 3.2.0.
public record SdlVirtualJoystickDesc(
        int type,
        int vendorId,
        int productId,
        int numAxes,
        int numButtons,
        int numBalls,
        int numHats,
        int buttonMask,
        int axisMask,
        String name,
        SdlVirtualJoystickTouchpadDesc[] touchpads,
        SdlVirtualJoystickSensorDesc[] sensors,
        SdlPointer userdata,
        VirtualJoystickUpdateCallback update,
        VirtualJoystickSetPlayerIndexCallback setPlayerIndex,
        VirtualJoystickRumbleCallback rumble,
        VirtualJoystickRumbleTriggersCallback rumbleTriggers,
        VirtualJoystickSetLedCallback setLed,
        VirtualJoystickSendEffectCallback sendEffect,
        VirtualJoystickSetSensorsEnabledCallback setSensorsEnabled,
        VirtualJoystickCleanupCallback cleanup
) {
    public SdlVirtualJoystickDesc {
        requireUint16("type", type);
        requireUint16("vendorId", vendorId);
        requireUint16("productId", productId);
        requireUint16("numAxes", numAxes);
        requireUint16("numButtons", numButtons);
        requireUint16("numBalls", numBalls);
        requireUint16("numHats", numHats);

        touchpads = touchpads == null ? new SdlVirtualJoystickTouchpadDesc[0] : touchpads.clone();
        sensors = sensors == null ? new SdlVirtualJoystickSensorDesc[0] : sensors.clone();
        requireUint16("touchpads.length", touchpads.length);
        requireUint16("sensors.length", sensors.length);
        userdata = userdata == null ? SdlPointer.NULL : userdata;
    }

    @Override
    public SdlVirtualJoystickTouchpadDesc[] touchpads() {
        return touchpads.clone();
    }

    @Override
    public SdlVirtualJoystickSensorDesc[] sensors() {
        return sensors.clone();
    }

    private static void requireUint16(String name, int value) {
        if (value < 0 || value > 0xffff) {
            throw new IllegalArgumentException(name + " must fit in an unsigned 16-bit integer");
        }
    }
}
