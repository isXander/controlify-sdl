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

    public record Common() implements EventData {
    }

    public record JoyAxis(SdlJoystickId which, byte axis, short value) implements EventData {
    }

    public record JoyBall(SdlJoystickId which, byte ball, short xrel, short yrel) implements EventData {
    }

    public record JoyHat(SdlJoystickId which, byte hat, byte value) implements EventData {
    }

    public record JoyButton(SdlJoystickId which, byte button, boolean down) implements EventData {
    }

    public record JoyDevice(SdlJoystickId which) implements EventData {
    }

    public record JoyBattery(SdlJoystickId which, int state, int percent) implements EventData {
    }

    public record GamepadAxis(SdlJoystickId which, byte axis, short value) implements EventData {
    }

    public record GamepadButton(SdlJoystickId which, byte button, boolean down) implements EventData {
    }

    public record GamepadDevice(SdlJoystickId which) implements EventData {
    }

    public record GamepadTouchpad(SdlJoystickId which, int touchpad, int finger,
                                  float x, float y, float pressure) implements EventData {
    }

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
