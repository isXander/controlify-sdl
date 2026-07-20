package dev.isxander.sdl;

/**
 * A backend-neutral native address. The zero address represents C {@code NULL}.
 */
public record SdlPointer(long address) {
    public static final SdlPointer NULL = new SdlPointer(0);

    public boolean isNull() {
        return address == 0;
    }
}
