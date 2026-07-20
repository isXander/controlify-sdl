package dev.isxander.sdl;

import java.nio.ByteBuffer;


public final class SdlCallbacks {
    private SdlCallbacks() {
    }

    @FunctionalInterface
    public interface CleanupPropertyCallback {
        void cleanup(SdlPointer userdata, SdlPointer value);
    }

    @FunctionalInterface
    public interface EnumeratePropertiesCallback {
        void accept(SdlPointer userdata, SdlPropertiesId properties, String name);
    }

    @FunctionalInterface
    public interface EventFilter {
        boolean filter(SdlPointer userdata, SdlEvent event);
    }

    @FunctionalInterface
    public interface AudioStreamCallback {
        void invoke(SdlPointer userdata, SdlAudioStreamHandle stream, int additionalAmount, int totalAmount);
    }

    @FunctionalInterface
    public interface AudioPostmixCallback {
        void invoke(SdlPointer userdata, SdlAudioSpec spec, ByteBuffer buffer);
    }

    @FunctionalInterface
    public interface IoSizeCallback {
        long size(SdlPointer userdata);
    }

    @FunctionalInterface
    public interface IoSeekCallback {
        long seek(SdlPointer userdata, long offset, int whence);
    }

    @FunctionalInterface
    public interface IoReadCallback {
        long read(SdlPointer userdata, ByteBuffer destination, long statusAddress);
    }

    @FunctionalInterface
    public interface IoWriteCallback {
        long write(SdlPointer userdata, ByteBuffer source, long statusAddress);
    }

    @FunctionalInterface
    public interface IoFlushCallback {
        boolean flush(SdlPointer userdata, long statusAddress);
    }

    @FunctionalInterface
    public interface IoCloseCallback {
        boolean close(SdlPointer userdata);
    }
}
