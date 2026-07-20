package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.EventFilter;

public interface SdlEvents {
    byte SDL_RELEASED = 0;
    byte SDL_PRESSED = 1;
    int SDL_ADDEVENT = 0;
    int SDL_PEEKEVENT = 1;
    int SDL_GETEVENT = 2;

    int SDL_EVENT_FIRST = 0;
    int SDL_EVENT_QUIT = 0x100;
    int SDL_EVENT_TERMINATING = 0x101;
    int SDL_EVENT_LOW_MEMORY = 0x102;
    int SDL_EVENT_WILL_ENTER_BACKGROUND = 0x103;
    int SDL_EVENT_DID_ENTER_BACKGROUND = 0x104;
    int SDL_EVENT_WILL_ENTER_FOREGROUND = 0x105;
    int SDL_EVENT_DID_ENTER_FOREGROUND = 0x106;
    int SDL_EVENT_LOCALE_CHANGED = 0x107;
    int SDL_EVENT_SYSTEM_THEME_CHANGED = 0x108;

    int SDL_EVENT_KEY_DOWN = 0x300;
    int SDL_EVENT_KEY_UP = 0x301;
    int SDL_EVENT_TEXT_EDITING = 0x302;
    int SDL_EVENT_TEXT_INPUT = 0x303;
    int SDL_EVENT_KEYMAP_CHANGED = 0x304;
    int SDL_EVENT_MOUSE_MOTION = 0x400;
    int SDL_EVENT_MOUSE_BUTTON_DOWN = 0x401;
    int SDL_EVENT_MOUSE_BUTTON_UP = 0x402;
    int SDL_EVENT_MOUSE_WHEEL = 0x403;

    int SDL_EVENT_JOYSTICK_AXIS_MOTION = 0x600;
    int SDL_EVENT_JOYSTICK_BALL_MOTION = 0x601;
    int SDL_EVENT_JOYSTICK_HAT_MOTION = 0x602;
    int SDL_EVENT_JOYSTICK_BUTTON_DOWN = 0x603;
    int SDL_EVENT_JOYSTICK_BUTTON_UP = 0x604;
    int SDL_EVENT_JOYSTICK_ADDED = 0x605;
    int SDL_EVENT_JOYSTICK_REMOVED = 0x606;
    int SDL_EVENT_JOYSTICK_BATTERY_UPDATED = 0x607;
    int SDL_EVENT_JOYSTICK_UPDATE_COMPLETE = 0x608;

    int SDL_EVENT_GAMEPAD_AXIS_MOTION = 0x650;
    int SDL_EVENT_GAMEPAD_BUTTON_DOWN = 0x651;
    int SDL_EVENT_GAMEPAD_BUTTON_UP = 0x652;
    int SDL_EVENT_GAMEPAD_ADDED = 0x653;
    int SDL_EVENT_GAMEPAD_REMOVED = 0x654;
    int SDL_EVENT_GAMEPAD_REMAPPED = 0x655;
    int SDL_EVENT_GAMEPAD_TOUCHPAD_DOWN = 0x656;
    int SDL_EVENT_GAMEPAD_TOUCHPAD_MOTION = 0x657;
    int SDL_EVENT_GAMEPAD_TOUCHPAD_UP = 0x658;
    int SDL_EVENT_GAMEPAD_SENSOR_UPDATE = 0x659;
    int SDL_EVENT_GAMEPAD_UPDATE_COMPLETE = 0x65A;
    int SDL_EVENT_GAMEPAD_STEAM_HANDLE_UPDATED = 0x65B;

    int SDL_EVENT_FINGER_DOWN = 0x700;
    int SDL_EVENT_FINGER_UP = 0x701;
    int SDL_EVENT_FINGER_MOTION = 0x702;
    int SDL_EVENT_CLIPBOARD_UPDATE = 0x900;
    int SDL_EVENT_DROP_FILE = 0x1000;
    int SDL_EVENT_DROP_TEXT = 0x1001;
    int SDL_EVENT_DROP_BEGIN = 0x1002;
    int SDL_EVENT_DROP_COMPLETE = 0x1003;
    int SDL_EVENT_DROP_POSITION = 0x1004;
    int SDL_EVENT_AUDIO_DEVICE_ADDED = 0x1100;
    int SDL_EVENT_AUDIO_DEVICE_REMOVED = 0x1101;
    int SDL_EVENT_AUDIO_DEVICE_FORMAT_CHANGED = 0x1102;
    int SDL_EVENT_SENSOR_UPDATE = 0x1200;
    int SDL_EVENT_POLL_SENTINEL = 0x7F00;
    int SDL_EVENT_USER = 0x8000;
    int SDL_EVENT_LAST = 0xFFFF;

    void SDL_PumpEvents();

    int SDL_PeepEvents(SdlEvent[] events, int action, int minType, int maxType);

    boolean SDL_HasEvent(int type);

    boolean SDL_HasEvents(int minType, int maxType);

    void SDL_FlushEvent(int type);

    void SDL_FlushEvents(int minType, int maxType);

    boolean SDL_PollEvent(SdlEvent event);

    boolean SDL_WaitEvent(SdlEvent event);

    boolean SDL_WaitEventTimeout(SdlEvent event, int timeoutMs);

    boolean SDL_PushEvent(SdlEvent event);

    void SDL_SetEventFilter(EventFilter filter, SdlPointer userdata);

    SdlEventFilterRegistration SDL_GetEventFilter();

    boolean SDL_AddEventWatch(EventFilter filter, SdlPointer userdata);

    void SDL_RemoveEventWatch(EventFilter filter, SdlPointer userdata);

    void SDL_FilterEvents(EventFilter filter, SdlPointer userdata);

    void SDL_SetEventEnabled(int type, boolean enabled);

    boolean SDL_EventEnabled(int type);

    int SDL_RegisterEvents(int numEvents);

    record SdlEventFilterRegistration(EventFilter filter, SdlPointer userdata) {
    }
}
