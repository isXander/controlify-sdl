package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlEvent;
import dev.isxander.sdl.SdlEvents;
import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlCallbacks.EventFilter;
import dev.isxander.sdl.SdlEvents.SdlEventFilterRegistration;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_INT;

final class SdlFfmEvents implements SdlEvents {
    private static final MethodHandle SDL_PUMP_EVENTS_HANDLE = SdlFfmNative.downcall(
            "SDL_PumpEvents",
            FunctionDescriptor.ofVoid());
    private static final MethodHandle SDL_PEEP_EVENTS_HANDLE = SdlFfmNative.downcall(
            "SDL_PeepEvents",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_EVENT,
                    JAVA_INT,
                    JAVA_INT,
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_HAS_EVENT_HANDLE = SdlFfmNative.downcall(
            "SDL_HasEvent",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_HAS_EVENTS_HANDLE = SdlFfmNative.downcall(
            "SDL_HasEvents",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_FLUSH_EVENT_HANDLE = SdlFfmNative.downcall(
            "SDL_FlushEvent",
            FunctionDescriptor.ofVoid(
                    JAVA_INT));
    private static final MethodHandle SDL_FLUSH_EVENTS_HANDLE = SdlFfmNative.downcall(
            "SDL_FlushEvents",
            FunctionDescriptor.ofVoid(
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_POLL_EVENT_HANDLE = SdlFfmNative.downcall(
            "SDL_PollEvent",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_EVENT));
    private static final MethodHandle SDL_WAIT_EVENT_HANDLE = SdlFfmNative.downcall(
            "SDL_WaitEvent",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_EVENT));
    private static final MethodHandle SDL_WAIT_EVENT_TIMEOUT_HANDLE = SdlFfmNative.downcall(
            "SDL_WaitEventTimeout",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_EVENT,
                    JAVA_INT));
    private static final MethodHandle SDL_PUSH_EVENT_HANDLE = SdlFfmNative.downcall(
            "SDL_PushEvent",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_EVENT));
    private static final MethodHandle SDL_SET_EVENT_FILTER_HANDLE = SdlFfmNative.downcall(
            "SDL_SetEventFilter",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_EVENT_FILTER,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_GET_EVENT_FILTER_HANDLE = SdlFfmNative.downcall(
            "SDL_GetEventFilter",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_EVENT_FILTER_POINTER,
                    SdlLayouts.VOID_POINTER_POINTER));
    private static final MethodHandle SDL_ADD_EVENT_WATCH_HANDLE = SdlFfmNative.downcall(
            "SDL_AddEventWatch",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    SdlLayouts.SDL_EVENT_FILTER,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_REMOVE_EVENT_WATCH_HANDLE = SdlFfmNative.downcall(
            "SDL_RemoveEventWatch",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_EVENT_FILTER,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_FILTER_EVENTS_HANDLE = SdlFfmNative.downcall(
            "SDL_FilterEvents",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_EVENT_FILTER,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_SET_EVENT_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_SetEventEnabled",
            FunctionDescriptor.ofVoid(
                    JAVA_INT,
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_EVENT_ENABLED_HANDLE = SdlFfmNative.downcall(
            "SDL_EventEnabled",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_REGISTER_EVENTS_HANDLE = SdlFfmNative.downcall(
            "SDL_RegisterEvents",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT));

    @Override
    public boolean SDL_AddEventWatch(EventFilter filter, SdlPointer userdata) {
        try {
            return (boolean) SDL_ADD_EVENT_WATCH_HANDLE.invokeExact(
                SdlFfmSupport.callback(filter), SdlFfmSupport.segment(userdata.address())
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_EventEnabled(int type) {
        try {
            return (boolean) SDL_EVENT_ENABLED_HANDLE.invokeExact(type);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_FilterEvents(EventFilter filter, SdlPointer userdata) {
        try {
            SDL_FILTER_EVENTS_HANDLE.invokeExact(SdlFfmSupport.callback(filter), SdlFfmSupport.segment(userdata.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_FlushEvent(int type) {
        try {
            SDL_FLUSH_EVENT_HANDLE.invokeExact(type);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_FlushEvents(int minType, int maxType) {
        try {
            SDL_FLUSH_EVENTS_HANDLE.invokeExact(minType, maxType);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlEventFilterRegistration SDL_GetEventFilter() {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.getEventFilter(arena);
        }
    }

    @Override
    public boolean SDL_HasEvent(int type) {
        try {
            return (boolean) SDL_HAS_EVENT_HANDLE.invokeExact(type);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_HasEvents(int minType, int maxType) {
        try {
            return (boolean) SDL_HAS_EVENTS_HANDLE.invokeExact(minType, maxType);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_PeepEvents(SdlEvent[] events, int action, int minType, int maxType) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.peepEvents(events, action, minType, maxType, arena);
        }
    }

    @Override
    public boolean SDL_PollEvent(SdlEvent event) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.readOneEvent(event, arena, SdlFfmEvents::SDL_PollEvent);
        }
    }

    @Override
    public void SDL_PumpEvents() {
        try {
            SDL_PUMP_EVENTS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_PushEvent(SdlEvent event) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_PUSH_EVENT_HANDLE.invokeExact(SdlEventCodec.allocate(event, arena));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_RegisterEvents(int numEvents) {
        try {
            return (int) SDL_REGISTER_EVENTS_HANDLE.invokeExact(numEvents);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_RemoveEventWatch(EventFilter filter, SdlPointer userdata) {
        try {
            SDL_REMOVE_EVENT_WATCH_HANDLE.invokeExact(SdlFfmSupport.callback(filter), SdlFfmSupport.segment(userdata.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_SetEventEnabled(int type, boolean enabled) {
        try {
            SDL_SET_EVENT_ENABLED_HANDLE.invokeExact(type, enabled);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_SetEventFilter(EventFilter filter, SdlPointer userdata) {
        try {
            SDL_SET_EVENT_FILTER_HANDLE.invokeExact(SdlFfmSupport.callback(filter), SdlFfmSupport.segment(userdata.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_WaitEvent(SdlEvent event) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.readOneEvent(event, arena, SdlFfmEvents::SDL_WaitEvent);
        }
    }

    @Override
    public boolean SDL_WaitEventTimeout(SdlEvent event, int timeoutMs) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.readOneEventTimeout(event, timeoutMs, arena);
        }
    }

    static int SDL_PeepEvents(MemorySegment events, int numEvents, int action, int minType, int maxType) {
        try {
            return (int) SDL_PEEP_EVENTS_HANDLE.invokeExact(events, numEvents, action, minType, maxType);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static boolean SDL_PollEvent(MemorySegment event) {
        try {
            return (boolean) SDL_POLL_EVENT_HANDLE.invokeExact(event);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static boolean SDL_WaitEvent(MemorySegment event) {
        try {
            return (boolean) SDL_WAIT_EVENT_HANDLE.invokeExact(event);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static boolean SDL_WaitEventTimeout(MemorySegment event, int timeoutMs) {
        try {
            return (boolean) SDL_WAIT_EVENT_TIMEOUT_HANDLE.invokeExact(event, timeoutMs);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static boolean SDL_GetEventFilter(MemorySegment filter, MemorySegment userdata) {
        try {
            return (boolean) SDL_GET_EVENT_FILTER_HANDLE.invokeExact(filter, userdata);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
