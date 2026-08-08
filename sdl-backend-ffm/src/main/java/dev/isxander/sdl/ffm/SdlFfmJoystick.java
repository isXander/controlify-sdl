package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlGuid;
import dev.isxander.sdl.SdlJoystick;
import dev.isxander.sdl.SdlJoystickHandle;
import dev.isxander.sdl.SdlJoystickId;
import dev.isxander.sdl.SdlPropertiesId;
import dev.isxander.sdl.SdlVirtualJoystickDesc;
import dev.isxander.sdl.SdlRefs.IntRef;
import dev.isxander.sdl.SdlRefs.ShortRef;
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

final class SdlFfmJoystick implements SdlJoystick {
    private static final MethodHandle SDL_LOCK_JOYSTICKS_HANDLE = SdlFfmNative.downcall(
            "SDL_LockJoysticks",
            FunctionDescriptor.ofVoid());
    private static final MethodHandle SDL_UNLOCK_JOYSTICKS_HANDLE = SdlFfmNative.downcall(
            "SDL_UnlockJoysticks",
            FunctionDescriptor.ofVoid());
    private static final MethodHandle SDL_HAS_JOYSTICK_HANDLE = SdlFfmNative.downcall(
            "SDL_HasJoystick",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_GET_JOYSTICKS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoysticks",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_JOYSTICK_ID_POINTER,
                    SdlLayouts.INT_POINTER));
    private static final MethodHandle SDL_GET_JOYSTICK_NAME_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickNameForID",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_PATH_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickPathForID",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_PLAYER_INDEX_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickPlayerIndexForID",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_GUID_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickGUIDForID",
            FunctionDescriptor.of(
                    SdlGuidLayout.layout(),
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_VENDOR_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickVendorForID",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_PRODUCT_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickProductForID",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_PRODUCT_VERSION_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickProductVersionForID",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_TYPE_FOR_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickTypeForID",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_OPEN_JOYSTICK_HANDLE = SdlFfmNative.downcall(
            "SDL_OpenJoystick",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_FROM_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickFromID",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_FROM_PLAYER_INDEX_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickFromPlayerIndex",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT));
    private static final MethodHandle SDL_ATTACH_VIRTUAL_JOYSTICK_HANDLE = SdlFfmNative.downcall(
            "SDL_AttachVirtualJoystick",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_VIRTUAL_JOYSTICK_DESC));
    private static final MethodHandle SDL_DETACH_VIRTUAL_JOYSTICK_HANDLE = SdlFfmNative.downcall(
            "SDL_DetachVirtualJoystick",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_IS_JOYSTICK_VIRTUAL_HANDLE = SdlFfmNative.downcall(
            "SDL_IsJoystickVirtual",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_SET_JOYSTICK_VIRTUAL_AXIS_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickVirtualAxis",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT,
                    JAVA_SHORT));
    private static final MethodHandle SDL_SET_JOYSTICK_VIRTUAL_BALL_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickVirtualBall",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT,
                    JAVA_SHORT,
                    JAVA_SHORT));
    private static final MethodHandle SDL_SET_JOYSTICK_VIRTUAL_BUTTON_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickVirtualButton",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT,
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_SET_JOYSTICK_VIRTUAL_HAT_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickVirtualHat",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT,
                    JAVA_BYTE));
    private static final MethodHandle SDL_SET_JOYSTICK_VIRTUAL_TOUCHPAD_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickVirtualTouchpad",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT,
                    JAVA_INT,
                    JAVA_BOOLEAN,
                    JAVA_FLOAT,
                    JAVA_FLOAT,
                    JAVA_FLOAT));
    private static final MethodHandle SDL_SEND_JOYSTICK_VIRTUAL_SENSOR_DATA_HANDLE = SdlFfmNative.downcall(
            "SDL_SendJoystickVirtualSensorData",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT,
                    JAVA_LONG,
                    SdlLayouts.FLOAT_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickProperties",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_NAME_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickName",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_PATH_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickPath",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_PLAYER_INDEX_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickPlayerIndex",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_SET_JOYSTICK_PLAYER_INDEX_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickPlayerIndex",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_VENDOR_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickVendor",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_PRODUCT_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickProduct",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_PRODUCT_VERSION_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickProductVersion",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_FIRMWARE_VERSION_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickFirmwareVersion",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_SERIAL_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickSerial",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_TYPE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickType",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_GUID_INFO_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickGUIDInfo",
            FunctionDescriptor.ofVoid(
                    SdlGuidLayout.layout(),
                    SdlLayouts.UINT16_POINTER,
                    SdlLayouts.UINT16_POINTER,
                    SdlLayouts.UINT16_POINTER,
                    SdlLayouts.UINT16_POINTER));
    private static final MethodHandle SDL_JOYSTICK_CONNECTED_HANDLE = SdlFfmNative.downcall(
            "SDL_JoystickConnected",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_ID_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickID",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_NUM_JOYSTICK_AXES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumJoystickAxes",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_NUM_JOYSTICK_HATS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumJoystickHats",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_NUM_JOYSTICK_BUTTONS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumJoystickButtons",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_SET_JOYSTICK_EVENTS_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickEventsEnabled",
            FunctionDescriptor.ofVoid(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_JOYSTICK_EVENTS_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_JoystickEventsEnabled",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_UPDATE_JOYSTICKS_HANDLE = SdlFfmNative.downcall(
            "SDL_UpdateJoysticks",
            FunctionDescriptor.ofVoid());
    private static final MethodHandle SDL_GET_JOYSTICK_AXIS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickAxis",
            FunctionDescriptor.of(
                    JAVA_SHORT,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_AXIS_INITIAL_STATE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickAxisInitialState",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT,
                    SdlLayouts.SINT16_POINTER));
    private static final MethodHandle SDL_GET_JOYSTICK_HAT_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickHat",
            FunctionDescriptor.of(
                    JAVA_BYTE,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT));
    private static final MethodHandle SDL_GET_JOYSTICK_BUTTON_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickButton",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_INT));
    private static final MethodHandle SDL_RUMBLE_JOYSTICK_HANDLE = SdlFfmNative.downcall(
            "SDL_RumbleJoystick",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_SHORT,
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_RUMBLE_JOYSTICK_TRIGGERS_HANDLE = SdlFfmNative.downcall(
            "SDL_RumbleJoystickTriggers",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_SHORT,
                    JAVA_SHORT,
                    JAVA_INT));
    private static final MethodHandle SDL_SET_JOYSTICK_LED_HANDLE = SdlFfmNative.downcall(
            "SDL_SetJoystickLED",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    JAVA_BYTE,
                    JAVA_BYTE,
                    JAVA_BYTE));
    private static final MethodHandle SDL_SEND_JOYSTICK_EFFECT_HANDLE = SdlFfmNative.downcall(
            "SDL_SendJoystickEffect",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_JOYSTICK,
                    SdlLayouts.VOID_POINTER,
                    JAVA_INT));
    private static final MethodHandle SDL_CLOSE_JOYSTICK_HANDLE = SdlFfmNative.downcall(
            "SDL_CloseJoystick",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_CONNECTION_STATE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickConnectionState",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK));
    private static final MethodHandle SDL_GET_JOYSTICK_POWER_INFO_HANDLE = SdlFfmNative.downcall(
            "SDL_GetJoystickPowerInfo",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_JOYSTICK,
                    SdlLayouts.INT_POINTER));

    @Override
    public SdlJoystickId SDL_AttachVirtualJoystick(SdlVirtualJoystickDesc desc) {
        try (Arena arena = Arena.ofConfined()) {
            return new SdlJoystickId((int) SDL_ATTACH_VIRTUAL_JOYSTICK_HANDLE.invokeExact(
                    SdlFfmSupport.virtualJoystickDesc(desc, arena)));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_CloseJoystick(SdlJoystickHandle joystick) {
        try {
            SDL_CLOSE_JOYSTICK_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_DetachVirtualJoystick(SdlJoystickId instanceId) {
        try {
            return (boolean) SDL_DETACH_VIRTUAL_JOYSTICK_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetJoystickAxis(SdlJoystickHandle joystick, int axis) {
        try {
            return (short) SDL_GET_JOYSTICK_AXIS_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()), axis);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetJoystickAxisInitialState(SdlJoystickHandle joystick, int axis, ShortRef state) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeState = arena.allocate(ValueLayout.JAVA_SHORT);
            nativeState.set(ValueLayout.JAVA_SHORT, 0L, state.value);
            boolean success = (boolean) SDL_GET_JOYSTICK_AXIS_INITIAL_STATE_HANDLE.invokeExact(
                SdlFfmSupport.segment(joystick.address()), axis, nativeState
            );
            state.value = nativeState.get(ValueLayout.JAVA_SHORT, 0L);
            return success;
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetJoystickButton(SdlJoystickHandle joystick, int button) {
        try {
            return (boolean) SDL_GET_JOYSTICK_BUTTON_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()), button);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetJoystickConnectionState(SdlJoystickHandle joystick) {
        try {
            return (int) SDL_GET_JOYSTICK_CONNECTION_STATE_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetJoystickFirmwareVersion(SdlJoystickHandle joystick) {
        try {
            return (short) SDL_GET_JOYSTICK_FIRMWARE_VERSION_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickHandle SDL_GetJoystickFromID(SdlJoystickId instanceId) {
        try {
            return new SdlJoystickHandle(((MemorySegment) SDL_GET_JOYSTICK_FROM_ID_HANDLE.invokeExact(
                     instanceId.value())).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickHandle SDL_GetJoystickFromPlayerIndex(int playerIndex) {
        try {
            return new SdlJoystickHandle(((MemorySegment) SDL_GET_JOYSTICK_FROM_PLAYER_INDEX_HANDLE.invokeExact(
                     playerIndex)).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlGuid SDL_GetJoystickGUIDForID(SdlJoystickId instanceId) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.guid((MemorySegment) SDL_GET_JOYSTICK_GUID_FOR_ID_HANDLE.invokeExact((SegmentAllocator) arena, instanceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_GetJoystickGUIDInfo(SdlGuid guid, ShortRef vendor, ShortRef product, ShortRef version, ShortRef crc16) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeVendor = arena.allocate(ValueLayout.JAVA_SHORT);
            nativeVendor.set(ValueLayout.JAVA_SHORT, 0L, vendor.value);
            MemorySegment nativeProduct = arena.allocate(ValueLayout.JAVA_SHORT);
            nativeProduct.set(ValueLayout.JAVA_SHORT, 0L, product.value);
            MemorySegment nativeVersion = arena.allocate(ValueLayout.JAVA_SHORT);
            nativeVersion.set(ValueLayout.JAVA_SHORT, 0L, version.value);
            MemorySegment nativeCrc16 = arena.allocate(ValueLayout.JAVA_SHORT);
            nativeCrc16.set(ValueLayout.JAVA_SHORT, 0L, crc16.value);
            SDL_GET_JOYSTICK_GUID_INFO_HANDLE.invokeExact(
                SdlFfmSupport.guid(guid, arena),
                nativeVendor,
                nativeProduct,
                nativeVersion,
                nativeCrc16
            );
            vendor.value = nativeVendor.get(ValueLayout.JAVA_SHORT, 0L);
            product.value = nativeProduct.get(ValueLayout.JAVA_SHORT, 0L);
            version.value = nativeVersion.get(ValueLayout.JAVA_SHORT, 0L);
            crc16.value = nativeCrc16.get(ValueLayout.JAVA_SHORT, 0L);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public byte SDL_GetJoystickHat(SdlJoystickHandle joystick, int hat) {
        try {
            return (byte) SDL_GET_JOYSTICK_HAT_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()), hat);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickId SDL_GetJoystickID(SdlJoystickHandle joystick) {
        try {
            return new SdlJoystickId((int) SDL_GET_JOYSTICK_ID_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetJoystickName(SdlJoystickHandle joystick) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_JOYSTICK_NAME_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetJoystickNameForID(SdlJoystickId instanceId) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_JOYSTICK_NAME_FOR_ID_HANDLE.invokeExact(instanceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetJoystickPath(SdlJoystickHandle joystick) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_JOYSTICK_PATH_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetJoystickPathForID(SdlJoystickId instanceId) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_JOYSTICK_PATH_FOR_ID_HANDLE.invokeExact(instanceId.value()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetJoystickPlayerIndex(SdlJoystickHandle joystick) {
        try {
            return (int) SDL_GET_JOYSTICK_PLAYER_INDEX_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetJoystickPlayerIndexForID(SdlJoystickId instanceId) {
        try {
            return (int) SDL_GET_JOYSTICK_PLAYER_INDEX_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetJoystickPowerInfo(SdlJoystickHandle joystick, IntRef percent) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativePercent = arena.allocate(ValueLayout.JAVA_INT);
            nativePercent.set(ValueLayout.JAVA_INT, 0L, percent.value);
            int powerState = (int) SDL_GET_JOYSTICK_POWER_INFO_HANDLE.invokeExact(
                SdlFfmSupport.segment(joystick.address()), nativePercent
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
    public short SDL_GetJoystickProduct(SdlJoystickHandle joystick) {
        try {
            return (short) SDL_GET_JOYSTICK_PRODUCT_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetJoystickProductForID(SdlJoystickId instanceId) {
        try {
            return (short) SDL_GET_JOYSTICK_PRODUCT_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetJoystickProductVersion(SdlJoystickHandle joystick) {
        try {
            return (short) SDL_GET_JOYSTICK_PRODUCT_VERSION_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetJoystickProductVersionForID(SdlJoystickId instanceId) {
        try {
            return (short) SDL_GET_JOYSTICK_PRODUCT_VERSION_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPropertiesId SDL_GetJoystickProperties(SdlJoystickHandle joystick) {
        try {
            return new SdlPropertiesId((int) SDL_GET_JOYSTICK_PROPERTIES_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetJoystickSerial(SdlJoystickHandle joystick) {
        try {
            return SdlFfmSupport.string((MemorySegment) SDL_GET_JOYSTICK_SERIAL_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address())));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetJoystickType(SdlJoystickHandle joystick) {
        try {
            return (int) SDL_GET_JOYSTICK_TYPE_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetJoystickTypeForID(SdlJoystickId instanceId) {
        try {
            return (int) SDL_GET_JOYSTICK_TYPE_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetJoystickVendor(SdlJoystickHandle joystick) {
        try {
            return (short) SDL_GET_JOYSTICK_VENDOR_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public short SDL_GetJoystickVendorForID(SdlJoystickId instanceId) {
        try {
            return (short) SDL_GET_JOYSTICK_VENDOR_FOR_ID_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickId[] SDL_GetJoysticks() {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.idArray(arena, SdlFfmJoystick::SDL_GetJoysticks);
        }
    }

    @Override
    public int SDL_GetNumJoystickAxes(SdlJoystickHandle joystick) {
        try {
            return (int) SDL_GET_NUM_JOYSTICK_AXES_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetNumJoystickButtons(SdlJoystickHandle joystick) {
        try {
            return (int) SDL_GET_NUM_JOYSTICK_BUTTONS_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetNumJoystickHats(SdlJoystickHandle joystick) {
        try {
            return (int) SDL_GET_NUM_JOYSTICK_HATS_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_HasJoystick() {
        try {
            return (boolean) SDL_HAS_JOYSTICK_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_JoystickConnected(SdlJoystickHandle joystick) {
        try {
            return (boolean) SDL_JOYSTICK_CONNECTED_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_JoystickEventsEnabled() {
        try {
            return (boolean) SDL_JOYSTICK_EVENTS_ENABLED_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_IsJoystickVirtual(SdlJoystickId instanceId) {
        try {
            return (boolean) SDL_IS_JOYSTICK_VIRTUAL_HANDLE.invokeExact(instanceId.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_LockJoysticks() {
        try {
            SDL_LOCK_JOYSTICKS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlJoystickHandle SDL_OpenJoystick(SdlJoystickId instanceId) {
        try {
            return new SdlJoystickHandle(((MemorySegment) SDL_OPEN_JOYSTICK_HANDLE.invokeExact(
                     instanceId.value())).address());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_RumbleJoystick(SdlJoystickHandle joystick, short lowFrequencyRumble, short highFrequencyRumble, int durationMs) {
        try {
            return (boolean) SDL_RUMBLE_JOYSTICK_HANDLE.invokeExact(
                SdlFfmSupport.segment(joystick.address()), lowFrequencyRumble, highFrequencyRumble, durationMs
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_RumbleJoystickTriggers(SdlJoystickHandle joystick, short leftRumble, short rightRumble, int durationMs) {
        try {
            return (boolean) SDL_RUMBLE_JOYSTICK_TRIGGERS_HANDLE.invokeExact(
                SdlFfmSupport.segment(joystick.address()), leftRumble, rightRumble, durationMs
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SendJoystickEffect(SdlJoystickHandle joystick, ByteBuffer data) {
        try {
            return (boolean) SDL_SEND_JOYSTICK_EFFECT_HANDLE.invokeExact(
                SdlFfmSupport.segment(joystick.address()), MemorySegment.ofBuffer(data), data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SendJoystickVirtualSensorData(SdlJoystickHandle joystick, int type,
                                                     long sensorTimestamp, FloatBuffer data) {
        try {
            return (boolean) SDL_SEND_JOYSTICK_VIRTUAL_SENSOR_DATA_HANDLE.invokeExact(
                    SdlFfmSupport.segment(joystick.address()), type, sensorTimestamp,
                    MemorySegment.ofBuffer(data), data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_SetJoystickEventsEnabled(boolean enabled) {
        try {
            SDL_SET_JOYSTICK_EVENTS_ENABLED_HANDLE.invokeExact(enabled);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetJoystickLED(SdlJoystickHandle joystick, byte red, byte green, byte blue) {
        try {
            return (boolean) SDL_SET_JOYSTICK_LED_HANDLE.invokeExact(
                SdlFfmSupport.segment(joystick.address()), red, green, blue
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetJoystickPlayerIndex(SdlJoystickHandle joystick, int playerIndex) {
        try {
            return (boolean) SDL_SET_JOYSTICK_PLAYER_INDEX_HANDLE.invokeExact(SdlFfmSupport.segment(joystick.address()), playerIndex);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetJoystickVirtualAxis(SdlJoystickHandle joystick, int axis, short value) {
        try {
            return (boolean) SDL_SET_JOYSTICK_VIRTUAL_AXIS_HANDLE.invokeExact(
                    SdlFfmSupport.segment(joystick.address()), axis, value);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetJoystickVirtualBall(SdlJoystickHandle joystick, int ball, short xrel, short yrel) {
        try {
            return (boolean) SDL_SET_JOYSTICK_VIRTUAL_BALL_HANDLE.invokeExact(
                    SdlFfmSupport.segment(joystick.address()), ball, xrel, yrel);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetJoystickVirtualButton(SdlJoystickHandle joystick, int button, boolean down) {
        try {
            return (boolean) SDL_SET_JOYSTICK_VIRTUAL_BUTTON_HANDLE.invokeExact(
                    SdlFfmSupport.segment(joystick.address()), button, down);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetJoystickVirtualHat(SdlJoystickHandle joystick, int hat, byte value) {
        try {
            return (boolean) SDL_SET_JOYSTICK_VIRTUAL_HAT_HANDLE.invokeExact(
                    SdlFfmSupport.segment(joystick.address()), hat, value);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetJoystickVirtualTouchpad(SdlJoystickHandle joystick, int touchpad, int finger,
                                                  boolean down, float x, float y, float pressure) {
        try {
            return (boolean) SDL_SET_JOYSTICK_VIRTUAL_TOUCHPAD_HANDLE.invokeExact(
                    SdlFfmSupport.segment(joystick.address()), touchpad, finger, down, x, y, pressure);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_UnlockJoysticks() {
        try {
            SDL_UNLOCK_JOYSTICKS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_UpdateJoysticks() {
        try {
            SDL_UPDATE_JOYSTICKS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_GetJoysticks(MemorySegment count) {
        try {
            return (MemorySegment) SDL_GET_JOYSTICKS_HANDLE.invokeExact(count);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
