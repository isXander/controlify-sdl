package dev.isxander.sdl;

import java.util.Arrays;
import java.util.HexFormat;

/**
 * SDL's 16-byte GUID value.
 */
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
