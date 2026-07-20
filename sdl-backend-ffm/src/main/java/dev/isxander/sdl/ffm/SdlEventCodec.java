package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlEvent;
import dev.isxander.sdl.SdlJoystickId;
import dev.isxander.sdl.ffm.internal.SdlCommonEvent;
import dev.isxander.sdl.ffm.internal.SdlEventLayout;
import dev.isxander.sdl.ffm.internal.SdlGamepadAxisEvent;
import dev.isxander.sdl.ffm.internal.SdlGamepadButtonEvent;
import dev.isxander.sdl.ffm.internal.SdlGamepadDeviceEvent;
import dev.isxander.sdl.ffm.internal.SdlGamepadSensorEvent;
import dev.isxander.sdl.ffm.internal.SdlGamepadTouchpadEvent;
import dev.isxander.sdl.ffm.internal.SdlJoyAxisEvent;
import dev.isxander.sdl.ffm.internal.SdlJoyBallEvent;
import dev.isxander.sdl.ffm.internal.SdlJoyBatteryEvent;
import dev.isxander.sdl.ffm.internal.SdlJoyButtonEvent;
import dev.isxander.sdl.ffm.internal.SdlJoyDeviceEvent;
import dev.isxander.sdl.ffm.internal.SdlJoyHatEvent;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_ADDED;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_AXIS_MOTION;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_BUTTON_DOWN;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_BUTTON_UP;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_REMAPPED;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_REMOVED;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_SENSOR_UPDATE;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_STEAM_HANDLE_UPDATED;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_TOUCHPAD_DOWN;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_TOUCHPAD_MOTION;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_TOUCHPAD_UP;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_GAMEPAD_UPDATE_COMPLETE;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_ADDED;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_AXIS_MOTION;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_BALL_MOTION;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_BATTERY_UPDATED;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_BUTTON_DOWN;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_BUTTON_UP;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_HAT_MOTION;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_REMOVED;
import static dev.isxander.sdl.SdlEvents.SDL_EVENT_JOYSTICK_UPDATE_COMPLETE;

final class SdlEventCodec {
    private SdlEventCodec() { }

    static MemorySegment allocate(SdlEvent event, Arena arena) {
        MemorySegment nativeEvent = SdlEventLayout.allocate(arena);
        write(event, nativeEvent);
        return nativeEvent;
    }

    static void read(MemorySegment address, SdlEvent target) {
        MemorySegment event = address.byteSize() < SdlEventLayout.sizeof()
                ? address.reinterpret(SdlEventLayout.sizeof()) : address;
        int type = SdlEventLayout.type(event);
        long timestamp = SdlCommonEvent.timestamp(SdlEventLayout.common(event));
        SdlEvent.EventData data = switch (type) {
            case SDL_EVENT_JOYSTICK_AXIS_MOTION -> {
                MemorySegment value = SdlEventLayout.jaxis(event);
                yield new SdlEvent.JoyAxis(id(SdlJoyAxisEvent.which(value)),
                        SdlJoyAxisEvent.axis(value), SdlJoyAxisEvent.value(value));
            }
            case SDL_EVENT_JOYSTICK_BALL_MOTION -> {
                MemorySegment value = SdlEventLayout.jball(event);
                yield new SdlEvent.JoyBall(id(SdlJoyBallEvent.which(value)), SdlJoyBallEvent.ball(value),
                        SdlJoyBallEvent.xrel(value), SdlJoyBallEvent.yrel(value));
            }
            case SDL_EVENT_JOYSTICK_HAT_MOTION -> {
                MemorySegment value = SdlEventLayout.jhat(event);
                yield new SdlEvent.JoyHat(id(SdlJoyHatEvent.which(value)), SdlJoyHatEvent.hat(value),
                        SdlJoyHatEvent.value(value));
            }
            case SDL_EVENT_JOYSTICK_BUTTON_DOWN, SDL_EVENT_JOYSTICK_BUTTON_UP -> {
                MemorySegment value = SdlEventLayout.jbutton(event);
                yield new SdlEvent.JoyButton(id(SdlJoyButtonEvent.which(value)),
                        SdlJoyButtonEvent.button(value), SdlJoyButtonEvent.down(value));
            }
            case SDL_EVENT_JOYSTICK_ADDED, SDL_EVENT_JOYSTICK_REMOVED,
                 SDL_EVENT_JOYSTICK_UPDATE_COMPLETE -> {
                MemorySegment value = SdlEventLayout.jdevice(event);
                yield new SdlEvent.JoyDevice(id(SdlJoyDeviceEvent.which(value)));
            }
            case SDL_EVENT_JOYSTICK_BATTERY_UPDATED -> {
                MemorySegment value = SdlEventLayout.jbattery(event);
                yield new SdlEvent.JoyBattery(id(SdlJoyBatteryEvent.which(value)),
                        SdlJoyBatteryEvent.state(value), SdlJoyBatteryEvent.percent(value));
            }
            case SDL_EVENT_GAMEPAD_AXIS_MOTION -> {
                MemorySegment value = SdlEventLayout.gaxis(event);
                yield new SdlEvent.GamepadAxis(id(SdlGamepadAxisEvent.which(value)),
                        SdlGamepadAxisEvent.axis(value), SdlGamepadAxisEvent.value(value));
            }
            case SDL_EVENT_GAMEPAD_BUTTON_DOWN, SDL_EVENT_GAMEPAD_BUTTON_UP -> {
                MemorySegment value = SdlEventLayout.gbutton(event);
                yield new SdlEvent.GamepadButton(id(SdlGamepadButtonEvent.which(value)),
                        SdlGamepadButtonEvent.button(value), SdlGamepadButtonEvent.down(value));
            }
            case SDL_EVENT_GAMEPAD_ADDED, SDL_EVENT_GAMEPAD_REMOVED, SDL_EVENT_GAMEPAD_REMAPPED,
                 SDL_EVENT_GAMEPAD_UPDATE_COMPLETE, SDL_EVENT_GAMEPAD_STEAM_HANDLE_UPDATED -> {
                MemorySegment value = SdlEventLayout.gdevice(event);
                yield new SdlEvent.GamepadDevice(id(SdlGamepadDeviceEvent.which(value)));
            }
            case SDL_EVENT_GAMEPAD_TOUCHPAD_DOWN, SDL_EVENT_GAMEPAD_TOUCHPAD_MOTION,
                 SDL_EVENT_GAMEPAD_TOUCHPAD_UP -> {
                MemorySegment value = SdlEventLayout.gtouchpad(event);
                yield new SdlEvent.GamepadTouchpad(id(SdlGamepadTouchpadEvent.which(value)),
                        SdlGamepadTouchpadEvent.touchpad(value), SdlGamepadTouchpadEvent.finger(value),
                        SdlGamepadTouchpadEvent.x(value), SdlGamepadTouchpadEvent.y(value),
                        SdlGamepadTouchpadEvent.pressure(value));
            }
            case SDL_EVENT_GAMEPAD_SENSOR_UPDATE -> {
                MemorySegment value = SdlEventLayout.gsensor(event);
                yield new SdlEvent.GamepadSensor(id(SdlGamepadSensorEvent.which(value)),
                        SdlGamepadSensorEvent.sensor(value),
                        new float[]{SdlGamepadSensorEvent.data(value, 0),
                                SdlGamepadSensorEvent.data(value, 1),
                                SdlGamepadSensorEvent.data(value, 2)},
                        SdlGamepadSensorEvent.sensor_timestamp(value));
            }
            default -> new SdlEvent.Common();
        };
        target.set(type, timestamp, data);
    }

    static void write(SdlEvent source, MemorySegment event) {
        event.fill((byte) 0);
        SdlEventLayout.type(event, source.type());
        SdlCommonEvent.timestamp(SdlEventLayout.common(event), source.timestamp());
        switch (source.data()) {
            case SdlEvent.Common ignored -> { }
            case SdlEvent.JoyAxis data -> {
                MemorySegment value = SdlEventLayout.jaxis(event);
                commonJoy(value, data.which(), source);
                SdlJoyAxisEvent.axis(value, data.axis());
                SdlJoyAxisEvent.value(value, data.value());
            }
            case SdlEvent.JoyBall data -> {
                MemorySegment value = SdlEventLayout.jball(event);
                SdlJoyBallEvent.type(value, source.type());
                SdlJoyBallEvent.timestamp(value, source.timestamp());
                SdlJoyBallEvent.which(value, data.which().value());
                SdlJoyBallEvent.ball(value, data.ball());
                SdlJoyBallEvent.xrel(value, data.xrel());
                SdlJoyBallEvent.yrel(value, data.yrel());
            }
            case SdlEvent.JoyHat data -> {
                MemorySegment value = SdlEventLayout.jhat(event);
                SdlJoyHatEvent.type(value, source.type());
                SdlJoyHatEvent.timestamp(value, source.timestamp());
                SdlJoyHatEvent.which(value, data.which().value());
                SdlJoyHatEvent.hat(value, data.hat());
                SdlJoyHatEvent.value(value, data.value());
            }
            case SdlEvent.JoyButton data -> {
                MemorySegment value = SdlEventLayout.jbutton(event);
                SdlJoyButtonEvent.type(value, source.type());
                SdlJoyButtonEvent.timestamp(value, source.timestamp());
                SdlJoyButtonEvent.which(value, data.which().value());
                SdlJoyButtonEvent.button(value, data.button());
                SdlJoyButtonEvent.down(value, data.down());
            }
            case SdlEvent.JoyDevice data -> {
                MemorySegment value = SdlEventLayout.jdevice(event);
                SdlJoyDeviceEvent.type(value, source.type());
                SdlJoyDeviceEvent.timestamp(value, source.timestamp());
                SdlJoyDeviceEvent.which(value, data.which().value());
            }
            case SdlEvent.JoyBattery data -> {
                MemorySegment value = SdlEventLayout.jbattery(event);
                SdlJoyBatteryEvent.type(value, source.type());
                SdlJoyBatteryEvent.timestamp(value, source.timestamp());
                SdlJoyBatteryEvent.which(value, data.which().value());
                SdlJoyBatteryEvent.state(value, data.state());
                SdlJoyBatteryEvent.percent(value, data.percent());
            }
            case SdlEvent.GamepadAxis data -> {
                MemorySegment value = SdlEventLayout.gaxis(event);
                SdlGamepadAxisEvent.type(value, source.type());
                SdlGamepadAxisEvent.timestamp(value, source.timestamp());
                SdlGamepadAxisEvent.which(value, data.which().value());
                SdlGamepadAxisEvent.axis(value, data.axis());
                SdlGamepadAxisEvent.value(value, data.value());
            }
            case SdlEvent.GamepadButton data -> {
                MemorySegment value = SdlEventLayout.gbutton(event);
                SdlGamepadButtonEvent.type(value, source.type());
                SdlGamepadButtonEvent.timestamp(value, source.timestamp());
                SdlGamepadButtonEvent.which(value, data.which().value());
                SdlGamepadButtonEvent.button(value, data.button());
                SdlGamepadButtonEvent.down(value, data.down());
            }
            case SdlEvent.GamepadDevice data -> {
                MemorySegment value = SdlEventLayout.gdevice(event);
                SdlGamepadDeviceEvent.type(value, source.type());
                SdlGamepadDeviceEvent.timestamp(value, source.timestamp());
                SdlGamepadDeviceEvent.which(value, data.which().value());
            }
            case SdlEvent.GamepadTouchpad data -> {
                MemorySegment value = SdlEventLayout.gtouchpad(event);
                SdlGamepadTouchpadEvent.type(value, source.type());
                SdlGamepadTouchpadEvent.timestamp(value, source.timestamp());
                SdlGamepadTouchpadEvent.which(value, data.which().value());
                SdlGamepadTouchpadEvent.touchpad(value, data.touchpad());
                SdlGamepadTouchpadEvent.finger(value, data.finger());
                SdlGamepadTouchpadEvent.x(value, data.x());
                SdlGamepadTouchpadEvent.y(value, data.y());
                SdlGamepadTouchpadEvent.pressure(value, data.pressure());
            }
            case SdlEvent.GamepadSensor data -> {
                MemorySegment value = SdlEventLayout.gsensor(event);
                SdlGamepadSensorEvent.type(value, source.type());
                SdlGamepadSensorEvent.timestamp(value, source.timestamp());
                SdlGamepadSensorEvent.which(value, data.which().value());
                SdlGamepadSensorEvent.sensor(value, data.sensor());
                float[] values = data.values();
                for (int i = 0; i < Math.min(3, values.length); i++) {
                    SdlGamepadSensorEvent.data(value, i, values[i]);
                }
                SdlGamepadSensorEvent.sensor_timestamp(value, data.sensorTimestamp());
            }
        }
    }

    private static void commonJoy(MemorySegment value, SdlJoystickId which, SdlEvent source) {
        SdlJoyAxisEvent.type(value, source.type());
        SdlJoyAxisEvent.timestamp(value, source.timestamp());
        SdlJoyAxisEvent.which(value, which.value());
    }

    private static SdlJoystickId id(int value) {
        return new SdlJoystickId(value);
    }
}
