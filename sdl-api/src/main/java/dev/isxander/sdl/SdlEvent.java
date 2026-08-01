package dev.isxander.sdl;

import java.util.Arrays;


/**
 * Mutable event value populated by SDL's event queue functions.
 */
public final class SdlEvent {
    private int type;
    private long timestamp;
    private EventData data;

    public SdlEvent() {
        this(0, 0, new Common());
    }

    public SdlEvent(int type, long timestamp, EventData data) {
        this.type = type;
        this.timestamp = timestamp;
        this.data = data;
    }

    public int type() {
        return type;
    }

    public long timestamp() {
        return timestamp;
    }

    public EventData data() {
        return data;
    }

    public void set(int type, long timestamp, EventData data) {
        this.type = type;
        this.timestamp = timestamp;
        this.data = data;
    }

    public sealed interface EventData permits Common, JoyAxis, JoyBall, JoyHat, JoyButton,
            JoyDevice, JoyBattery, GamepadAxis, GamepadButton, GamepadDevice,
            GamepadTouchpad, GamepadSensor {
    }

    /// Fields shared by every event
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record Common() implements EventData {
    }

    /// Joystick axis motion event structure (event.jaxis.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record JoyAxis(SdlJoystickId which, byte axis, short value) implements EventData {
    }

    /// Joystick trackball motion event structure (event.jball.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record JoyBall(SdlJoystickId which, byte ball, short xrel, short yrel) implements EventData {
    }

    /// Joystick hat position change event structure (event.jhat.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record JoyHat(SdlJoystickId which, byte hat, byte value) implements EventData {
    }

    /// Joystick button event structure (event.jbutton.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record JoyButton(SdlJoystickId which, byte button, boolean down) implements EventData {
    }

    /// Joystick device event structure (event.jdevice.*)
    ///
    /// SDL will send JOYSTICK_ADDED events for devices that are already plugged in
    /// during SDL_Init.
    ///
    /// @since This struct is available since SDL 3.2.0.
    ///
    /// See `SDL_GamepadDeviceEvent`.
    public record JoyDevice(SdlJoystickId which) implements EventData {
    }

    /// Joystick battery level change event structure (event.jbattery.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record JoyBattery(SdlJoystickId which, int state, int percent) implements EventData {
    }

    /// Gamepad axis motion event structure (event.gaxis.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record GamepadAxis(SdlJoystickId which, byte axis, short value) implements EventData {
    }

    /// Gamepad button event structure (event.gbutton.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record GamepadButton(SdlJoystickId which, byte button, boolean down) implements EventData {
    }

    /// Gamepad device event structure (event.gdevice.*)
    ///
    /// Joysticks that are supported gamepads receive both an SDL_JoyDeviceEvent
    /// and an SDL_GamepadDeviceEvent.
    ///
    /// SDL will send GAMEPAD_ADDED events for joysticks that are already plugged
    /// in during SDL_Init() and are recognized as gamepads. It will also send
    /// events for joysticks that get gamepad mappings at runtime.
    ///
    /// @since This struct is available since SDL 3.2.0.
    ///
    /// See `SDL_JoyDeviceEvent`.
    public record GamepadDevice(SdlJoystickId which) implements EventData {
    }

    /// Gamepad touchpad event structure (event.gtouchpad.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record GamepadTouchpad(SdlJoystickId which, int touchpad, int finger,
                                  float x, float y, float pressure) implements EventData {
    }

    /// Gamepad sensor event structure (event.gsensor.*)
    ///
    /// @since This struct is available since SDL 3.2.0.
    public record GamepadSensor(SdlJoystickId which, int sensor, float[] values,
                                long sensorTimestamp) implements EventData {
        public GamepadSensor {
            values = values.clone();
        }

        @Override
        public float[] values() {
            return values.clone();
        }

        @Override
        public String toString() {
            return "GamepadSensor[which=" + which + ", sensor=" + sensor
                   + ", values=" + Arrays.toString(values) + ", sensorTimestamp=" + sensorTimestamp + ']';
        }
    }
}
