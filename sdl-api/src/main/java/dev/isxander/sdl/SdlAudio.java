package dev.isxander.sdl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import dev.isxander.sdl.SdlCallbacks.AudioPostmixCallback;
import dev.isxander.sdl.SdlCallbacks.AudioStreamCallback;
import dev.isxander.sdl.SdlRefs.IntRef;
import dev.isxander.sdl.SdlRefs.PointerRef;

public interface SdlAudio {
    /// Unsigned 8-bit samples
    int SDL_AUDIO_U8 = 0x0008;
    /// Signed 8-bit samples
    int SDL_AUDIO_S8 = 0x8008;
    /// Signed 16-bit samples
    int SDL_AUDIO_S16LE = 0x8010;
    /// As above, but big-endian byte order
    int SDL_AUDIO_S16BE = 0x9010;
    /// 32-bit integer samples
    int SDL_AUDIO_S32LE = 0x8020;
    /// As above, but big-endian byte order
    int SDL_AUDIO_S32BE = 0x9020;
    /// 32-bit floating point samples
    int SDL_AUDIO_F32LE = 0x8120;
    /// As above, but big-endian byte order
    int SDL_AUDIO_F32BE = 0x9120;
    int SDL_AUDIO_S16 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
            ? SDL_AUDIO_S16LE : SDL_AUDIO_S16BE;
    int SDL_AUDIO_S32 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
            ? SDL_AUDIO_S32LE : SDL_AUDIO_S32BE;
    int SDL_AUDIO_F32 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
            ? SDL_AUDIO_F32LE : SDL_AUDIO_F32BE;
    /// A value used to request a default playback audio device.
    ///
    /// Several functions that require an SDL_AudioDeviceID will accept this value
    /// to signify the app just wants the system to choose a default device instead
    /// of the app providing a specific one.
    ///
    /// @since This macro is available since SDL 3.2.0.
    SdlAudioDeviceId SDL_AUDIO_DEVICE_DEFAULT_PLAYBACK = new SdlAudioDeviceId(-1);
    /// A value used to request a default recording audio device.
    ///
    /// Several functions that require an SDL_AudioDeviceID will accept this value
    /// to signify the app just wants the system to choose a default device instead
    /// of the app providing a specific one.
    ///
    /// @since This macro is available since SDL 3.2.0.
    SdlAudioDeviceId SDL_AUDIO_DEVICE_DEFAULT_RECORDING = new SdlAudioDeviceId(-2);
    int SDL_MIX_MAXVOLUME = 128;

    /// Get the number of built-in audio drivers.
    ///
    /// @return the number of built-in audio drivers.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetAudioDriver`.
    int SDL_GetNumAudioDrivers();

    /// Use this function to get the name of a built in audio driver.
    ///
    /// @param index the index of the audio driver; the value ranges from 0 to
    ///              SDL_GetNumAudioDrivers() - 1.
    /// @return the name of the audio driver at the requested index, or NULL if an
    ///         invalid index was specified.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_GetNumAudioDrivers`.
    String SDL_GetAudioDriver(int index);

    /// Get the name of the current audio driver.
    ///
    /// @return the name of the current audio driver or NULL if no driver has been
    ///         initialized.
    ///
    /// @since This function is available since SDL 3.2.0.
    String SDL_GetCurrentAudioDriver();

    /// Get a list of currently-connected audio playback devices.
    ///
    /// @return a 0 terminated array of audio device instance IDs.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlAudioDeviceId[] SDL_GetAudioPlaybackDevices();

    /// Get a list of currently-connected audio recording devices.
    ///
    /// @return a 0 terminated array of audio device instance IDs.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlAudioDeviceId[] SDL_GetAudioRecordingDevices();

    /// Get the human-readable name of a specific audio device.
    ///
    /// @param deviceId the instance ID of the device to query.
    /// @return the name of the audio device, or NULL on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    String SDL_GetAudioDeviceName(SdlAudioDeviceId deviceId);

    /// Get the current audio format of a specific audio device.
    ///
    /// @param deviceId the instance ID of the device to query.
    /// @param spec a pointer to an SDL_AudioSpec to be filled in with the audio
    ///             format of the device.
    /// @param sampleFrames a pointer to the number of sample frames to be used
    ///                     with the audio device's callback.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_GetAudioDeviceFormat(SdlAudioDeviceId deviceId, SdlAudioSpecRef spec, IntRef sampleFrames);

    /// Open a specific audio device.
    ///
    /// @param deviceId the ID of the device to open, or an SDL_AUDIO_DEVICE_DEFAULT_*
    ///                 constant.
    /// @param spec the requested device configuration.
    /// @return the device ID that is used to identify the new audio device.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlAudioDeviceId SDL_OpenAudioDevice(SdlAudioDeviceId deviceId, SdlAudioSpec spec);

    /// Use this function to pause audio playback on a specified device.
    ///
    /// @param device a device opened by SDL_OpenAudioDevice().
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_PauseAudioDevice(SdlAudioDeviceId device);

    /// Use this function to unpause audio playback on a specified device.
    ///
    /// @param device a device opened by SDL_OpenAudioDevice().
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_ResumeAudioDevice(SdlAudioDeviceId device);

    /// Use this function to query if an audio device is paused.
    ///
    /// @param device a device opened by SDL_OpenAudioDevice().
    /// @return true if device is valid and paused, false otherwise.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_AudioDevicePaused(SdlAudioDeviceId device);

    /// Get the gain of an audio device.
    ///
    /// The gain of a device is its volume; a larger gain means a louder output,
    /// with a gain of zero being silence.
    ///
    /// Audio devices default to a gain of 1.0f (no change in output).
    ///
    /// Physical devices may not have their gain changed, only logical devices, and
    /// this function will always return -1.0f when used on physical devices.
    ///
    /// @param deviceId the audio device to query.
    /// @return the gain of the device or -1.0f on failure; call SDL_GetError()
    ///         for more information.
    ///
    /// Thread safety: It is safe to call this function from any thread.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_SetAudioDeviceGain`.
    float SDL_GetAudioDeviceGain(SdlAudioDeviceId deviceId);

    /// Close a previously-opened audio device.
    ///
    /// The application should close open audio devices once they are no longer
    /// needed.
    ///
    /// This function may block briefly while pending audio data is played by the
    /// hardware, so that applications don't drop the last buffer of data they
    /// supplied if terminating immediately afterwards.
    void SDL_CloseAudioDevice(SdlAudioDeviceId deviceId);

    /// Bind a list of audio streams to an audio device.
    ///
    /// Audio data will flow through any bound streams. For a playback device, data
    /// for all bound streams will be mixed together and fed to the device. For a
    /// recording device, a copy of recorded data will be provided to each bound
    /// stream.
    ///
    /// Audio streams can only be bound to an open device. This operation is
    /// atomic--all streams bound in the same call will start processing at the
    /// same time, so they can stay in sync. Also: either all streams will be bound
    /// or none of them will be.
    ///
    /// It is an error to bind an already-bound stream; it must be explicitly
    /// unbound first.
    ///
    /// Binding a stream to a device will set its output format for playback
    /// devices, and its input format for recording devices, so they match the
    /// device's settings. The caller is welcome to change the other end of the
    /// stream's format at any time with SDL_SetAudioStreamFormat(). If the other
    /// end of the stream's format has never been set (the audio stream was created
    /// with a NULL audio spec), this function will set it to match the device
    /// end's format.
    boolean SDL_BindAudioStreams(SdlAudioDeviceId deviceId, SdlAudioStreamHandle[] streams);

    /// Bind a single audio stream to an audio device.
    ///
    /// This is a convenience function, equivalent to calling
    /// `SDL_BindAudioStreams(devid, &stream, 1)`.
    boolean SDL_BindAudioStream(SdlAudioDeviceId deviceId, SdlAudioStreamHandle stream);

    /// Unbind a list of audio streams from their audio devices.
    ///
    /// The streams being unbound do not all have to be on the same device. All
    /// streams on the same device will be unbound atomically (data will stop
    /// flowing through all unbound streams on the same device at the same time).
    ///
    /// Unbinding a stream that isn't bound to a device is a legal no-op.
    void SDL_UnbindAudioStreams(SdlAudioStreamHandle[] streams);

    /// Unbind a single audio stream from its audio device.
    ///
    /// This is a convenience function, equivalent to calling
    /// `SDL_UnbindAudioStreams(&stream, 1)`.
    void SDL_UnbindAudioStream(SdlAudioStreamHandle stream);

    /// Query an audio stream for its currently-bound device.
    ///
    /// This reports the logical audio device that an audio stream is currently
    /// bound to.
    ///
    /// If not bound, or invalid, this returns zero, which is not a valid device
    /// ID.
    SdlAudioDeviceId SDL_GetAudioStreamDevice(SdlAudioStreamHandle stream);

    /// Create a new audio stream.
    ///
    /// Note that `src_spec` or `dst_spec` may be NULL, but any attempts to
    /// put or get data from an audio stream will fail until it has valid
    /// specs assigned to both ends of the stream. Specs can be assigned later
    /// through SDL_SetAudioStreamFormat(), or binding the stream to an audio
    /// device (which will set the format of only the input or output,
    /// depending on what kind of device the stream was bound to).
    SdlAudioStreamHandle SDL_CreateAudioStream(SdlAudioSpec srcSpec, SdlAudioSpec dstSpec);

    /// Get the properties associated with an audio stream.
    ///
    /// The application can hang any data it wants here, but the following
    /// properties are understood by SDL:
    ///
    /// - `SDL_PROP_AUDIOSTREAM_AUTO_CLEANUP_BOOLEAN`: if true (the default), the
    ///   stream be automatically cleaned up when the audio subsystem quits. If set
    ///   to false, the streams will persist beyond that. This property is ignored
    ///   for streams created through SDL_OpenAudioDeviceStream(), and will always
    ///   be cleaned up. Streams that are not cleaned up will still be unbound from
    ///   devices when the audio subsystem quits. This property was added in SDL
    ///   3.4.0.
    SdlPropertiesId SDL_GetAudioStreamProperties(SdlAudioStreamHandle stream);

    /// Query the current format of an audio stream.
    boolean SDL_GetAudioStreamFormat(SdlAudioStreamHandle stream, SdlAudioSpecRef srcSpec, SdlAudioSpecRef dstSpec);

    /// Change the input and output formats of an audio stream.
    ///
    /// Future calls to and SDL_GetAudioStreamAvailable and SDL_GetAudioStreamData
    /// will reflect the new format, and future calls to SDL_PutAudioStreamData
    /// must provide data in the new input formats.
    ///
    /// Data that was previously queued in the stream will still be operated on in
    /// the format that was current when it was added, which is to say you can put
    /// the end of a sound file in one format to a stream, change formats for the
    /// next sound file, and start putting that new data while the previous sound
    /// file is still queued, and everything will still play back correctly.
    ///
    /// If a stream is bound to a device, then the format of the side of the stream
    /// bound to a device cannot be changed (src_spec for recording devices,
    /// dst_spec for playback devices). Attempts to make a change to this side will
    /// be ignored, but this will not report an error. The other side's format can
    /// be changed.
    ///
    /// `src_spec` and `dst_spec` may each be NULL; a NULL spec signals not to
    /// change the current format for that side of the stream.
    boolean SDL_SetAudioStreamFormat(SdlAudioStreamHandle stream, SdlAudioSpec srcSpec, SdlAudioSpec dstSpec);

    /// Get the frequency ratio of an audio stream.
    float SDL_GetAudioStreamFrequencyRatio(SdlAudioStreamHandle stream);

    /// Change the frequency ratio of an audio stream.
    ///
    /// The frequency ratio is used to adjust the rate at which input data is
    /// consumed. Changing this effectively modifies the speed and pitch of the
    /// audio. A value greater than 1.0f will play the audio faster, and at a
    /// higher pitch. A value less than 1.0f will play the audio slower, and at a
    /// lower pitch. 1.0f means play at normal speed.
    ///
    /// This is applied during SDL_GetAudioStreamData, and can be continuously
    /// changed to create various effects.
    boolean SDL_SetAudioStreamFrequencyRatio(SdlAudioStreamHandle stream, float ratio);

    /// Get the gain of an audio stream.
    ///
    /// The gain of a stream is its volume; a larger gain means a louder output,
    /// with a gain of zero being silence.
    ///
    /// Audio streams default to a gain of 1.0f (no change in output).
    float SDL_GetAudioStreamGain(SdlAudioStreamHandle stream);

    /// Change the gain of an audio stream.
    ///
    /// The gain of a stream is its volume; a larger gain means a louder output,
    /// with a gain of zero being silence.
    ///
    /// Audio streams default to a gain of 1.0f (no change in output).
    ///
    /// This is applied during SDL_GetAudioStreamData, and can be continuously
    /// changed to create various effects.
    boolean SDL_SetAudioStreamGain(SdlAudioStreamHandle stream, float gain);

    /// Get the current input channel map of an audio stream.
    ///
    /// Channel maps are optional; most things do not need them, instead passing
    /// data in the [order that SDL expects](CategoryAudio#channel-layouts).
    ///
    /// Audio streams default to no remapping applied. This is represented by
    /// returning NULL, and does not signify an error.
    int[] SDL_GetAudioStreamInputChannelMap(SdlAudioStreamHandle stream);

    /// Get the current output channel map of an audio stream.
    ///
    /// Channel maps are optional; most things do not need them, instead passing
    /// data in the [order that SDL expects](CategoryAudio#channel-layouts).
    ///
    /// Audio streams default to no remapping applied. This is represented by
    /// returning NULL, and does not signify an error.
    int[] SDL_GetAudioStreamOutputChannelMap(SdlAudioStreamHandle stream);

    /// Set the current input channel map of an audio stream.
    ///
    /// Channel maps are optional; most things do not need them, instead passing
    /// data in the [order that SDL expects](CategoryAudio#channel-layouts).
    ///
    /// The input channel map reorders data that is added to a stream via
    /// SDL_PutAudioStreamData. Future calls to SDL_PutAudioStreamData must provide
    /// data in the new channel order.
    ///
    /// Each item in the array represents an input channel, and its value is the
    /// channel that it should be remapped to. To reverse a stereo signal's left
    /// and right values, you'd have an array of `{ 1, 0 }`. It is legal to remap
    /// multiple channels to the same thing, so `{ 1, 1 }` would duplicate the
    /// right channel to both channels of a stereo signal. An element in the
    /// channel map set to -1 instead of a valid channel will mute that channel,
    /// setting it to a silence value.
    ///
    /// You cannot change the number of channels through a channel map, just
    /// reorder/mute them.
    ///
    /// Data that was previously queued in the stream will still be operated on in
    /// the order that was current when it was added, which is to say you can put
    /// the end of a sound file in one order to a stream, change orders for the
    /// next sound file, and start putting that new data while the previous sound
    /// file is still queued, and everything will still play back correctly.
    ///
    /// Audio streams default to no remapping applied. Passing a NULL channel map
    /// is legal, and turns off remapping.
    ///
    /// SDL will copy the channel map; the caller does not have to save this array
    /// after this call.
    ///
    /// If `count` is not equal to the current number of channels in the audio
    /// stream's format, this will fail. This is a safety measure to make sure a
    /// race condition hasn't changed the format while this call is setting the
    /// channel map.
    ///
    /// Unlike attempting to change the stream's format, the input channel map on a
    /// stream bound to a recording device is permitted to change at any time; any
    /// data added to the stream from the device after this call will have the new
    /// mapping, but previously-added data will still have the prior mapping.
    boolean SDL_SetAudioStreamInputChannelMap(SdlAudioStreamHandle stream, int[] map);

    /// Set the current output channel map of an audio stream.
    ///
    /// Channel maps are optional; most things do not need them, instead passing
    /// data in the [order that SDL expects](CategoryAudio#channel-layouts).
    ///
    /// The output channel map reorders data that is leaving a stream via
    /// SDL_GetAudioStreamData.
    ///
    /// Each item in the array represents an input channel, and its value is the
    /// channel that it should be remapped to. To reverse a stereo signal's left
    /// and right values, you'd have an array of `{ 1, 0 }`. It is legal to remap
    /// multiple channels to the same thing, so `{ 1, 1 }` would duplicate the
    /// right channel to both channels of a stereo signal. An element in the
    /// channel map set to -1 instead of a valid channel will mute that channel,
    /// setting it to a silence value.
    ///
    /// You cannot change the number of channels through a channel map, just
    /// reorder/mute them.
    ///
    /// The output channel map can be changed at any time, as output remapping is
    /// applied during SDL_GetAudioStreamData.
    ///
    /// Audio streams default to no remapping applied. Passing a NULL channel map
    /// is legal, and turns off remapping.
    ///
    /// SDL will copy the channel map; the caller does not have to save this array
    /// after this call.
    ///
    /// If `count` is not equal to the current number of channels in the audio
    /// stream's format, this will fail. This is a safety measure to make sure a
    /// race condition hasn't changed the format while this call is setting the
    /// channel map.
    ///
    /// Unlike attempting to change the stream's format, the output channel map on
    /// a stream bound to a recording device is permitted to change at any time;
    /// any data added to the stream after this call will have the new mapping, but
    /// previously-added data will still have the prior mapping. When the channel
    /// map doesn't match the hardware's channel layout, SDL will convert the data
    /// before feeding it to the device for playback.
    boolean SDL_SetAudioStreamOutputChannelMap(SdlAudioStreamHandle stream, int[] map);

    /// Add data to the stream.
    ///
    /// This data must match the format/channels/samplerate specified in the latest
    /// call to SDL_SetAudioStreamFormat, or the format specified when creating the
    /// stream if it hasn't been changed.
    ///
    /// Note that this call simply copies the unconverted data for later. This is
    /// different than SDL2, where data was converted during the Put call and the
    /// Get call would just dequeue the previously-converted data.
    boolean SDL_PutAudioStreamData(SdlAudioStreamHandle stream, ByteBuffer buffer);

    /// Get converted/resampled data from the stream.
    ///
    /// The input/output data format/channels/samplerate is specified when creating
    /// the stream, and can be changed after creation by calling
    /// SDL_SetAudioStreamFormat.
    ///
    /// Note that any conversion and resampling necessary is done during this call,
    /// and SDL_PutAudioStreamData simply queues unconverted data for later. This
    /// is different than SDL2, where that work was done while inputting new data
    /// to the stream and requesting the output just copied the converted data.
    int SDL_GetAudioStreamData(SdlAudioStreamHandle stream, ByteBuffer buffer);

    /// Get the number of converted/resampled bytes available.
    ///
    /// The stream may be buffering data behind the scenes until it has enough to
    /// resample correctly, so this number might be lower than what you expect, or
    /// even be zero. Add more data or flush the stream if you need the data now.
    ///
    /// If the stream has so much data that it would overflow an int, the return
    /// value is clamped to a maximum value, but no queued data is lost; if there
    /// are gigabytes of data queued, the app might need to read some of it with
    /// SDL_GetAudioStreamData before this function's return value is no longer
    /// clamped.
    int SDL_GetAudioStreamAvailable(SdlAudioStreamHandle stream);

    /// Get the number of bytes currently queued.
    ///
    /// This is the number of bytes put into a stream as input, not the number that
    /// can be retrieved as output. Because of several details, it's not possible
    /// to calculate one number directly from the other. If you need to know how
    /// much usable data can be retrieved right now, you should use
    /// SDL_GetAudioStreamAvailable() and not this function.
    ///
    /// Note that audio streams can change their input format at any time, even if
    /// there is still data queued in a different format, so the returned byte
    /// count will not necessarily match the number of _sample frames_ available.
    /// Users of this API should be aware of format changes they make when feeding
    /// a stream and plan accordingly.
    ///
    /// Queued data is not converted until it is consumed by
    /// SDL_GetAudioStreamData, so this value should be representative of the exact
    /// data that was put into the stream.
    ///
    /// If the stream has so much data that it would overflow an int, the return
    /// value is clamped to a maximum value, but no queued data is lost; if there
    /// are gigabytes of data queued, the app might need to read some of it with
    /// SDL_GetAudioStreamData before this function's return value is no longer
    /// clamped.
    int SDL_GetAudioStreamQueued(SdlAudioStreamHandle stream);

    /// Tell the stream that you're done sending data, and anything being buffered
    /// should be converted/resampled and made available immediately.
    ///
    /// It is legal to add more data to a stream after flushing, but there may be
    /// audio gaps in the output. Generally this is intended to signal the end of
    /// input, so the complete output becomes available.
    boolean SDL_FlushAudioStream(SdlAudioStreamHandle stream);

    /// Clear any pending data in the stream.
    ///
    /// This drops any queued data, so there will be nothing to read from the
    /// stream until more is added.
    boolean SDL_ClearAudioStream(SdlAudioStreamHandle stream);

    /// Use this function to pause audio playback on the audio device associated
    /// with an audio stream.
    ///
    /// This function pauses audio processing for a given device. Any bound audio
    /// streams will not progress, and no audio will be generated. Pausing one
    /// device does not prevent other unpaused devices from running.
    ///
    /// Pausing a device can be useful to halt all audio without unbinding all the
    /// audio streams. This might be useful while a game is paused, or a level is
    /// loading, etc.
    boolean SDL_PauseAudioStreamDevice(SdlAudioStreamHandle stream);

    /// Use this function to unpause audio playback on the audio device associated
    /// with an audio stream.
    ///
    /// This function unpauses audio processing for a given device that has
    /// previously been paused. Once unpaused, any bound audio streams will begin
    /// to progress again, and audio can be generated.
    ///
    /// SDL_OpenAudioDeviceStream opens audio devices in a paused state, so this
    /// function call is required for audio playback to begin on such devices.
    boolean SDL_ResumeAudioStreamDevice(SdlAudioStreamHandle stream);

    /// Lock an audio stream for serialized access.
    ///
    /// Each SDL_AudioStream has an internal mutex it uses to protect its data
    /// structures from threading conflicts. This function allows an app to lock
    /// that mutex, which could be useful if registering callbacks on this stream.
    ///
    /// One does not need to lock a stream to use in it most cases, as the stream
    /// manages this lock internally. However, this lock is held during callbacks,
    /// which may run from arbitrary threads at any time, so if an app needs to
    /// protect shared data during those callbacks, locking the stream guarantees
    /// that the callback is not running while the lock is held.
    ///
    /// As this is just a wrapper over SDL_LockMutex for an internal lock; it has
    /// all the same attributes (recursive locks are allowed, etc).
    boolean SDL_LockAudioStream(SdlAudioStreamHandle stream);

    /// Unlock an audio stream for serialized access.
    ///
    /// This unlocks an audio stream after a call to SDL_LockAudioStream.
    boolean SDL_UnlockAudioStream(SdlAudioStreamHandle stream);

    /// Set a callback that runs when data is requested from an audio stream.
    ///
    /// This callback is called _before_ data is obtained from the stream, giving
    /// the callback the chance to add more on-demand.
    ///
    /// The callback can (optionally) call SDL_PutAudioStreamData() to add more
    /// audio to the stream during this call; if needed, the request that triggered
    /// this callback will obtain the new data immediately.
    ///
    /// The callback's `additional_amount` argument is roughly how many bytes of
    /// _unconverted_ data (in the stream's input format) is needed by the caller,
    /// although this may overestimate a little for safety. This takes into account
    /// how much is already in the stream and only asks for any extra necessary to
    /// resolve the request, which means the callback may be asked for zero bytes,
    /// and a different amount on each call.
    ///
    /// The callback is not required to supply exact amounts; it is allowed to
    /// supply too much or too little or none at all. The caller will get what's
    /// available, up to the amount they requested, regardless of this callback's
    /// outcome.
    ///
    /// Clearing or flushing an audio stream does not call this callback.
    ///
    /// This function obtains the stream's lock, which means any existing callback
    /// (get or put) in progress will finish running before setting the new
    /// callback.
    ///
    /// Setting a NULL function turns off the callback.
    boolean SDL_SetAudioStreamGetCallback(SdlAudioStreamHandle stream, AudioStreamCallback callback, SdlPointer userdata);

    /// Set a callback that runs when data is added to an audio stream.
    ///
    /// This callback is called _after_ the data is added to the stream, giving the
    /// callback the chance to obtain it immediately.
    ///
    /// The callback can (optionally) call SDL_GetAudioStreamData() to obtain audio
    /// from the stream during this call.
    ///
    /// The callback's `additional_amount` argument is how many bytes of
    /// _converted_ data (in the stream's output format) was provided by the
    /// caller, although this may underestimate a little for safety. This value
    /// might be less than what is currently available in the stream, if data was
    /// already there, and might be less than the caller provided if the stream
    /// needs to keep a buffer to aid in resampling. Which means the callback may
    /// be provided with zero bytes, and a different amount on each call.
    ///
    /// The callback may call SDL_GetAudioStreamAvailable to see the total amount
    /// currently available to read from the stream, instead of the total provided
    /// by the current call.
    ///
    /// The callback is not required to obtain all data. It is allowed to read less
    /// or none at all. Anything not read now simply remains in the stream for
    /// later access.
    ///
    /// Clearing or flushing an audio stream does not call this callback.
    ///
    /// This function obtains the stream's lock, which means any existing callback
    /// (get or put) in progress will finish running before setting the new
    /// callback.
    ///
    /// Setting a NULL function turns off the callback.
    boolean SDL_SetAudioStreamPutCallback(SdlAudioStreamHandle stream, AudioStreamCallback callback, SdlPointer userdata);

    /// Free an audio stream.
    ///
    /// This will release all allocated data, including any audio that is still
    /// queued. You do not need to manually clear the stream first.
    ///
    /// If this stream was bound to an audio device, it is unbound during this
    /// call. If this stream was created with SDL_OpenAudioDeviceStream, the audio
    /// device that was opened alongside this stream's creation will be closed,
    /// too.
    void SDL_DestroyAudioStream(SdlAudioStreamHandle stream);

    /// Convenience function for straightforward audio init for the common case.
    ///
    /// If all your app intends to do is provide a single source of PCM audio, this
    /// function allows you to do all your audio setup in a single call.
    SdlAudioStreamHandle SDL_OpenAudioDeviceStream(SdlAudioDeviceId deviceId, SdlAudioSpec spec,
                                                   AudioStreamCallback callback, SdlPointer userdata);

    /// Set a callback that fires when data is about to be fed to an audio device.
    ///
    /// This is useful for accessing the final mix, perhaps for writing a
    /// visualizer or applying a final effect to the audio data before playback.
    ///
    /// The buffer is the final mix of all bound audio streams on an opened device;
    /// this callback will fire regularly for any device that is both opened and
    /// unpaused. If there is no new data to mix, either because no streams are
    /// bound to the device or all the streams are empty, this callback will still
    /// fire with the entire buffer set to silence.
    ///
    /// This callback is allowed to make changes to the data; the contents of the
    /// buffer after this call is what is ultimately passed along to the hardware.
    ///
    /// The callback is always provided the data in float format (values from -1.0f
    /// to 1.0f), but the number of channels or sample rate may be different than
    /// the format the app requested when opening the device; SDL might have had to
    /// manage a conversion behind the scenes, or the playback might have jumped to
    /// new physical hardware when a system default changed, etc. These details may
    /// change between calls. Accordingly, the size of the buffer might change
    /// between calls as well.
    ///
    /// This callback can run at any time, and from any thread; if you need to
    /// serialize access to your app's data, you should provide and use a mutex or
    /// other synchronization device.
    ///
    /// All of this to say: there are specific needs this callback can fulfill, but
    /// it is not the simplest interface. Apps should generally provide audio in
    /// their preferred format through an SDL_AudioStream and let SDL handle the
    /// difference.
    ///
    /// This function is extremely time-sensitive; the callback should do the least
    /// amount of work possible and return as quickly as it can. The longer the
    /// callback runs, the higher the risk of audio dropouts or other problems.
    ///
    /// This function will block until the audio device is in between iterations,
    /// so any existing callback that might be running will finish before this
    /// function sets the new callback and returns.
    ///
    /// Setting a NULL callback function disables any previously-set callback.
    boolean SDL_SetAudioPostmixCallback(SdlAudioDeviceId deviceId, AudioPostmixCallback callback, SdlPointer userdata);

    /// Load the audio data of a WAVE file into memory.
    ///
    /// Loading a WAVE file requires `src`, `spec`, `audio_buf` and `audio_len` to
    /// be valid pointers. The entire data portion of the file is then loaded into
    /// memory and decoded if necessary.
    boolean SDL_LoadWAV_IO(SdlIoStreamHandle src, boolean closeio, SdlAudioSpecRef spec,
                           PointerRef audioBuffer, IntRef audioLength);

    /// Loads a WAV from a file path.
    ///
    /// This is a convenience function that is effectively the same as:
    ///
    /// ```c
    /// SDL_LoadWAV_IO(SDL_IOFromFile(path, "rb"), true, spec, audio_buf, audio_len);
    /// ```
    boolean SDL_LoadWAV(String path, SdlAudioSpecRef spec, PointerRef audioBuffer, IntRef audioLength);

    /// Mix audio data in a specified format.
    ///
    /// This takes an audio buffer `src` of `len` bytes of `format` data and mixes
    /// it into `dst`, performing addition, volume adjustment, and overflow
    /// clipping. The buffer pointed to by `dst` must also be `len` bytes of
    /// `format` data.
    ///
    /// This is provided for convenience -- you can mix your own audio data.
    ///
    /// Do not use this function for mixing together more than two streams of
    /// sample data. The output from repeated application of this function may be
    /// distorted by clipping, because there is no accumulator with greater range
    /// than the input (not to mention this being an inefficient way of doing it).
    ///
    /// It is a common misconception that this function is required to write audio
    /// data to an output stream in an audio callback. While you can do that,
    /// SDL_MixAudio() is really only needed when you're mixing a single audio
    /// stream with a volume adjustment.
    boolean SDL_MixAudio(ByteBuffer destination, ByteBuffer source, int format, int length, float volume);

    /// Convert some audio data of one format to another format.
    ///
    /// Please note that this function is for convenience, but should not be used
    /// to resample audio in blocks, as it will introduce audio artifacts on the
    /// boundaries. You should only use this function if you are converting audio
    /// data in its entirety in one call. If you want to convert audio in smaller
    /// chunks, use an SDL_AudioStream, which is designed for this situation.
    boolean SDL_ConvertAudioSamples(SdlAudioSpec srcSpec, ByteBuffer srcData,
                                    SdlAudioSpec dstSpec, PointerRef dstData, IntRef dstLength);

    /// Get the appropriate memset value for silencing an audio format.
    ///
    /// The value returned by this function can be used as the second argument to
    /// memset (or SDL_memset) to set an audio buffer in a specific format to
    /// silence.
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
