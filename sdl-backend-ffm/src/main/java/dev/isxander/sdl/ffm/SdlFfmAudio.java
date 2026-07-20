package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlAudio;
import dev.isxander.sdl.SdlAudioDeviceId;
import dev.isxander.sdl.SdlAudioSpec;
import dev.isxander.sdl.SdlAudioStreamHandle;
import dev.isxander.sdl.SdlIoStreamHandle;
import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlPropertiesId;
import dev.isxander.sdl.SdlAudio.SdlAudioSpecRef;
import dev.isxander.sdl.SdlCallbacks.AudioPostmixCallback;
import dev.isxander.sdl.SdlCallbacks.AudioStreamCallback;
import dev.isxander.sdl.SdlRefs.IntRef;
import dev.isxander.sdl.SdlRefs.PointerRef;
import dev.isxander.sdl.ffm.internal.SdlAudioSpecLayout;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.nio.ByteBuffer;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_FLOAT;
import static java.lang.foreign.ValueLayout.JAVA_INT;

final class SdlFfmAudio implements SdlAudio {
    private static final MethodHandle SDL_GET_NUM_AUDIO_DRIVERS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumAudioDrivers",
            FunctionDescriptor.of(
                    JAVA_INT));
    private static final MethodHandle SDL_GET_AUDIO_DRIVER_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioDriver",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_CURRENT_AUDIO_DRIVER_HANDLE = SdlFfmNative.downcall(
            "SDL_GetCurrentAudioDriver",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_AUDIO_PLAYBACK_DEVICES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioPlaybackDevices",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_AUDIO_DEVICE_ID_POINTER,
                    SdlLayouts.INT_POINTER
            )
    );
    private static final MethodHandle SDL_GET_AUDIO_RECORDING_DEVICES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioRecordingDevices",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_AUDIO_DEVICE_ID_POINTER,
                    SdlLayouts.INT_POINTER
            )
    );
    private static final MethodHandle SDL_GET_AUDIO_DEVICE_NAME_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioDeviceName",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_AUDIO_DEVICE_FORMAT_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioDeviceFormat",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_OPEN_AUDIO_DEVICE_HANDLE = SdlFfmNative.downcall(
            "SDL_OpenAudioDevice",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_SPEC));
    private static final MethodHandle SDL_PAUSE_AUDIO_DEVICE_HANDLE = SdlFfmNative.downcall(
            "SDL_PauseAudioDevice",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_RESUME_AUDIO_DEVICE_HANDLE = SdlFfmNative.downcall(
            "SDL_ResumeAudioDevice",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_AUDIO_DEVICE_PAUSED_HANDLE = SdlFfmNative.downcall(
            "SDL_AudioDevicePaused",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_AUDIO_DEVICE_GAIN_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioDeviceGain",
            FunctionDescriptor.of(
                    JAVA_FLOAT,
                    JAVA_INT));
    private static final MethodHandle SDL_CLOSE_AUDIO_DEVICE_HANDLE = SdlFfmNative.downcall(
            "SDL_CloseAudioDevice",
            FunctionDescriptor.ofVoid(
                    JAVA_INT));
    private static final MethodHandle SDL_BIND_AUDIO_STREAMS_HANDLE = SdlFfmNative.downcall(
            "SDL_BindAudioStreams",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_STREAM_ARRAY,
                    JAVA_INT));
    private static final MethodHandle SDL_BIND_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_BindAudioStream",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_UNBIND_AUDIO_STREAMS_HANDLE = SdlFfmNative.downcall(
            "SDL_UnbindAudioStreams",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_AUDIO_STREAM_ARRAY,
                    JAVA_INT));
    private static final MethodHandle SDL_UNBIND_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_UnbindAudioStream",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_DEVICE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamDevice",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_CREATE_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_CreateAudioStream",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.SDL_AUDIO_SPEC));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamProperties",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_FORMAT_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamFormat",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.SDL_AUDIO_SPEC));
    private static final MethodHandle SDL_SET_AUDIO_STREAM_FORMAT_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioStreamFormat",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.SDL_AUDIO_SPEC));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_FREQUENCY_RATIO_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamFrequencyRatio",
            FunctionDescriptor.of(
                    JAVA_FLOAT,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_SET_AUDIO_STREAM_FREQUENCY_RATIO_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioStreamFrequencyRatio",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    JAVA_FLOAT));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_GAIN_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamGain",
            FunctionDescriptor.of(
                    JAVA_FLOAT,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_SET_AUDIO_STREAM_GAIN_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioStreamGain",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    JAVA_FLOAT));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_INPUT_CHANNEL_MAP_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamInputChannelMap",
            FunctionDescriptor.of(
                    SdlLayouts.INT_POINTER,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_OUTPUT_CHANNEL_MAP_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamOutputChannelMap",
            FunctionDescriptor.of(
                    SdlLayouts.INT_POINTER,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_SET_AUDIO_STREAM_INPUT_CHANNEL_MAP_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioStreamInputChannelMap",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.INT_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_SET_AUDIO_STREAM_OUTPUT_CHANNEL_MAP_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioStreamOutputChannelMap",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.INT_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_PUT_AUDIO_STREAM_DATA_HANDLE = SdlFfmNative.downcall(
            "SDL_PutAudioStreamData",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.VOID_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_DATA_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamData",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.VOID_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_AVAILABLE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamAvailable",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_GET_AUDIO_STREAM_QUEUED_HANDLE = SdlFfmNative.downcall(
            "SDL_GetAudioStreamQueued",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_FLUSH_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_FlushAudioStream",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_CLEAR_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_ClearAudioStream",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_PAUSE_AUDIO_STREAM_DEVICE_HANDLE = SdlFfmNative.downcall(
            "SDL_PauseAudioStreamDevice",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_RESUME_AUDIO_STREAM_DEVICE_HANDLE = SdlFfmNative.downcall(
            "SDL_ResumeAudioStreamDevice",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_LOCK_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_LockAudioStream",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_UNLOCK_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_UnlockAudioStream",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_SET_AUDIO_STREAM_GET_CALLBACK_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioStreamGetCallback",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.SDL_AUDIO_STREAM_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_SET_AUDIO_STREAM_PUT_CALLBACK_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioStreamPutCallback",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_STREAM,
                    SdlLayouts.SDL_AUDIO_STREAM_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_DESTROY_AUDIO_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_DestroyAudioStream",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_AUDIO_STREAM));
    private static final MethodHandle SDL_OPEN_AUDIO_DEVICE_STREAM_HANDLE = SdlFfmNative.downcall(
            "SDL_OpenAudioDeviceStream",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_AUDIO_STREAM,
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.SDL_AUDIO_STREAM_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_SET_AUDIO_POSTMIX_CALLBACK_HANDLE = SdlFfmNative.downcall(
            "SDL_SetAudioPostmixCallback",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_POSTMIX_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_LOAD_WAV_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_LoadWAV_IO",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_IO_STREAM,
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.UINT8_POINTER_POINTER,
                    SdlLayouts.UINT32_POINTER));
    private static final MethodHandle SDL_LOAD_WAV_HANDLE = SdlFfmNative.downcall(
            "SDL_LoadWAV",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.UINT8_POINTER_POINTER,
                    SdlLayouts.UINT32_POINTER));
    private static final MethodHandle SDL_MIX_AUDIO_HANDLE = SdlFfmNative.downcall(
            "SDL_MixAudio",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.UINT8_POINTER,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_INT,
                    JAVA_INT,
                    JAVA_FLOAT));
    private static final MethodHandle SDL_CONVERT_AUDIO_SAMPLES_HANDLE = SdlFfmNative.downcall(
            "SDL_ConvertAudioSamples",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_INT,
                    SdlLayouts.SDL_AUDIO_SPEC,
                    SdlLayouts.UINT8_POINTER_POINTER,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_GET_SILENCE_VALUE_FOR_FORMAT_HANDLE = SdlFfmNative.downcall(
            "SDL_GetSilenceValueForFormat",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));

    @Override
    public boolean SDL_AudioDevicePaused(SdlAudioDeviceId device) {
        try {
            return (boolean) SDL_AUDIO_DEVICE_PAUSED_HANDLE.invokeExact(device.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_BindAudioStream(SdlAudioDeviceId deviceId, SdlAudioStreamHandle stream) {
        try {
            return (boolean) SDL_BIND_AUDIO_STREAM_HANDLE.invokeExact(deviceId.value(), SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_BindAudioStreams(SdlAudioDeviceId deviceId, SdlAudioStreamHandle[] streams) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_BIND_AUDIO_STREAMS_HANDLE.invokeExact(
                deviceId.value(), SdlFfmSupport.handles(streams, arena), streams.length
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_ClearAudioStream(SdlAudioStreamHandle stream) {
        try {
            return (boolean) SDL_CLEAR_AUDIO_STREAM_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_CloseAudioDevice(SdlAudioDeviceId deviceId) {
        try {
            SDL_CLOSE_AUDIO_DEVICE_HANDLE.invokeExact(deviceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_ConvertAudioSamples(SdlAudioSpec srcSpec, ByteBuffer srcData, SdlAudioSpec dstSpec, PointerRef dstData, IntRef dstLength) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeDstData = arena.allocate(SdlLayouts.UINT8_POINTER);
            nativeDstData.set(SdlLayouts.UINT8_POINTER, 0L, dstData.value == null ? MemorySegment.NULL : SdlFfmSupport.segment(dstData.value.address()));
            MemorySegment nativeDstLength = arena.allocate(ValueLayout.JAVA_INT);
            nativeDstLength.set(ValueLayout.JAVA_INT, 0L, dstLength.value);
            boolean converted = (boolean) SDL_CONVERT_AUDIO_SAMPLES_HANDLE.invokeExact(
                SdlFfmSupport.audioSpec(srcSpec, arena),
                MemorySegment.ofBuffer(srcData),
                srcData.remaining(),
                SdlFfmSupport.audioSpec(dstSpec, arena),
                nativeDstData,
                nativeDstLength
            );
            dstData.value = new SdlPointer(nativeDstData.get(SdlLayouts.UINT8_POINTER, 0L).address());
            dstLength.value = nativeDstLength.get(ValueLayout.JAVA_INT, 0L);
            return converted;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlAudioStreamHandle SDL_CreateAudioStream(SdlAudioSpec srcSpec, SdlAudioSpec dstSpec) {
        try (Arena arena = Arena.ofConfined()) {
            return new SdlAudioStreamHandle(
                ((MemorySegment) SDL_CREATE_AUDIO_STREAM_HANDLE.invokeExact(
                        SdlFfmSupport.audioSpec(srcSpec, arena), SdlFfmSupport.audioSpec(dstSpec, arena)
                    )).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_DestroyAudioStream(SdlAudioStreamHandle stream) {
        try {
            SDL_DESTROY_AUDIO_STREAM_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_FlushAudioStream(SdlAudioStreamHandle stream) {
        try {
            return (boolean) SDL_FLUSH_AUDIO_STREAM_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetAudioDeviceFormat(SdlAudioDeviceId deviceId, SdlAudioSpecRef spec, IntRef sampleFrames) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeSpec = SdlAudioSpecLayout.allocate(arena);
            if (spec.value != null) {
                SdlFfmSupport.writeAudioSpec(nativeSpec, spec.value);
            }

            MemorySegment nativeSampleFrames = arena.allocate(ValueLayout.JAVA_INT);
            nativeSampleFrames.set(ValueLayout.JAVA_INT, 0L, sampleFrames.value);
            boolean success = (boolean) SDL_GET_AUDIO_DEVICE_FORMAT_HANDLE.invokeExact(
                deviceId.value(), nativeSpec, nativeSampleFrames
            );
            spec.value = SdlFfmSupport.readAudioSpec(nativeSpec);
            sampleFrames.value = nativeSampleFrames.get(ValueLayout.JAVA_INT, 0L);
            return success;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public float SDL_GetAudioDeviceGain(SdlAudioDeviceId deviceId) {
        try {
            return (float) SDL_GET_AUDIO_DEVICE_GAIN_HANDLE.invokeExact(deviceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetAudioDeviceName(SdlAudioDeviceId deviceId) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_AUDIO_DEVICE_NAME_HANDLE.invokeExact(deviceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetAudioDriver(int index) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_AUDIO_DRIVER_HANDLE.invokeExact(index));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlAudioDeviceId[] SDL_GetAudioPlaybackDevices() {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.audioDeviceArray(arena, SdlFfmAudio::SDL_GetAudioPlaybackDevices);
        }
    }

    @Override
    public SdlAudioDeviceId[] SDL_GetAudioRecordingDevices() {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.audioDeviceArray(arena, SdlFfmAudio::SDL_GetAudioRecordingDevices);
        }
    }

    @Override
    public int SDL_GetAudioStreamAvailable(SdlAudioStreamHandle stream) {
        try {
            return (int) SDL_GET_AUDIO_STREAM_AVAILABLE_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetAudioStreamData(SdlAudioStreamHandle stream, ByteBuffer buffer) {
        try {
            return (int) SDL_GET_AUDIO_STREAM_DATA_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()), MemorySegment.ofBuffer(buffer), buffer.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlAudioDeviceId SDL_GetAudioStreamDevice(SdlAudioStreamHandle stream) {
        try {
            return new SdlAudioDeviceId((int) SDL_GET_AUDIO_STREAM_DEVICE_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetAudioStreamFormat(SdlAudioStreamHandle stream, SdlAudioSpecRef srcSpec, SdlAudioSpecRef dstSpec) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeSrcSpec = SdlAudioSpecLayout.allocate(arena);
            if (srcSpec.value != null) {
                SdlFfmSupport.writeAudioSpec(nativeSrcSpec, srcSpec.value);
            }

            MemorySegment nativeDstSpec = SdlAudioSpecLayout.allocate(arena);
            if (dstSpec.value != null) {
                SdlFfmSupport.writeAudioSpec(nativeDstSpec, dstSpec.value);
            }

            boolean success = (boolean) SDL_GET_AUDIO_STREAM_FORMAT_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()), nativeSrcSpec, nativeDstSpec
            );
            srcSpec.value = SdlFfmSupport.readAudioSpec(nativeSrcSpec);
            dstSpec.value = SdlFfmSupport.readAudioSpec(nativeDstSpec);
            return success;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public float SDL_GetAudioStreamFrequencyRatio(SdlAudioStreamHandle stream) {
        try {
            return (float) SDL_GET_AUDIO_STREAM_FREQUENCY_RATIO_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public float SDL_GetAudioStreamGain(SdlAudioStreamHandle stream) {
        try {
            return (float) SDL_GET_AUDIO_STREAM_GAIN_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int[] SDL_GetAudioStreamInputChannelMap(SdlAudioStreamHandle stream) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment count = arena.allocate(ValueLayout.JAVA_INT);
            return SdlFfmSupport.intArray(
                (MemorySegment) SDL_GET_AUDIO_STREAM_INPUT_CHANNEL_MAP_HANDLE.invokeExact(
                    SdlFfmSupport.segment(stream.address()), count
                ),
                count.get(ValueLayout.JAVA_INT, 0L)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int[] SDL_GetAudioStreamOutputChannelMap(SdlAudioStreamHandle stream) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment count = arena.allocate(ValueLayout.JAVA_INT);
            return SdlFfmSupport.intArray(
                (MemorySegment) SDL_GET_AUDIO_STREAM_OUTPUT_CHANNEL_MAP_HANDLE.invokeExact(
                    SdlFfmSupport.segment(stream.address()), count
                ),
                count.get(ValueLayout.JAVA_INT, 0L)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPropertiesId SDL_GetAudioStreamProperties(SdlAudioStreamHandle stream) {
        try {
            return new SdlPropertiesId((int) SDL_GET_AUDIO_STREAM_PROPERTIES_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetAudioStreamQueued(SdlAudioStreamHandle stream) {
        try {
            return (int) SDL_GET_AUDIO_STREAM_QUEUED_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetCurrentAudioDriver() {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_CURRENT_AUDIO_DRIVER_HANDLE.invokeExact());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetNumAudioDrivers() {
        try {
            return (int) SDL_GET_NUM_AUDIO_DRIVERS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetSilenceValueForFormat(int format) {
        try {
            return (int) SDL_GET_SILENCE_VALUE_FOR_FORMAT_HANDLE.invokeExact(format);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_LoadWAV(String path, SdlAudioSpecRef spec, PointerRef audioBuffer, IntRef audioLength) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeSpec = SdlAudioSpecLayout.allocate(arena);
            if (spec.value != null) {
                SdlFfmSupport.writeAudioSpec(nativeSpec, spec.value);
            }

            MemorySegment nativeAudioBuffer = arena.allocate(SdlLayouts.UINT8_POINTER);
            nativeAudioBuffer.set(SdlLayouts.UINT8_POINTER, 0L, audioBuffer.value == null ? MemorySegment.NULL : SdlFfmSupport.segment(audioBuffer.value.address()));
            MemorySegment nativeAudioLength = arena.allocate(ValueLayout.JAVA_INT);
            nativeAudioLength.set(ValueLayout.JAVA_INT, 0L, audioLength.value);
            boolean success = (boolean) SDL_LOAD_WAV_HANDLE.invokeExact(
                SdlFfmSupport.utf8(path, arena),
                nativeSpec,
                nativeAudioBuffer,
                nativeAudioLength
            );
            spec.value = SdlFfmSupport.readAudioSpec(nativeSpec);
            audioBuffer.value = new SdlPointer(nativeAudioBuffer.get(SdlLayouts.UINT8_POINTER, 0L).address());
            audioLength.value = nativeAudioLength.get(ValueLayout.JAVA_INT, 0L);
            return success;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_LoadWAV_IO(SdlIoStreamHandle src, boolean closeio, SdlAudioSpecRef spec, PointerRef audioBuffer, IntRef audioLength) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeSpec = SdlAudioSpecLayout.allocate(arena);
            if (spec.value != null) {
                SdlFfmSupport.writeAudioSpec(nativeSpec, spec.value);
            }

            MemorySegment nativeAudioBuffer = arena.allocate(SdlLayouts.UINT8_POINTER);
            nativeAudioBuffer.set(SdlLayouts.UINT8_POINTER, 0L, audioBuffer.value == null ? MemorySegment.NULL : SdlFfmSupport.segment(audioBuffer.value.address()));
            MemorySegment nativeAudioLength = arena.allocate(ValueLayout.JAVA_INT);
            nativeAudioLength.set(ValueLayout.JAVA_INT, 0L, audioLength.value);
            boolean success = (boolean) SDL_LOAD_WAV_IO_HANDLE.invokeExact(
                SdlFfmSupport.segment(src.address()),
                closeio,
                nativeSpec,
                nativeAudioBuffer,
                nativeAudioLength
            );
            spec.value = SdlFfmSupport.readAudioSpec(nativeSpec);
            audioBuffer.value = new SdlPointer(nativeAudioBuffer.get(SdlLayouts.UINT8_POINTER, 0L).address());
            audioLength.value = nativeAudioLength.get(ValueLayout.JAVA_INT, 0L);
            return success;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_LockAudioStream(SdlAudioStreamHandle stream) {
        try {
            return (boolean) SDL_LOCK_AUDIO_STREAM_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_MixAudio(ByteBuffer destination, ByteBuffer source, int format, int length, float volume) {
        try {
            return (boolean) SDL_MIX_AUDIO_HANDLE.invokeExact(
                MemorySegment.ofBuffer(destination), MemorySegment.ofBuffer(source), format, length, volume
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlAudioDeviceId SDL_OpenAudioDevice(SdlAudioDeviceId deviceId, SdlAudioSpec spec) {
        try (Arena arena = Arena.ofConfined()) {
            return new SdlAudioDeviceId(
                (int) SDL_OPEN_AUDIO_DEVICE_HANDLE.invokeExact(deviceId.value(), SdlFfmSupport.audioSpec(spec, arena))
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlAudioStreamHandle SDL_OpenAudioDeviceStream(SdlAudioDeviceId deviceId, SdlAudioSpec spec, AudioStreamCallback callback, SdlPointer userdata) {
        try (Arena arena = Arena.ofConfined()) {
            return new SdlAudioStreamHandle(
                ((MemorySegment) SDL_OPEN_AUDIO_DEVICE_STREAM_HANDLE.invokeExact(
                        deviceId.value(),
                        SdlFfmSupport.audioSpec(spec, arena),
                        SdlFfmSupport.callback(callback),
                        SdlFfmSupport.segment(userdata.address())
                    )).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_PauseAudioDevice(SdlAudioDeviceId device) {
        try {
            return (boolean) SDL_PAUSE_AUDIO_DEVICE_HANDLE.invokeExact(device.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_PauseAudioStreamDevice(SdlAudioStreamHandle stream) {
        try {
            return (boolean) SDL_PAUSE_AUDIO_STREAM_DEVICE_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_PutAudioStreamData(SdlAudioStreamHandle stream, ByteBuffer buffer) {
        try {
            return (boolean) SDL_PUT_AUDIO_STREAM_DATA_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()), MemorySegment.ofBuffer(buffer), buffer.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_ResumeAudioDevice(SdlAudioDeviceId device) {
        try {
            return (boolean) SDL_RESUME_AUDIO_DEVICE_HANDLE.invokeExact(device.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_ResumeAudioStreamDevice(SdlAudioStreamHandle stream) {
        try {
            return (boolean) SDL_RESUME_AUDIO_STREAM_DEVICE_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioPostmixCallback(SdlAudioDeviceId deviceId, AudioPostmixCallback callback, SdlPointer userdata) {
        try {
            return (boolean) SDL_SET_AUDIO_POSTMIX_CALLBACK_HANDLE.invokeExact(
                deviceId.value(), SdlFfmSupport.callback(callback), SdlFfmSupport.segment(userdata.address())
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioStreamFormat(SdlAudioStreamHandle stream, SdlAudioSpec srcSpec, SdlAudioSpec dstSpec) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_AUDIO_STREAM_FORMAT_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()),
                SdlFfmSupport.audioSpec(srcSpec, arena),
                SdlFfmSupport.audioSpec(dstSpec, arena)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioStreamFrequencyRatio(SdlAudioStreamHandle stream, float ratio) {
        try {
            return (boolean) SDL_SET_AUDIO_STREAM_FREQUENCY_RATIO_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()), ratio);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioStreamGain(SdlAudioStreamHandle stream, float gain) {
        try {
            return (boolean) SDL_SET_AUDIO_STREAM_GAIN_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()), gain);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioStreamGetCallback(SdlAudioStreamHandle stream, AudioStreamCallback callback, SdlPointer userdata) {
        try {
            return (boolean) SDL_SET_AUDIO_STREAM_GET_CALLBACK_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()),
                SdlFfmSupport.callback(callback),
                SdlFfmSupport.segment(userdata.address())
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioStreamInputChannelMap(SdlAudioStreamHandle stream, int[] map) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_AUDIO_STREAM_INPUT_CHANNEL_MAP_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()), SdlFfmSupport.ints(map, arena), map.length
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioStreamOutputChannelMap(SdlAudioStreamHandle stream, int[] map) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_AUDIO_STREAM_OUTPUT_CHANNEL_MAP_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()), SdlFfmSupport.ints(map, arena), map.length
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetAudioStreamPutCallback(SdlAudioStreamHandle stream, AudioStreamCallback callback, SdlPointer userdata) {
        try {
            return (boolean) SDL_SET_AUDIO_STREAM_PUT_CALLBACK_HANDLE.invokeExact(
                SdlFfmSupport.segment(stream.address()),
                SdlFfmSupport.callback(callback),
                SdlFfmSupport.segment(userdata.address())
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_UnbindAudioStream(SdlAudioStreamHandle stream) {
        try {
            SDL_UNBIND_AUDIO_STREAM_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_UnbindAudioStreams(SdlAudioStreamHandle[] streams) {
        try (Arena arena = Arena.ofConfined()) {
            SDL_UNBIND_AUDIO_STREAMS_HANDLE.invokeExact(SdlFfmSupport.handles(streams, arena), streams.length);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_UnlockAudioStream(SdlAudioStreamHandle stream) {
        try {
            return (boolean) SDL_UNLOCK_AUDIO_STREAM_HANDLE.invokeExact(SdlFfmSupport.segment(stream.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_GetAudioPlaybackDevices(MemorySegment count) {
        try {
            return (MemorySegment) SDL_GET_AUDIO_PLAYBACK_DEVICES_HANDLE.invokeExact(count);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_GetAudioRecordingDevices(MemorySegment count) {
        try {
            return (MemorySegment) SDL_GET_AUDIO_RECORDING_DEVICES_HANDLE.invokeExact(count);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
