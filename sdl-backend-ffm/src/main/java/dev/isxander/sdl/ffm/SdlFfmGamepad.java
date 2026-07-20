package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlGamepad;
import dev.isxander.sdl.SdlGamepadBinding;
import dev.isxander.sdl.SdlGamepadHandle;
import dev.isxander.sdl.SdlGuid;
import dev.isxander.sdl.SdlIoStreamHandle;
import dev.isxander.sdl.SdlJoystickHandle;
import dev.isxander.sdl.SdlJoystickId;
import dev.isxander.sdl.SdlPropertiesId;
import dev.isxander.sdl.SdlRefs.ByteRef;
import dev.isxander.sdl.SdlRefs.FloatRef;
import dev.isxander.sdl.SdlRefs.IntRef;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlGuidLayout;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_FLOAT;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
import static java.lang.foreign.ValueLayout.JAVA_SHORT;

final class SdlFfmGamepad implements SdlGamepad {
    private static final MethodHandle SDL_ADD_GAMEPAD_MAPPING_HANDLE = SdlFfmNative.downcall(
            "SDL_AddGamepadMapping",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_ADD_GAMEPAD_MAPPINGS_FROM_IO_HANDLE = SdlFfmNative.downcall(
            "SDL_AddGamepadMappingsFromIO",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_IO_STREAM,
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_ADD_GAMEPAD_MAPPINGS_FROM_FILE_HANDLE = SdlFfmNative.downcall(
            "SDL_AddGamepadMappingsFromFile",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_RELOAD_GAMEPAD_MAPPINGS_HANDLE = SdlFfmNative.downcall(
            "SDL_ReloadGamepadMappings",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_GET_GAMEPAD_MAPPING_FOR_GUID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadMappingForGUID",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlGuidLayout.layout()));
    private static final MethodHandle SDL_GET_GAMEPAD_MAPPING_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadMapping",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_SET_GAMEPAD_MAPPING_HANDLE = SdlFfmNative.downcall(
            "SDL_SetGamepadMapping",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_HAS_GAMEPAD_HANDLE = SdlFfmNative.downcall(
            "SDL_HasGamepad",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_GET_GAMEPADS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepads",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_JOYSTICK_ID_POINTER,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_IS_GAMEPAD_HANDLE = SdlFfmNative.downcall(
            "SDL_IsGamepad",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_NAME_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadNameForID",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_PATH_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadPathForID",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_PLAYER_INDEX_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadPlayerIndexForID",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_GUID_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadGUIDForID",
            FunctionDescriptor.of(
                    SdlGuidLayout.layout(),
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_VENDOR_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadVendorForID",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_PRODUCT_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadProductForID",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_PRODUCT_VERSION_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadProductVersionForID",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_TYPE_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadTypeForID",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_REAL_GAMEPAD_TYPE_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetRealGamepadTypeForID",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_MAPPING_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadMappingForID",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_OPEN_GAMEPAD_HANDLE = SdlFfmNative.downcall(
            "SDL_OpenGamepad",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_FROM_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadFromID",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_FROM_PLAYER_INDEX_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadFromPlayerIndex",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadProperties",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadID",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_NAME_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadName",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_PATH_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadPath",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_TYPE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadType",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_REAL_GAMEPAD_TYPE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetRealGamepadType",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_PLAYER_INDEX_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadPlayerIndex",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_SET_GAMEPAD_PLAYER_INDEX_HANDLE = SdlFfmNative.downcall(
            "SDL_SetGamepadPlayerIndex",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_VENDOR_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadVendor",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_PRODUCT_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadProduct",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_PRODUCT_VERSION_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadProductVersion",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_FIRMWARE_VERSION_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadFirmwareVersion",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_SERIAL_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadSerial",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_STEAM_HANDLE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadSteamHandle",
            FunctionDescriptor.of(
                    JAVA_LONG,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_CONNECTION_STATE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadConnectionState",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_POWER_INFO_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadPowerInfo",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_GAMEPAD_CONNECTED_HANDLE = SdlFfmNative.downcall(
            "SDL_GamepadConnected",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_JOYSTICK_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadJoystick",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_JOYSTICK,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_SET_GAMEPAD_EVENTS_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_SetGamepadEventsEnabled",
            FunctionDescriptor.ofVoid(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_GAMEPAD_EVENTS_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_GamepadEventsEnabled",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_GET_GAMEPAD_BINDINGS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadBindings",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_GAMEPAD_BINDING_ARRAY,
                    SdlLayouts.SDL_GAMEPAD,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_UPDATE_GAMEPADS_HANDLE = SdlFfmNative.downcall(
            "SDL_UpdateGamepads",
            FunctionDescriptor.ofVoid());
    private static final MethodHandle SDL_GET_GAMEPAD_TYPE_FROM_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadTypeFromString",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_GAMEPAD_STRING_FOR_TYPE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadStringForType",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_AXIS_FROM_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadAxisFromString",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_GAMEPAD_STRING_FOR_AXIS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadStringForAxis",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GAMEPAD_HAS_AXIS_HANDLE = SdlFfmNative.downcall(
            "SDL_GamepadHasAxis",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_AXIS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadAxis",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_BUTTON_FROM_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadButtonFromString",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_GAMEPAD_STRING_FOR_BUTTON_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadStringForButton",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GAMEPAD_HAS_BUTTON_HANDLE = SdlFfmNative.downcall(
            "SDL_GamepadHasButton",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_BUTTON_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadButton",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_BUTTON_LABEL_FOR_TYPE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadButtonLabelForType",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_BUTTON_LABEL_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadButtonLabel",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_NUM_GAMEPAD_TOUCHPADS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumGamepadTouchpads",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_NUM_GAMEPAD_TOUCHPAD_FINGERS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumGamepadTouchpadFingers",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_TOUCHPAD_FINGER_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadTouchpadFinger",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT,
                    JAVA_INT,
                    SdlLayouts.BOOL_POINTER,
                    SdlLayouts.FLOAT_POINTER,
                    SdlLayouts.FLOAT_POINTER,
                    SdlLayouts.FLOAT_POINTER));
    private static final MethodHandle SDL_GAMEPAD_HAS_SENSOR_HANDLE = SdlFfmNative.downcall(
            "SDL_GamepadHasSensor",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_SET_GAMEPAD_SENSOR_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_SetGamepadSensorEnabled",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT,
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_GAMEPAD_SENSOR_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_GamepadSensorEnabled",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_SENSOR_DATA_RATE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadSensorDataRate",
            FunctionDescriptor.of(
                    JAVA_FLOAT,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_SENSOR_DATA_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadSensorData",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT,
                    SdlLayouts.FLOAT_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_RUMBLE_GAMEPAD_HANDLE = SdlFfmNative.downcall(
            "SDL_RumbleGamepad",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_SHORT,
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_RUMBLE_GAMEPAD_TRIGGERS_HANDLE = SdlFfmNative.downcall(
            "SDL_RumbleGamepadTriggers",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_SHORT,
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_SET_GAMEPAD_LED_HANDLE = SdlFfmNative.downcall(
            "SDL_SetGamepadLED",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_BYTE,
                    JAVA_BYTE,
                    JAVA_BYTE));
    private static final MethodHandle SDL_SEND_GAMEPAD_EFFECT_HANDLE = SdlFfmNative.downcall(
            "SDL_SendGamepadEffect",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_GAMEPAD,
                    SdlLayouts.VOID_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_CLOSE_GAMEPAD_HANDLE = SdlFfmNative.downcall(
            "SDL_CloseGamepad",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_GAMEPAD));
    private static final MethodHandle SDL_GET_GAMEPAD_APPLE_SF_SYMBOLS_NAME_FOR_BUTTON_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadAppleSFSymbolsNameForButton",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_GAMEPAD_APPLE_SF_SYMBOLS_NAME_FOR_AXIS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGamepadAppleSFSymbolsNameForAxis",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_GAMEPAD,
                    JAVA_INT));

    @Override
    public int SDL_AddGamepadMapping(String mapping) {
        try (Arena arena = Arena.ofConfined()) {
            return (int) SDL_ADD_GAMEPAD_MAPPING_HANDLE.invokeExact(SdlFfmSupport.utf8(mapping, arena));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_AddGamepadMappingsFromFile(String file) {
        try (Arena arena = Arena.ofConfined()) {
            return (int) SDL_ADD_GAMEPAD_MAPPINGS_FROM_FILE_HANDLE.invokeExact(SdlFfmSupport.utf8(file, arena));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_AddGamepadMappingsFromIO(SdlIoStreamHandle src, boolean closeio) {
        try {
            return (int) SDL_ADD_GAMEPAD_MAPPINGS_FROM_IO_HANDLE.invokeExact(SdlFfmSupport.segment(src.address()), closeio);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_CloseGamepad(SdlGamepadHandle gamepad) {
        try {
            SDL_CLOSE_GAMEPAD_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GamepadConnected(SdlGamepadHandle gamepad) {
        try {
            return (boolean) SDL_GAMEPAD_CONNECTED_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GamepadEventsEnabled() {
        try {
            return (boolean) SDL_GAMEPAD_EVENTS_ENABLED_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GamepadHasAxis(SdlGamepadHandle gamepad, int axis) {
        try {
            return (boolean) SDL_GAMEPAD_HAS_AXIS_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), axis);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GamepadHasButton(SdlGamepadHandle gamepad, int button) {
        try {
            return (boolean) SDL_GAMEPAD_HAS_BUTTON_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), button);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GamepadHasSensor(SdlGamepadHandle gamepad, int type) {
        try {
            return (boolean) SDL_GAMEPAD_HAS_SENSOR_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), type);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GamepadSensorEnabled(SdlGamepadHandle gamepad, int type) {
        try {
            return (boolean) SDL_GAMEPAD_SENSOR_ENABLED_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), type);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadAppleSFSymbolsNameForAxis(SdlGamepadHandle gamepad, int axis) {
        try {
            return SdlFfmSupport.string(
                (MemorySegment) SDL_GET_GAMEPAD_APPLE_SF_SYMBOLS_NAME_FOR_AXIS_HANDLE.invokeExact(
                    SdlFfmSupport.segment(gamepad.address()), axis
                )
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadAppleSFSymbolsNameForButton(SdlGamepadHandle gamepad, int button) {
        try {
            return SdlFfmSupport.string(
                (MemorySegment) SDL_GET_GAMEPAD_APPLE_SF_SYMBOLS_NAME_FOR_BUTTON_HANDLE.invokeExact(
                    SdlFfmSupport.segment(gamepad.address()), button
                )
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadAxis(SdlGamepadHandle gamepad, int axis) {
        try {
            return (short) SDL_GET_GAMEPAD_AXIS_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), axis);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadAxisFromString(String value) {
        try (Arena arena = Arena.ofConfined()) {
            return (int) SDL_GET_GAMEPAD_AXIS_FROM_STRING_HANDLE.invokeExact(SdlFfmSupport.utf8(value, arena));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlGamepadBinding[] SDL_GetGamepadBindings(SdlGamepadHandle gamepad) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.gamepadBindings(gamepad, arena);
        }
    }

    @Override
    public boolean SDL_GetGamepadButton(SdlGamepadHandle gamepad, int button) {
        try {
            return (boolean) SDL_GET_GAMEPAD_BUTTON_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), button);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadButtonFromString(String value) {
        try (Arena arena = Arena.ofConfined()) {
            return (int) SDL_GET_GAMEPAD_BUTTON_FROM_STRING_HANDLE.invokeExact(SdlFfmSupport.utf8(value, arena));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadButtonLabel(SdlGamepadHandle gamepad, int button) {
        try {
            return (int) SDL_GET_GAMEPAD_BUTTON_LABEL_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), button);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadButtonLabelForType(int type, int button) {
        try {
            return (int) SDL_GET_GAMEPAD_BUTTON_LABEL_FOR_TYPE_HANDLE.invokeExact(type, button);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadConnectionState(SdlGamepadHandle gamepad) {
        try {
            return (int) SDL_GET_GAMEPAD_CONNECTION_STATE_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadFirmwareVersion(SdlGamepadHandle gamepad) {
        try {
            return (short) SDL_GET_GAMEPAD_FIRMWARE_VERSION_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlGamepadHandle SDL_GetGamepadFromID(SdlJoystickId instanceId) {
        try {
            return new SdlGamepadHandle(((MemorySegment) SDL_GET_GAMEPAD_FROM_ID_HANDLE.invokeExact(
                     instanceId.value())).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlGamepadHandle SDL_GetGamepadFromPlayerIndex(int playerIndex) {
        try {
            return new SdlGamepadHandle(((MemorySegment) SDL_GET_GAMEPAD_FROM_PLAYER_INDEX_HANDLE.invokeExact(
                     playerIndex)).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlGuid SDL_GetGamepadGUIDForID(SdlJoystickId instanceId) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.guid((MemorySegment) SDL_GET_GAMEPAD_GUID_FOR_ID_HANDLE.invokeExact((SegmentAllocator) arena, instanceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickId SDL_GetGamepadID(SdlGamepadHandle gamepad) {
        try {
            return new SdlJoystickId((int) SDL_GET_GAMEPAD_ID_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickHandle SDL_GetGamepadJoystick(SdlGamepadHandle gamepad) {
        try {
            return new SdlJoystickHandle(
                    ((MemorySegment) SDL_GET_GAMEPAD_JOYSTICK_HANDLE.invokeExact(
                            SdlFfmSupport.segment(gamepad.address()))).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadMapping(SdlGamepadHandle gamepad) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_MAPPING_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadMappingForGUID(SdlGuid guid) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_MAPPING_FOR_GUID_HANDLE.invokeExact(SdlFfmSupport.guid(guid, arena)));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadMappingForID(SdlJoystickId instanceId) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_MAPPING_FOR_ID_HANDLE.invokeExact(instanceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadName(SdlGamepadHandle gamepad) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_NAME_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadNameForID(SdlJoystickId instanceId) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_NAME_FOR_ID_HANDLE.invokeExact(instanceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadPath(SdlGamepadHandle gamepad) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_PATH_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadPathForID(SdlJoystickId instanceId) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_PATH_FOR_ID_HANDLE.invokeExact(instanceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadPlayerIndex(SdlGamepadHandle gamepad) {
        try {
            return (int) SDL_GET_GAMEPAD_PLAYER_INDEX_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadPlayerIndexForID(SdlJoystickId instanceId) {
        try {
            return (int) SDL_GET_GAMEPAD_PLAYER_INDEX_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadPowerInfo(SdlGamepadHandle gamepad, IntRef percent) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativePercent = arena.allocate(ValueLayout.JAVA_INT);
            nativePercent.set(ValueLayout.JAVA_INT, 0L, percent.value);
            int powerState = (int) SDL_GET_GAMEPAD_POWER_INFO_HANDLE.invokeExact(
                SdlFfmSupport.segment(gamepad.address()), nativePercent
            );
            percent.value = nativePercent.get(ValueLayout.JAVA_INT, 0L);
            return powerState;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadProduct(SdlGamepadHandle gamepad) {
        try {
            return (short) SDL_GET_GAMEPAD_PRODUCT_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadProductForID(SdlJoystickId instanceId) {
        try {
            return (short) SDL_GET_GAMEPAD_PRODUCT_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadProductVersion(SdlGamepadHandle gamepad) {
        try {
            return (short) SDL_GET_GAMEPAD_PRODUCT_VERSION_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadProductVersionForID(SdlJoystickId instanceId) {
        try {
            return (short) SDL_GET_GAMEPAD_PRODUCT_VERSION_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPropertiesId SDL_GetGamepadProperties(SdlGamepadHandle gamepad) {
        try {
            return new SdlPropertiesId((int) SDL_GET_GAMEPAD_PROPERTIES_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetGamepadSensorData(SdlGamepadHandle gamepad, int type, FloatBuffer data) {
        try {
            return (boolean) SDL_GET_GAMEPAD_SENSOR_DATA_HANDLE.invokeExact(
                SdlFfmSupport.segment(gamepad.address()), type, MemorySegment.ofBuffer(data), data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public float SDL_GetGamepadSensorDataRate(SdlGamepadHandle gamepad, int type) {
        try {
            return (float) SDL_GET_GAMEPAD_SENSOR_DATA_RATE_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), type);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadSerial(SdlGamepadHandle gamepad) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_SERIAL_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_GetGamepadSteamHandle(SdlGamepadHandle gamepad) {
        try {
            return (long) SDL_GET_GAMEPAD_STEAM_HANDLE_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadStringForAxis(int axis) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_STRING_FOR_AXIS_HANDLE.invokeExact(axis));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadStringForButton(int button) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_STRING_FOR_BUTTON_HANDLE.invokeExact(button));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetGamepadStringForType(int type) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_GAMEPAD_STRING_FOR_TYPE_HANDLE.invokeExact(type));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetGamepadTouchpadFinger(SdlGamepadHandle gamepad, int touchpad, int finger, ByteRef down, FloatRef x, FloatRef y, FloatRef pressure) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeDown = arena.allocate(ValueLayout.JAVA_BYTE);
            nativeDown.set(ValueLayout.JAVA_BYTE, 0L, down.value);
            MemorySegment nativeX = arena.allocate(ValueLayout.JAVA_FLOAT);
            nativeX.set(ValueLayout.JAVA_FLOAT, 0L, x.value);
            MemorySegment nativeY = arena.allocate(ValueLayout.JAVA_FLOAT);
            nativeY.set(ValueLayout.JAVA_FLOAT, 0L, y.value);
            MemorySegment nativePressure = arena.allocate(ValueLayout.JAVA_FLOAT);
            nativePressure.set(ValueLayout.JAVA_FLOAT, 0L, pressure.value);
            boolean success = (boolean) SDL_GET_GAMEPAD_TOUCHPAD_FINGER_HANDLE.invokeExact(
                SdlFfmSupport.segment(gamepad.address()),
                touchpad,
                finger,
                nativeDown,
                nativeX,
                nativeY,
                nativePressure
            );
            down.value = nativeDown.get(ValueLayout.JAVA_BYTE, 0L);
            x.value = nativeX.get(ValueLayout.JAVA_FLOAT, 0L);
            y.value = nativeY.get(ValueLayout.JAVA_FLOAT, 0L);
            pressure.value = nativePressure.get(ValueLayout.JAVA_FLOAT, 0L);
            return success;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadType(SdlGamepadHandle gamepad) {
        try {
            return (int) SDL_GET_GAMEPAD_TYPE_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadTypeForID(SdlJoystickId instanceId) {
        try {
            return (int) SDL_GET_GAMEPAD_TYPE_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetGamepadTypeFromString(String value) {
        try (Arena arena = Arena.ofConfined()) {
            return (int) SDL_GET_GAMEPAD_TYPE_FROM_STRING_HANDLE.invokeExact(SdlFfmSupport.utf8(value, arena));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadVendor(SdlGamepadHandle gamepad) {
        try {
            return (short) SDL_GET_GAMEPAD_VENDOR_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetGamepadVendorForID(SdlJoystickId instanceId) {
        try {
            return (short) SDL_GET_GAMEPAD_VENDOR_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickId[] SDL_GetGamepads() {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.idArray(arena, SdlFfmGamepad::SDL_GetGamepads);
        }
    }

    @Override
    public int SDL_GetNumGamepadTouchpadFingers(SdlGamepadHandle gamepad, int touchpad) {
        try {
            return (int) SDL_GET_NUM_GAMEPAD_TOUCHPAD_FINGERS_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), touchpad);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetNumGamepadTouchpads(SdlGamepadHandle gamepad) {
        try {
            return (int) SDL_GET_NUM_GAMEPAD_TOUCHPADS_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetRealGamepadType(SdlGamepadHandle gamepad) {
        try {
            return (int) SDL_GET_REAL_GAMEPAD_TYPE_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetRealGamepadTypeForID(SdlJoystickId instanceId) {
        try {
            return (int) SDL_GET_REAL_GAMEPAD_TYPE_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_HasGamepad() {
        try {
            return (boolean) SDL_HAS_GAMEPAD_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_IsGamepad(SdlJoystickId instanceId) {
        try {
            return (boolean) SDL_IS_GAMEPAD_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlGamepadHandle SDL_OpenGamepad(SdlJoystickId instanceId) {
        try {
            return new SdlGamepadHandle(((MemorySegment) SDL_OPEN_GAMEPAD_HANDLE.invokeExact(
                     instanceId.value())).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_ReloadGamepadMappings() {
        try {
            return (boolean) SDL_RELOAD_GAMEPAD_MAPPINGS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_RumbleGamepad(SdlGamepadHandle gamepad, short lowFrequencyRumble, short highFrequencyRumble, int durationMs) {
        try {
            return (boolean) SDL_RUMBLE_GAMEPAD_HANDLE.invokeExact(
                SdlFfmSupport.segment(gamepad.address()), lowFrequencyRumble, highFrequencyRumble, durationMs
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_RumbleGamepadTriggers(SdlGamepadHandle gamepad, short leftRumble, short rightRumble, int durationMs) {
        try {
            return (boolean) SDL_RUMBLE_GAMEPAD_TRIGGERS_HANDLE.invokeExact(
                SdlFfmSupport.segment(gamepad.address()), leftRumble, rightRumble, durationMs
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SendGamepadEffect(SdlGamepadHandle gamepad, ByteBuffer data) {
        try {
            return (boolean) SDL_SEND_GAMEPAD_EFFECT_HANDLE.invokeExact(
                SdlFfmSupport.segment(gamepad.address()), MemorySegment.ofBuffer(data), data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_SetGamepadEventsEnabled(boolean enabled) {
        try {
            SDL_SET_GAMEPAD_EVENTS_ENABLED_HANDLE.invokeExact(enabled);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetGamepadLED(SdlGamepadHandle gamepad, byte red, byte green, byte blue) {
        try {
            return (boolean) SDL_SET_GAMEPAD_LED_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), red, green, blue);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetGamepadMapping(SdlJoystickId instanceId, String mapping) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_GAMEPAD_MAPPING_HANDLE.invokeExact(
                instanceId.value(), SdlFfmSupport.utf8(mapping, arena)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetGamepadPlayerIndex(SdlGamepadHandle gamepad, int playerIndex) {
        try {
            return (boolean) SDL_SET_GAMEPAD_PLAYER_INDEX_HANDLE.invokeExact(SdlFfmSupport.segment(gamepad.address()), playerIndex);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetGamepadSensorEnabled(SdlGamepadHandle gamepad, int type, boolean enabled) {
        try {
            return (boolean) SDL_SET_GAMEPAD_SENSOR_ENABLED_HANDLE.invokeExact(
                SdlFfmSupport.segment(gamepad.address()), type, enabled
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_UpdateGamepads() {
        try {
            SDL_UPDATE_GAMEPADS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_GetGamepads(MemorySegment count) {
        try {
            return (MemorySegment) SDL_GET_GAMEPADS_HANDLE.invokeExact(count);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_GetGamepadBindings(MemorySegment gamepad, MemorySegment count) {
        try {
            return (MemorySegment) SDL_GET_GAMEPAD_BINDINGS_HANDLE.invokeExact(gamepad, count);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
