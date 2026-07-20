package dev.isxander.sdl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import dev.isxander.sdl.SdlRefs.ByteRef;
import dev.isxander.sdl.SdlRefs.FloatRef;
import dev.isxander.sdl.SdlRefs.IntRef;

public interface SdlGamepad {
    int SDL_GAMEPAD_TYPE_UNKNOWN = 0;
    int SDL_GAMEPAD_TYPE_STANDARD = 1;
    int SDL_GAMEPAD_TYPE_XBOX360 = 2;
    int SDL_GAMEPAD_TYPE_XBOXONE = 3;
    int SDL_GAMEPAD_TYPE_PS3 = 4;
    int SDL_GAMEPAD_TYPE_PS4 = 5;
    int SDL_GAMEPAD_TYPE_PS5 = 6;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_PRO = 7;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_LEFT = 8;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_RIGHT = 9;
    int SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_PAIR = 10;
    int SDL_GAMEPAD_TYPE_GAMECUBE = 11;
    int SDL_GAMEPAD_TYPE_COUNT = 12;

    int SDL_GAMEPAD_AXIS_INVALID = -1;
    int SDL_GAMEPAD_AXIS_LEFTX = 0;
    int SDL_GAMEPAD_AXIS_LEFTY = 1;
    int SDL_GAMEPAD_AXIS_RIGHTX = 2;
    int SDL_GAMEPAD_AXIS_RIGHTY = 3;
    int SDL_GAMEPAD_AXIS_LEFT_TRIGGER = 4;
    int SDL_GAMEPAD_AXIS_RIGHT_TRIGGER = 5;
    int SDL_GAMEPAD_AXIS_COUNT = 6;

    int SDL_GAMEPAD_BUTTON_INVALID = -1;
    int SDL_GAMEPAD_BUTTON_SOUTH = 0;
    int SDL_GAMEPAD_BUTTON_EAST = 1;
    int SDL_GAMEPAD_BUTTON_WEST = 2;
    int SDL_GAMEPAD_BUTTON_NORTH = 3;
    int SDL_GAMEPAD_BUTTON_BACK = 4;
    int SDL_GAMEPAD_BUTTON_GUIDE = 5;
    int SDL_GAMEPAD_BUTTON_START = 6;
    int SDL_GAMEPAD_BUTTON_LEFT_STICK = 7;
    int SDL_GAMEPAD_BUTTON_RIGHT_STICK = 8;
    int SDL_GAMEPAD_BUTTON_LEFT_SHOULDER = 9;
    int SDL_GAMEPAD_BUTTON_RIGHT_SHOULDER = 10;
    int SDL_GAMEPAD_BUTTON_DPAD_UP = 11;
    int SDL_GAMEPAD_BUTTON_DPAD_DOWN = 12;
    int SDL_GAMEPAD_BUTTON_DPAD_LEFT = 13;
    int SDL_GAMEPAD_BUTTON_DPAD_RIGHT = 14;
    int SDL_GAMEPAD_BUTTON_MISC1 = 15;
    int SDL_GAMEPAD_BUTTON_RIGHT_PADDLE1 = 16;
    int SDL_GAMEPAD_BUTTON_LEFT_PADDLE1 = 17;
    int SDL_GAMEPAD_BUTTON_RIGHT_PADDLE2 = 18;
    int SDL_GAMEPAD_BUTTON_LEFT_PADDLE2 = 19;
    int SDL_GAMEPAD_BUTTON_TOUCHPAD = 20;
    int SDL_GAMEPAD_BUTTON_MISC2 = 21;
    int SDL_GAMEPAD_BUTTON_MISC3 = 22;
    int SDL_GAMEPAD_BUTTON_MISC4 = 23;
    int SDL_GAMEPAD_BUTTON_MISC5 = 24;
    int SDL_GAMEPAD_BUTTON_MISC6 = 25;
    int SDL_GAMEPAD_BUTTON_COUNT = 26;

    int SDL_GAMEPAD_BUTTON_LABEL_UNKNOWN = 0;
    int SDL_GAMEPAD_BUTTON_LABEL_A = 1;
    int SDL_GAMEPAD_BUTTON_LABEL_B = 2;
    int SDL_GAMEPAD_BUTTON_LABEL_X = 3;
    int SDL_GAMEPAD_BUTTON_LABEL_Y = 4;
    int SDL_GAMEPAD_BUTTON_LABEL_CROSS = 5;
    int SDL_GAMEPAD_BUTTON_LABEL_CIRCLE = 6;
    int SDL_GAMEPAD_BUTTON_LABEL_SQUARE = 7;
    int SDL_GAMEPAD_BUTTON_LABEL_TRIANGLE = 8;

    int SDL_GAMEPAD_BINDTYPE_NONE = 0;
    int SDL_GAMEPAD_BINDTYPE_BUTTON = 1;
    int SDL_GAMEPAD_BINDTYPE_AXIS = 2;
    int SDL_GAMEPAD_BINDTYPE_HAT = 3;

    int SDL_SENSOR_INVALID = -1;
    int SDL_SENSOR_UNKNOWN = 0;
    int SDL_SENSOR_ACCEL = 1;
    int SDL_SENSOR_GYRO = 2;
    int SDL_SENSOR_ACCEL_L = 3;
    int SDL_SENSOR_GYRO_L = 4;
    int SDL_SENSOR_ACCEL_R = 5;
    int SDL_SENSOR_GYRO_R = 6;
    int SDL_SENSOR_COUNT = 7;
    float SDL_STANDARD_GRAVITY = 9.80665f;

    int SDL_POWERSTATE_ERROR = -1;
    int SDL_POWERSTATE_UNKNOWN = 0;
    int SDL_POWERSTATE_ON_BATTERY = 1;
    int SDL_POWERSTATE_NO_BATTERY = 2;
    int SDL_POWERSTATE_CHARGING = 3;
    int SDL_POWERSTATE_CHARGED = 4;

    int SDL_AddGamepadMapping(String mapping);

    int SDL_AddGamepadMappingsFromIO(SdlIoStreamHandle src, boolean closeio);

    int SDL_AddGamepadMappingsFromFile(String file);

    boolean SDL_ReloadGamepadMappings();

    String SDL_GetGamepadMappingForGUID(SdlGuid guid);

    String SDL_GetGamepadMapping(SdlGamepadHandle gamepad);

    boolean SDL_SetGamepadMapping(SdlJoystickId instanceId, String mapping);

    boolean SDL_HasGamepad();

    SdlJoystickId[] SDL_GetGamepads();

    boolean SDL_IsGamepad(SdlJoystickId instanceId);

    String SDL_GetGamepadNameForID(SdlJoystickId instanceId);

    String SDL_GetGamepadPathForID(SdlJoystickId instanceId);

    int SDL_GetGamepadPlayerIndexForID(SdlJoystickId instanceId);

    SdlGuid SDL_GetGamepadGUIDForID(SdlJoystickId instanceId);

    short SDL_GetGamepadVendorForID(SdlJoystickId instanceId);

    short SDL_GetGamepadProductForID(SdlJoystickId instanceId);

    short SDL_GetGamepadProductVersionForID(SdlJoystickId instanceId);

    int SDL_GetGamepadTypeForID(SdlJoystickId instanceId);

    int SDL_GetRealGamepadTypeForID(SdlJoystickId instanceId);

    String SDL_GetGamepadMappingForID(SdlJoystickId instanceId);

    SdlGamepadHandle SDL_OpenGamepad(SdlJoystickId instanceId);

    SdlGamepadHandle SDL_GetGamepadFromID(SdlJoystickId instanceId);

    SdlGamepadHandle SDL_GetGamepadFromPlayerIndex(int playerIndex);

    SdlPropertiesId SDL_GetGamepadProperties(SdlGamepadHandle gamepad);

    SdlJoystickId SDL_GetGamepadID(SdlGamepadHandle gamepad);

    String SDL_GetGamepadName(SdlGamepadHandle gamepad);

    String SDL_GetGamepadPath(SdlGamepadHandle gamepad);

    int SDL_GetGamepadType(SdlGamepadHandle gamepad);

    int SDL_GetRealGamepadType(SdlGamepadHandle gamepad);

    int SDL_GetGamepadPlayerIndex(SdlGamepadHandle gamepad);

    boolean SDL_SetGamepadPlayerIndex(SdlGamepadHandle gamepad, int playerIndex);

    short SDL_GetGamepadVendor(SdlGamepadHandle gamepad);

    short SDL_GetGamepadProduct(SdlGamepadHandle gamepad);

    short SDL_GetGamepadProductVersion(SdlGamepadHandle gamepad);

    short SDL_GetGamepadFirmwareVersion(SdlGamepadHandle gamepad);

    String SDL_GetGamepadSerial(SdlGamepadHandle gamepad);

    long SDL_GetGamepadSteamHandle(SdlGamepadHandle gamepad);

    int SDL_GetGamepadConnectionState(SdlGamepadHandle gamepad);

    int SDL_GetGamepadPowerInfo(SdlGamepadHandle gamepad, IntRef percent);

    boolean SDL_GamepadConnected(SdlGamepadHandle gamepad);

    SdlJoystickHandle SDL_GetGamepadJoystick(SdlGamepadHandle gamepad);

    void SDL_SetGamepadEventsEnabled(boolean enabled);

    boolean SDL_GamepadEventsEnabled();

    SdlGamepadBinding[] SDL_GetGamepadBindings(SdlGamepadHandle gamepad);

    void SDL_UpdateGamepads();

    int SDL_GetGamepadTypeFromString(String value);

    String SDL_GetGamepadStringForType(int type);

    int SDL_GetGamepadAxisFromString(String value);

    String SDL_GetGamepadStringForAxis(int axis);

    boolean SDL_GamepadHasAxis(SdlGamepadHandle gamepad, int axis);

    short SDL_GetGamepadAxis(SdlGamepadHandle gamepad, int axis);

    int SDL_GetGamepadButtonFromString(String value);

    String SDL_GetGamepadStringForButton(int button);

    boolean SDL_GamepadHasButton(SdlGamepadHandle gamepad, int button);

    boolean SDL_GetGamepadButton(SdlGamepadHandle gamepad, int button);

    int SDL_GetGamepadButtonLabelForType(int type, int button);

    int SDL_GetGamepadButtonLabel(SdlGamepadHandle gamepad, int button);

    int SDL_GetNumGamepadTouchpads(SdlGamepadHandle gamepad);

    int SDL_GetNumGamepadTouchpadFingers(SdlGamepadHandle gamepad, int touchpad);

    boolean SDL_GetGamepadTouchpadFinger(SdlGamepadHandle gamepad, int touchpad, int finger,
                                         ByteRef down, FloatRef x, FloatRef y, FloatRef pressure);

    boolean SDL_GamepadHasSensor(SdlGamepadHandle gamepad, int type);

    boolean SDL_SetGamepadSensorEnabled(SdlGamepadHandle gamepad, int type, boolean enabled);

    boolean SDL_GamepadSensorEnabled(SdlGamepadHandle gamepad, int type);

    float SDL_GetGamepadSensorDataRate(SdlGamepadHandle gamepad, int type);

    boolean SDL_GetGamepadSensorData(SdlGamepadHandle gamepad, int type, FloatBuffer data);

    boolean SDL_RumbleGamepad(SdlGamepadHandle gamepad, short lowFrequencyRumble,
                              short highFrequencyRumble, int durationMs);

    boolean SDL_RumbleGamepadTriggers(SdlGamepadHandle gamepad, short leftRumble,
                                      short rightRumble, int durationMs);

    boolean SDL_SetGamepadLED(SdlGamepadHandle gamepad, byte red, byte green, byte blue);

    boolean SDL_SendGamepadEffect(SdlGamepadHandle gamepad, ByteBuffer data);

    void SDL_CloseGamepad(SdlGamepadHandle gamepad);

    String SDL_GetGamepadAppleSFSymbolsNameForButton(SdlGamepadHandle gamepad, int button);

    String SDL_GetGamepadAppleSFSymbolsNameForAxis(SdlGamepadHandle gamepad, int axis);
}
