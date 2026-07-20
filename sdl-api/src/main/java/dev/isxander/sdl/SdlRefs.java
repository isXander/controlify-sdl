package dev.isxander.sdl;

/**
 * Small mutable values used for C output parameters.
 */
public final class SdlRefs {
    private SdlRefs() {
    }

    public static final class IntRef {
        public int value;

        public IntRef() {
        }

        public IntRef(int value) {
            this.value = value;
        }
    }

    public static final class ShortRef {
        public short value;

        public ShortRef() {
        }

        public ShortRef(short value) {
            this.value = value;
        }
    }

    public static final class ByteRef {
        public byte value;

        public ByteRef() {
        }

        public ByteRef(byte value) {
            this.value = value;
        }
    }

    public static final class FloatRef {
        public float value;

        public FloatRef() {
        }

        public FloatRef(float value) {
            this.value = value;
        }
    }

    public static final class LongRef {
        public long value;

        public LongRef() {
        }

        public LongRef(long value) {
            this.value = value;
        }
    }

    public static final class PointerRef {
        public SdlPointer value = SdlPointer.NULL;

        public PointerRef() {
        }

        public PointerRef(SdlPointer value) {
            this.value = value;
        }
    }
}
