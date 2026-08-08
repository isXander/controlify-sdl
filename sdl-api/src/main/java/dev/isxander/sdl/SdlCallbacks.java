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

    /// Called when a virtual joystick's state should be updated.
    @FunctionalInterface
    public interface VirtualJoystickUpdateCallback {
        void update(SdlPointer userdata);
    }

    /// Called when a virtual joystick's player index is set.
    @FunctionalInterface
    public interface VirtualJoystickSetPlayerIndexCallback {
        void setPlayerIndex(SdlPointer userdata, int playerIndex);
    }

    /// Implements rumble for a virtual joystick.
    @FunctionalInterface
    public interface VirtualJoystickRumbleCallback {
        boolean rumble(SdlPointer userdata, short lowFrequencyRumble, short highFrequencyRumble);
    }

    /// Implements trigger rumble for a virtual joystick.
    @FunctionalInterface
    public interface VirtualJoystickRumbleTriggersCallback {
        boolean rumbleTriggers(SdlPointer userdata, short leftRumble, short rightRumble);
    }

    /// Implements LED updates for a virtual joystick.
    @FunctionalInterface
    public interface VirtualJoystickSetLedCallback {
        boolean setLed(SdlPointer userdata, byte red, byte green, byte blue);
    }

    /// Implements joystick-specific effect packets for a virtual joystick.
    @FunctionalInterface
    public interface VirtualJoystickSendEffectCallback {
        boolean sendEffect(SdlPointer userdata, ByteBuffer data);
    }

    /// Implements sensor enablement for a virtual joystick.
    @FunctionalInterface
    public interface VirtualJoystickSetSensorsEnabledCallback {
        boolean setSensorsEnabled(SdlPointer userdata, boolean enabled);
    }

    /// Cleans up a virtual joystick's userdata when the joystick is detached.
    @FunctionalInterface
    public interface VirtualJoystickCleanupCallback {
        void cleanup(SdlPointer userdata);
    }
}
