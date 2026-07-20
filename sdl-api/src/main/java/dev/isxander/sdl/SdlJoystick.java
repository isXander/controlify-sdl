package dev.isxander.sdl;

import java.nio.ByteBuffer;

import dev.isxander.sdl.SdlRefs.IntRef;
import dev.isxander.sdl.SdlRefs.ShortRef;

public interface SdlJoystick {
    int SDL_JOYSTICK_TYPE_UNKNOWN = 0;
    int SDL_JOYSTICK_TYPE_GAMEPAD = 1;
    int SDL_JOYSTICK_TYPE_WHEEL = 2;
    int SDL_JOYSTICK_TYPE_ARCADE_STICK = 3;
    int SDL_JOYSTICK_TYPE_FLIGHT_STICK = 4;
    int SDL_JOYSTICK_TYPE_DANCE_PAD = 5;
    int SDL_JOYSTICK_TYPE_GUITAR = 6;
    int SDL_JOYSTICK_TYPE_DRUM_KIT = 7;
    int SDL_JOYSTICK_TYPE_ARCADE_PAD = 8;
    int SDL_JOYSTICK_TYPE_THROTTLE = 9;
    int SDL_JOYSTICK_TYPE_COUNT = 10;

    int SDL_JOYSTICK_CONNECTION_INVALID = -1;
    int SDL_JOYSTICK_CONNECTION_UNKNOWN = 0;
    int SDL_JOYSTICK_CONNECTION_WIRED = 1;
    int SDL_JOYSTICK_CONNECTION_WIRELESS = 2;

    byte SDL_HAT_CENTERED = 0x00;
    byte SDL_HAT_UP = 0x01;
    byte SDL_HAT_RIGHT = 0x02;
    byte SDL_HAT_DOWN = 0x04;
    byte SDL_HAT_LEFT = 0x08;
    byte SDL_HAT_RIGHTUP = SDL_HAT_RIGHT | SDL_HAT_UP;
    byte SDL_HAT_RIGHTDOWN = SDL_HAT_RIGHT | SDL_HAT_DOWN;
    byte SDL_HAT_LEFTUP = SDL_HAT_LEFT | SDL_HAT_UP;
    byte SDL_HAT_LEFTDOWN = SDL_HAT_LEFT | SDL_HAT_DOWN;

    void SDL_LockJoysticks();

    void SDL_UnlockJoysticks();

    boolean SDL_HasJoystick();

    SdlJoystickId[] SDL_GetJoysticks();

    String SDL_GetJoystickNameForID(SdlJoystickId instanceId);

    String SDL_GetJoystickPathForID(SdlJoystickId instanceId);

    int SDL_GetJoystickPlayerIndexForID(SdlJoystickId instanceId);

    SdlGuid SDL_GetJoystickGUIDForID(SdlJoystickId instanceId);

    short SDL_GetJoystickVendorForID(SdlJoystickId instanceId);

    short SDL_GetJoystickProductForID(SdlJoystickId instanceId);

    short SDL_GetJoystickProductVersionForID(SdlJoystickId instanceId);

    int SDL_GetJoystickTypeForID(SdlJoystickId instanceId);

    SdlJoystickHandle SDL_OpenJoystick(SdlJoystickId instanceId);

    SdlJoystickHandle SDL_GetJoystickFromID(SdlJoystickId instanceId);

    SdlJoystickHandle SDL_GetJoystickFromPlayerIndex(int playerIndex);

    SdlPropertiesId SDL_GetJoystickProperties(SdlJoystickHandle joystick);

    String SDL_GetJoystickName(SdlJoystickHandle joystick);

    String SDL_GetJoystickPath(SdlJoystickHandle joystick);

    int SDL_GetJoystickPlayerIndex(SdlJoystickHandle joystick);

    boolean SDL_SetJoystickPlayerIndex(SdlJoystickHandle joystick, int playerIndex);

    short SDL_GetJoystickVendor(SdlJoystickHandle joystick);

    short SDL_GetJoystickProduct(SdlJoystickHandle joystick);

    short SDL_GetJoystickProductVersion(SdlJoystickHandle joystick);

    short SDL_GetJoystickFirmwareVersion(SdlJoystickHandle joystick);

    String SDL_GetJoystickSerial(SdlJoystickHandle joystick);

    int SDL_GetJoystickType(SdlJoystickHandle joystick);

    void SDL_GetJoystickGUIDInfo(SdlGuid guid, ShortRef vendor, ShortRef product, ShortRef version, ShortRef crc16);

    boolean SDL_JoystickConnected(SdlJoystickHandle joystick);

    SdlJoystickId SDL_GetJoystickID(SdlJoystickHandle joystick);

    int SDL_GetNumJoystickAxes(SdlJoystickHandle joystick);

    int SDL_GetNumJoystickHats(SdlJoystickHandle joystick);

    int SDL_GetNumJoystickButtons(SdlJoystickHandle joystick);

    void SDL_SetJoystickEventsEnabled(boolean enabled);

    boolean SDL_JoystickEventsEnabled();

    void SDL_UpdateJoysticks();

    short SDL_GetJoystickAxis(SdlJoystickHandle joystick, int axis);

    boolean SDL_GetJoystickAxisInitialState(SdlJoystickHandle joystick, int axis, ShortRef state);

    byte SDL_GetJoystickHat(SdlJoystickHandle joystick, int hat);

    boolean SDL_GetJoystickButton(SdlJoystickHandle joystick, int button);

    boolean SDL_RumbleJoystick(SdlJoystickHandle joystick, short lowFrequencyRumble,
                               short highFrequencyRumble, int durationMs);

    boolean SDL_RumbleJoystickTriggers(SdlJoystickHandle joystick, short leftRumble,
                                       short rightRumble, int durationMs);

    boolean SDL_SetJoystickLED(SdlJoystickHandle joystick, byte red, byte green, byte blue);

    boolean SDL_SendJoystickEffect(SdlJoystickHandle joystick, ByteBuffer data);

    void SDL_CloseJoystick(SdlJoystickHandle joystick);

    int SDL_GetJoystickConnectionState(SdlJoystickHandle joystick);

    int SDL_GetJoystickPowerInfo(SdlJoystickHandle joystick, IntRef percent);
}
