package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.AddressLayout;
import java.lang.foreign.MemoryLayout;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_FLOAT;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
import static java.lang.foreign.ValueLayout.JAVA_SHORT;

/**
 * Semantic address layouts for the pointer types used by the mapped SDL API.
 * Opaque SDL handles are still native addresses, but retain their C type in
 * descriptors instead of collapsing to an anonymous {@code void *} layout.
 */
public final class SdlLayouts {
    public static final AddressLayout VOID_POINTER = pointer("void *");
    public static final AddressLayout VOID_POINTER_POINTER = pointer("void **", VOID_POINTER);
    public static final AddressLayout UTF8_STRING = pointer("char *", JAVA_BYTE);
    public static final AddressLayout WIDE_STRING = pointer("wchar_t *");
    public static final AddressLayout UINT8_POINTER = pointer("Uint8 *", JAVA_BYTE);
    public static final AddressLayout UINT8_POINTER_POINTER = pointer("Uint8 **", UINT8_POINTER);
    public static final AddressLayout SINT16_POINTER = pointer("Sint16 *", JAVA_SHORT);
    public static final AddressLayout UINT16_POINTER = pointer("Uint16 *", JAVA_SHORT);
    public static final AddressLayout INT_POINTER = pointer("int *", JAVA_INT);
    public static final AddressLayout UINT32_POINTER = pointer("Uint32 *", JAVA_INT);
    public static final AddressLayout SIZE_POINTER = pointer("size_t *", JAVA_LONG);
    public static final AddressLayout FLOAT_POINTER = pointer("float *", JAVA_FLOAT);
    public static final AddressLayout BOOL_POINTER = pointer("bool *", JAVA_BOOLEAN);

    public static final AddressLayout SDL_AUDIO_STREAM = pointer("SDL_AudioStream *");
    public static final AddressLayout SDL_AUDIO_STREAM_ARRAY =
            pointer("SDL_AudioStream **", SDL_AUDIO_STREAM);
    public static final AddressLayout SDL_GAMEPAD = pointer("SDL_Gamepad *");
    public static final AddressLayout SDL_HID_DEVICE = pointer("SDL_hid_device *");
    public static final AddressLayout SDL_IO_STREAM = pointer("SDL_IOStream *");
    public static final AddressLayout SDL_JOYSTICK = pointer("SDL_Joystick *");
    public static final AddressLayout SDL_WINDOW = pointer("SDL_Window *");

    public static final AddressLayout SDL_AUDIO_SPEC = pointer("SDL_AudioSpec *");
    public static final AddressLayout SDL_EVENT = pointer("SDL_Event *");
    public static final AddressLayout SDL_GAMEPAD_BINDING = pointer("SDL_GamepadBinding *");
    public static final AddressLayout SDL_GAMEPAD_BINDING_ARRAY =
            pointer("SDL_GamepadBinding **", SDL_GAMEPAD_BINDING);
    public static final AddressLayout SDL_HID_DEVICE_INFO = pointer("SDL_hid_device_info *");
    public static final AddressLayout SDL_IO_STREAM_INTERFACE = pointer("SDL_IOStreamInterface *");

    public static final AddressLayout SDL_AUDIO_DEVICE_ID_POINTER =
            pointer("SDL_AudioDeviceID *", JAVA_INT);
    public static final AddressLayout SDL_JOYSTICK_ID_POINTER =
            pointer("SDL_JoystickID *", JAVA_INT);
    public static final AddressLayout SDL_IO_STATUS_POINTER =
            pointer("SDL_IOStatus *", JAVA_INT);
    public static final AddressLayout SDL_EVENT_FILTER = pointer("SDL_EventFilter");
    public static final AddressLayout SDL_EVENT_FILTER_POINTER =
            pointer("SDL_EventFilter *", SDL_EVENT_FILTER);
    public static final AddressLayout SDL_AUDIO_STREAM_CALLBACK =
            pointer("SDL_AudioStreamCallback");
    public static final AddressLayout SDL_AUDIO_POSTMIX_CALLBACK =
            pointer("SDL_AudioPostmixCallback");
    public static final AddressLayout SDL_CLEANUP_PROPERTY_CALLBACK =
            pointer("SDL_CleanupPropertyCallback");
    public static final AddressLayout SDL_ENUMERATE_PROPERTIES_CALLBACK =
            pointer("SDL_EnumeratePropertiesCallback");
    public static final AddressLayout SDL_IO_SIZE_CALLBACK = pointer("SDL_IOStreamInterface.size");
    public static final AddressLayout SDL_IO_SEEK_CALLBACK = pointer("SDL_IOStreamInterface.seek");
    public static final AddressLayout SDL_IO_READ_CALLBACK = pointer("SDL_IOStreamInterface.read");
    public static final AddressLayout SDL_IO_WRITE_CALLBACK = pointer("SDL_IOStreamInterface.write");
    public static final AddressLayout SDL_IO_FLUSH_CALLBACK = pointer("SDL_IOStreamInterface.flush");
    public static final AddressLayout SDL_IO_CLOSE_CALLBACK = pointer("SDL_IOStreamInterface.close");

    private SdlLayouts() {
    }

    private static AddressLayout pointer(String name) {
        return ADDRESS.withName(name);
    }

    private static AddressLayout pointer(String name, MemoryLayout target) {
        return ADDRESS.withTargetLayout(target).withName(name);
    }
}
