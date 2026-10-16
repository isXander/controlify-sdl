package dev.isxander.sdl.ffm;

import dev.isxander.sdl.*;

public final class SdlFfm implements Sdl {
    private final SdlFfmInit init;
    private final SdlFfmError error;
    private final SdlFfmVersion version;
    private final SdlFfmPlatform platform;
    private final SdlFfmHints hints;
    private final SdlFfmProperties properties;
    private final SdlFfmEvents events;
    private final SdlFfmJoystick joystick;
    private final SdlFfmGamepad gamepad;
    private final SdlFfmAudio audio;
    private final SdlFfmHidApi hidApi;
    private final SdlFfmIoStream ioStream;
    private final SdlFfmKeyboard keyboard;
    private final SdlFfmStdinc stdinc;
    private final SdlFfmGuidApi guid;
    private final SdlFfmTimer timer;

    SdlFfm() {
        this.init = new SdlFfmInit();
        this.error = new SdlFfmError();
        this.version = new SdlFfmVersion();
        this.platform = new SdlFfmPlatform();
        this.hints = new SdlFfmHints();
        this.properties = new SdlFfmProperties();
        this.events = new SdlFfmEvents();
        this.joystick = new SdlFfmJoystick();
        this.gamepad = new SdlFfmGamepad();
        this.audio = new SdlFfmAudio();
        this.hidApi = new SdlFfmHidApi();
        this.ioStream = new SdlFfmIoStream();
        this.keyboard = new SdlFfmKeyboard();
        this.stdinc = new SdlFfmStdinc();
        this.guid = new SdlFfmGuidApi();
        this.timer = new SdlFfmTimer();
    }

    @Override
    public SdlInit init() {
        return this.init;
    }

    @Override
    public SdlError error() {
        return this.error;
    }

    @Override
    public SdlVersion version() {
        return this.version;
    }

    @Override
    public SdlPlatform platform() {
        return this.platform;
    }

    @Override
    public SdlHints hints() {
        return this.hints;
    }

    @Override
    public SdlProperties properties() {
        return this.properties;
    }

    @Override
    public SdlEvents events() {
        return this.events;
    }

    @Override
    public SdlJoystick joystick() {
        return this.joystick;
    }

    @Override
    public SdlGamepad gamepad() {
        return this.gamepad;
    }

    @Override
    public SdlAudio audio() {
        return this.audio;
    }

    @Override
    public SdlHidApi hidApi() {
        return this.hidApi;
    }

    @Override
    public SdlIoStream ioStream() {
        return this.ioStream;
    }

    @Override
    public SdlKeyboard keyboard() {
        return this.keyboard;
    }

    @Override
    public SdlStdinc stdinc() {
        return this.stdinc;
    }

    @Override
    public SdlGuidApi guid() {
        return this.guid;
    }

    @Override
    public SdlTimer timer() {
        return this.timer;
    }
}
