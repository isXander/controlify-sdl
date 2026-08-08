package dev.isxander.sdl;

/// Describes a virtual joystick touchpad.
///
/// @param numFingers the number of simultaneous fingers on this touchpad.
///
/// @since This struct is available since SDL 3.2.0.
public record SdlVirtualJoystickTouchpadDesc(int numFingers) {
    public SdlVirtualJoystickTouchpadDesc {
        if (numFingers < 0 || numFingers > 0xffff) {
            throw new IllegalArgumentException("numFingers must fit in an unsigned 16-bit integer");
        }
    }
}
