package dev.isxander.sdl;

import dev.isxander.sdl.SdlCallbacks.EventFilter;

public interface SdlEvents {
    byte SDL_RELEASED = 0;
    byte SDL_PRESSED = 1;
    /// Add events to the back of the queue.
    int SDL_ADDEVENT = 0;
    /// Check but don't remove events from the queue front.
    int SDL_PEEKEVENT = 1;
    /// Retrieve/remove events from the front of the queue.
    int SDL_GETEVENT = 2;

    /// Unused (do not remove)
    int SDL_EVENT_FIRST = 0;
    /// User-requested quit
    int SDL_EVENT_QUIT = 0x100;
    /// The application is being terminated by the OS.
    int SDL_EVENT_TERMINATING = 0x101;
    /// The application is low on memory, free memory if possible.
    int SDL_EVENT_LOW_MEMORY = 0x102;
    /// The application is about to enter the background.
    int SDL_EVENT_WILL_ENTER_BACKGROUND = 0x103;
    /// The application did enter the background and may not get CPU for some time.
    int SDL_EVENT_DID_ENTER_BACKGROUND = 0x104;
    /// The application is about to enter the foreground.
    int SDL_EVENT_WILL_ENTER_FOREGROUND = 0x105;
    /// The application is now interactive.
    int SDL_EVENT_DID_ENTER_FOREGROUND = 0x106;
    /// The user's locale preferences have changed.
    int SDL_EVENT_LOCALE_CHANGED = 0x107;
    /// The system theme changed
    int SDL_EVENT_SYSTEM_THEME_CHANGED = 0x108;

    /// Key pressed
    int SDL_EVENT_KEY_DOWN = 0x300;
    /// Key released
    int SDL_EVENT_KEY_UP = 0x301;
    /// Keyboard text editing (composition)
    int SDL_EVENT_TEXT_EDITING = 0x302;
    /// Keyboard text input
    int SDL_EVENT_TEXT_INPUT = 0x303;
    /// Keymap changed due to a system event such as an input language or keyboard layout change.
    int SDL_EVENT_KEYMAP_CHANGED = 0x304;
    /// Mouse moved
    int SDL_EVENT_MOUSE_MOTION = 0x400;
    /// Mouse button pressed
    int SDL_EVENT_MOUSE_BUTTON_DOWN = 0x401;
    /// Mouse button released
    int SDL_EVENT_MOUSE_BUTTON_UP = 0x402;
    /// Mouse wheel motion
    int SDL_EVENT_MOUSE_WHEEL = 0x403;

    /// Joystick axis motion
    int SDL_EVENT_JOYSTICK_AXIS_MOTION = 0x600;
    /// Joystick trackball motion
    int SDL_EVENT_JOYSTICK_BALL_MOTION = 0x601;
    /// Joystick hat position change
    int SDL_EVENT_JOYSTICK_HAT_MOTION = 0x602;
    /// Joystick button pressed
    int SDL_EVENT_JOYSTICK_BUTTON_DOWN = 0x603;
    /// Joystick button released
    int SDL_EVENT_JOYSTICK_BUTTON_UP = 0x604;
    /// A new joystick has been inserted into the system
    int SDL_EVENT_JOYSTICK_ADDED = 0x605;
    /// An opened joystick has been removed
    int SDL_EVENT_JOYSTICK_REMOVED = 0x606;
    /// Joystick battery level change
    int SDL_EVENT_JOYSTICK_BATTERY_UPDATED = 0x607;
    /// Joystick update is complete
    int SDL_EVENT_JOYSTICK_UPDATE_COMPLETE = 0x608;

    /// Gamepad axis motion
    int SDL_EVENT_GAMEPAD_AXIS_MOTION = 0x650;
    /// Gamepad button pressed
    int SDL_EVENT_GAMEPAD_BUTTON_DOWN = 0x651;
    /// Gamepad button released
    int SDL_EVENT_GAMEPAD_BUTTON_UP = 0x652;
    /// A new gamepad has been inserted into the system
    int SDL_EVENT_GAMEPAD_ADDED = 0x653;
    /// A gamepad has been removed
    int SDL_EVENT_GAMEPAD_REMOVED = 0x654;
    /// The gamepad mapping was updated
    int SDL_EVENT_GAMEPAD_REMAPPED = 0x655;
    /// Gamepad touchpad was touched
    int SDL_EVENT_GAMEPAD_TOUCHPAD_DOWN = 0x656;
    /// Gamepad touchpad finger was moved
    int SDL_EVENT_GAMEPAD_TOUCHPAD_MOTION = 0x657;
    /// Gamepad touchpad finger was lifted
    int SDL_EVENT_GAMEPAD_TOUCHPAD_UP = 0x658;
    /// Gamepad sensor was updated
    int SDL_EVENT_GAMEPAD_SENSOR_UPDATE = 0x659;
    /// Gamepad update is complete
    int SDL_EVENT_GAMEPAD_UPDATE_COMPLETE = 0x65A;
    /// Gamepad Steam handle has changed
    int SDL_EVENT_GAMEPAD_STEAM_HANDLE_UPDATED = 0x65B;

    int SDL_EVENT_FINGER_DOWN = 0x700;
    int SDL_EVENT_FINGER_UP = 0x701;
    int SDL_EVENT_FINGER_MOTION = 0x702;
    /// The clipboard changed
    int SDL_EVENT_CLIPBOARD_UPDATE = 0x900;
    /// The system requests a file open
    int SDL_EVENT_DROP_FILE = 0x1000;
    /// text/plain drag-and-drop event
    int SDL_EVENT_DROP_TEXT = 0x1001;
    /// A new set of drops is beginning (NULL filename)
    int SDL_EVENT_DROP_BEGIN = 0x1002;
    /// Current set of drops is now complete (NULL filename)
    int SDL_EVENT_DROP_COMPLETE = 0x1003;
    /// Position while moving over the window
    int SDL_EVENT_DROP_POSITION = 0x1004;
    /// A new audio device is available
    int SDL_EVENT_AUDIO_DEVICE_ADDED = 0x1100;
    /// An audio device has been removed.
    int SDL_EVENT_AUDIO_DEVICE_REMOVED = 0x1101;
    /// An audio device's format has been changed by the system.
    int SDL_EVENT_AUDIO_DEVICE_FORMAT_CHANGED = 0x1102;
    /// A sensor was updated
    int SDL_EVENT_SENSOR_UPDATE = 0x1200;
    /// Signals the end of an event poll cycle
    int SDL_EVENT_POLL_SENTINEL = 0x7F00;
    /// Events SDL_EVENT_USER through SDL_EVENT_LAST are for your use,
    /// and should be allocated with SDL_RegisterEvents()
    int SDL_EVENT_USER = 0x8000;
    /// This last event is only for bounding internal arrays
    int SDL_EVENT_LAST = 0xFFFF;

    /// Pump the event loop, gathering events from the input devices.
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_PumpEvents();

    /// Check the event queue for messages and optionally return them.
    ///
    /// @param events destination buffer for the retrieved events, may be NULL to
    ///               leave events in the queue and return the number of events
    ///               that would have been stored.
    /// @param action action to take; see [Remarks](#remarks) for details.
    /// @param minType minimum value of the event type to be considered;
    ///                SDL_EVENT_FIRST is a safe choice.
    /// @param maxType maximum value of the event type to be considered;
    ///                SDL_EVENT_LAST is a safe choice.
    /// @return the number of events actually stored or -1 on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    int SDL_PeepEvents(SdlEvent[] events, int action, int minType, int maxType);

    /// Check for the existence of a certain event type in the event queue.
    ///
    /// @param type the type of event to be queried; see SDL_EventType for details.
    /// @return true if events matching `type` are present, or false if events
    ///         matching `type` are not present.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_HasEvent(int type);

    /// Check for the existence of certain event types in the event queue.
    ///
    /// @param minType the low end of event type to be queried, inclusive; see
    ///                SDL_EventType for details.
    /// @param maxType the high end of event type to be queried, inclusive; see
    ///                SDL_EventType for details.
    /// @return true if events with type >= `minType` and <= `maxType` are
    ///         present, or false if not.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_HasEvents(int minType, int maxType);

    /// Clear events of a specific type from the event queue.
    ///
    /// @param type the type of event to be cleared; see SDL_EventType for details.
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_FlushEvent(int type);

    /// Clear events of a range of types from the event queue.
    ///
    /// @param minType the low end of event type to be cleared, inclusive; see
    ///                SDL_EventType for details.
    /// @param maxType the high end of event type to be cleared, inclusive; see
    ///                SDL_EventType for details.
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_FlushEvents(int minType, int maxType);

    /// Poll for currently pending events.
    ///
    /// @param event the SDL_Event structure to be filled with the next event from
    ///              the queue, or NULL.
    /// @return true if this got an event or false if there are none available.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_PollEvent(SdlEvent event);

    /// Wait indefinitely for the next available event.
    ///
    /// @param event the SDL_Event structure to be filled in with the next event
    ///              from the queue, or NULL.
    /// @return true on success or false if there was an error while waiting for
    ///         events; call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_WaitEvent(SdlEvent event);

    /// Wait until the specified timeout (in milliseconds) for the next available
    /// event.
    ///
    /// @param event the SDL_Event structure to be filled in with the next event
    ///              from the queue, or NULL.
    /// @param timeoutMs the maximum number of milliseconds to wait for the next
    ///                  available event.
    /// @return true if this got an event or false if the timeout elapsed without
    ///         any events available.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_WaitEventTimeout(SdlEvent event, int timeoutMs);

    /// Add an event to the event queue.
    ///
    /// @param event the SDL_Event to be added to the queue.
    /// @return true on success, false if the event was filtered or on failure;
    ///         call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_PushEvent(SdlEvent event);

    /// Set up a filter to process all events before they are added to the internal
    /// event queue.
    ///
    /// @param filter a function to call when an event happens.
    /// @param userdata a pointer that is passed to `filter`.
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_SetEventFilter(EventFilter filter, SdlPointer userdata);

    /// Query the current event filter.
    ///
    /// @return true on success or false if there is no event filter set.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlEventFilterRegistration SDL_GetEventFilter();

    /// Add a callback to be triggered when an event is added to the event queue.
    ///
    /// @param filter an SDL_EventFilter function to call when an event happens.
    /// @param userdata a pointer that is passed to `filter`.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_AddEventWatch(EventFilter filter, SdlPointer userdata);

    /// Remove an event watch callback added with SDL_AddEventWatch().
    ///
    /// @param filter the function originally passed to SDL_AddEventWatch().
    /// @param userdata the pointer originally passed to SDL_AddEventWatch().
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_RemoveEventWatch(EventFilter filter, SdlPointer userdata);

    /// Run a specific filter function on the current event queue, removing any
    /// events for which the filter returns false.
    ///
    /// @param filter the SDL_EventFilter function to call when an event happens.
    /// @param userdata a pointer that is passed to `filter`.
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_FilterEvents(EventFilter filter, SdlPointer userdata);

    /// Set the state of processing events by type.
    ///
    /// @param type the type of event; see SDL_EventType for details.
    /// @param enabled whether to process the event or not.
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_SetEventEnabled(int type, boolean enabled);

    /// Query the state of processing events by type.
    ///
    /// @param type the type of event; see SDL_EventType for details.
    /// @return true if the event is being processed, false otherwise.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_EventEnabled(int type);

    /// Allocate a set of user-defined events, and return the beginning event
    /// number for that set of events.
    ///
    /// @param numEvents the number of events to be allocated.
    /// @return the beginning event number, or 0 if numevents is invalid or if
    ///         there are not enough user-defined events left.
    ///
    /// @since This function is available since SDL 3.2.0.
    int SDL_RegisterEvents(int numEvents);

    record SdlEventFilterRegistration(EventFilter filter, SdlPointer userdata) {
    }
}
