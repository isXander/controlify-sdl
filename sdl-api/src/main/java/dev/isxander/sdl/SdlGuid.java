package dev.isxander.sdl;

import java.util.Arrays;
import java.util.HexFormat;

/// An SDL_GUID is a 128-bit identifier for an input device that identifies
/// that device across runs of SDL programs on the same platform.
///
/// If the device is detached and then re-attached to a different port, or if
/// the base system is rebooted, the device should still report the same GUID.
///
/// GUIDs are as precise as possible but are not guaranteed to distinguish
/// physically distinct but equivalent devices. For example, two game
/// controllers from the same vendor with the same product ID and revision may
/// have the same GUID.
///
/// GUIDs may be platform-dependent (i.e., the same device may report different
/// GUIDs on different operating systems).
///
/// @since This struct is available since SDL 3.2.0.
public record SdlGuid(byte[] data) {
    public static final int BYTE_SIZE = 16;

    public SdlGuid(byte[] data) {
        if (data.length != BYTE_SIZE) {
            throw new IllegalArgumentException("An SDL GUID must contain exactly 16 bytes");
        }
        this.data = data.clone();
    }

    @Override
    public byte[] data() {
        return data.clone();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SdlGuid guid && Arrays.equals(data, guid.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return HexFormat.of().formatHex(data);
    }
}
