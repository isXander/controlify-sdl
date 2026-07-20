package dev.isxander.sdl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import dev.isxander.sdl.SdlCallbacks.AudioPostmixCallback;
import dev.isxander.sdl.SdlCallbacks.AudioStreamCallback;
import dev.isxander.sdl.SdlRefs.IntRef;
import dev.isxander.sdl.SdlRefs.PointerRef;

public interface SdlAudio {
    int SDL_AUDIO_U8 = 0x0008;
    int SDL_AUDIO_S8 = 0x8008;
    int SDL_AUDIO_S16LE = 0x8010;
    int SDL_AUDIO_S16BE = 0x9010;
    int SDL_AUDIO_S32LE = 0x8020;
    int SDL_AUDIO_S32BE = 0x9020;
    int SDL_AUDIO_F32LE = 0x8120;
    int SDL_AUDIO_F32BE = 0x9120;
    int SDL_AUDIO_S16 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
            ? SDL_AUDIO_S16LE : SDL_AUDIO_S16BE;
    int SDL_AUDIO_S32 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
            ? SDL_AUDIO_S32LE : SDL_AUDIO_S32BE;
    int SDL_AUDIO_F32 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
            ? SDL_AUDIO_F32LE : SDL_AUDIO_F32BE;
    SdlAudioDeviceId SDL_AUDIO_DEVICE_DEFAULT_PLAYBACK = new SdlAudioDeviceId(-1);
    SdlAudioDeviceId SDL_AUDIO_DEVICE_DEFAULT_RECORDING = new SdlAudioDeviceId(-2);
    int SDL_MIX_MAXVOLUME = 128;

    int SDL_GetNumAudioDrivers();

    String SDL_GetAudioDriver(int index);

    String SDL_GetCurrentAudioDriver();

    SdlAudioDeviceId[] SDL_GetAudioPlaybackDevices();

    SdlAudioDeviceId[] SDL_GetAudioRecordingDevices();

    String SDL_GetAudioDeviceName(SdlAudioDeviceId deviceId);

    boolean SDL_GetAudioDeviceFormat(SdlAudioDeviceId deviceId, SdlAudioSpecRef spec, IntRef sampleFrames);

    SdlAudioDeviceId SDL_OpenAudioDevice(SdlAudioDeviceId deviceId, SdlAudioSpec spec);

    boolean SDL_PauseAudioDevice(SdlAudioDeviceId device);

    boolean SDL_ResumeAudioDevice(SdlAudioDeviceId device);

    boolean SDL_AudioDevicePaused(SdlAudioDeviceId device);

    float SDL_GetAudioDeviceGain(SdlAudioDeviceId deviceId);

    void SDL_CloseAudioDevice(SdlAudioDeviceId deviceId);

    boolean SDL_BindAudioStreams(SdlAudioDeviceId deviceId, SdlAudioStreamHandle[] streams);

    boolean SDL_BindAudioStream(SdlAudioDeviceId deviceId, SdlAudioStreamHandle stream);

    void SDL_UnbindAudioStreams(SdlAudioStreamHandle[] streams);

    void SDL_UnbindAudioStream(SdlAudioStreamHandle stream);

    SdlAudioDeviceId SDL_GetAudioStreamDevice(SdlAudioStreamHandle stream);

    SdlAudioStreamHandle SDL_CreateAudioStream(SdlAudioSpec srcSpec, SdlAudioSpec dstSpec);

    SdlPropertiesId SDL_GetAudioStreamProperties(SdlAudioStreamHandle stream);

    boolean SDL_GetAudioStreamFormat(SdlAudioStreamHandle stream, SdlAudioSpecRef srcSpec, SdlAudioSpecRef dstSpec);

    boolean SDL_SetAudioStreamFormat(SdlAudioStreamHandle stream, SdlAudioSpec srcSpec, SdlAudioSpec dstSpec);

    float SDL_GetAudioStreamFrequencyRatio(SdlAudioStreamHandle stream);

    boolean SDL_SetAudioStreamFrequencyRatio(SdlAudioStreamHandle stream, float ratio);

    float SDL_GetAudioStreamGain(SdlAudioStreamHandle stream);

    boolean SDL_SetAudioStreamGain(SdlAudioStreamHandle stream, float gain);

    int[] SDL_GetAudioStreamInputChannelMap(SdlAudioStreamHandle stream);

    int[] SDL_GetAudioStreamOutputChannelMap(SdlAudioStreamHandle stream);

    boolean SDL_SetAudioStreamInputChannelMap(SdlAudioStreamHandle stream, int[] map);

    boolean SDL_SetAudioStreamOutputChannelMap(SdlAudioStreamHandle stream, int[] map);

    boolean SDL_PutAudioStreamData(SdlAudioStreamHandle stream, ByteBuffer buffer);

    int SDL_GetAudioStreamData(SdlAudioStreamHandle stream, ByteBuffer buffer);

    int SDL_GetAudioStreamAvailable(SdlAudioStreamHandle stream);

    int SDL_GetAudioStreamQueued(SdlAudioStreamHandle stream);

    boolean SDL_FlushAudioStream(SdlAudioStreamHandle stream);

    boolean SDL_ClearAudioStream(SdlAudioStreamHandle stream);

    boolean SDL_PauseAudioStreamDevice(SdlAudioStreamHandle stream);

    boolean SDL_ResumeAudioStreamDevice(SdlAudioStreamHandle stream);

    boolean SDL_LockAudioStream(SdlAudioStreamHandle stream);

    boolean SDL_UnlockAudioStream(SdlAudioStreamHandle stream);

    boolean SDL_SetAudioStreamGetCallback(SdlAudioStreamHandle stream, AudioStreamCallback callback, SdlPointer userdata);

    boolean SDL_SetAudioStreamPutCallback(SdlAudioStreamHandle stream, AudioStreamCallback callback, SdlPointer userdata);

    void SDL_DestroyAudioStream(SdlAudioStreamHandle stream);

    SdlAudioStreamHandle SDL_OpenAudioDeviceStream(SdlAudioDeviceId deviceId, SdlAudioSpec spec,
                                                   AudioStreamCallback callback, SdlPointer userdata);

    boolean SDL_SetAudioPostmixCallback(SdlAudioDeviceId deviceId, AudioPostmixCallback callback, SdlPointer userdata);

    boolean SDL_LoadWAV_IO(SdlIoStreamHandle src, boolean closeio, SdlAudioSpecRef spec,
                           PointerRef audioBuffer, IntRef audioLength);

    boolean SDL_LoadWAV(String path, SdlAudioSpecRef spec, PointerRef audioBuffer, IntRef audioLength);

    boolean SDL_MixAudio(ByteBuffer destination, ByteBuffer source, int format, int length, float volume);

    boolean SDL_ConvertAudioSamples(SdlAudioSpec srcSpec, ByteBuffer srcData,
                                    SdlAudioSpec dstSpec, PointerRef dstData, IntRef dstLength);

    int SDL_GetSilenceValueForFormat(int format);

    final class SdlAudioSpecRef {
        public SdlAudioSpec value;

        public SdlAudioSpecRef() {
        }

        public SdlAudioSpecRef(SdlAudioSpec value) {
            this.value = value;
        }
    }
}
