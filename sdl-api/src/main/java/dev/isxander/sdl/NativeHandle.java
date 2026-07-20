package dev.isxander.sdl;

/**
 * Common contract for SDL's opaque pointer handles.
 */
public interface NativeHandle {
    long address();

    default boolean isNull() {
        return address() == 0;
    }
}
