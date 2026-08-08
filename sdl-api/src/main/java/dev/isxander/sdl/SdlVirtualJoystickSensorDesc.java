package dev.isxander.sdl;

/// Describes a virtual joystick sensor.
///
/// @param type the type of this sensor.
/// @param rate the update frequency of this sensor, which may be 0.0f.
///
/// @since This struct is available since SDL 3.2.0.
public record SdlVirtualJoystickSensorDesc(int type, float rate) {
}
