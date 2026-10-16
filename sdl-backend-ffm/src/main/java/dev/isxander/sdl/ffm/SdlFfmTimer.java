package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlCallbacks;
import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlTimer;
import dev.isxander.sdl.SdlTimerId;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

final class SdlFfmTimer implements SdlTimer {
    private static final MethodHandle SDL_GET_TICKS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetTicks",
            FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG));
    private static final MethodHandle SDL_GET_TICKS_NS_HANDLE = SdlFfmNative.downcall(
            "SDL_GetTicksNS",
            FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG));
    private static final MethodHandle SDL_GET_PERFORMANCE_COUNTER_HANDLE = SdlFfmNative.downcall(
            "SDL_GetPerformanceCounter",
            FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG));
    private static final MethodHandle SDL_GET_PERFORMANCE_FREQUENCY_HANDLE = SdlFfmNative.downcall(
            "SDL_GetPerformanceFrequency",
            FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG));
    private static final MethodHandle SDL_DELAY_HANDLE = SdlFfmNative.downcall(
            "SDL_Delay",
            FunctionDescriptor.ofVoid(
                    ValueLayout.JAVA_INT));
    private static final MethodHandle SDL_DELAY_NS_HANDLE = SdlFfmNative.downcall(
            "SDL_DelayNS",
            FunctionDescriptor.ofVoid(
                    ValueLayout.JAVA_LONG));
    private static final MethodHandle SDL_DELAY_PRECISE_HANDLE = SdlFfmNative.downcall(
            "SDL_DelayPrecise",
            FunctionDescriptor.ofVoid(
                    ValueLayout.JAVA_LONG));
    private static final MethodHandle SDL_ADD_TIMER_HANDLE = SdlFfmNative.downcall(
            "SDL_AddTimer",
            FunctionDescriptor.of(
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    SdlLayouts.SDL_TIMER_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_ADD_TIMER_NS_HANDLE = SdlFfmNative.downcall(
            "SDL_AddTimerNS",
            FunctionDescriptor.of(
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_LONG,
                    SdlLayouts.SDL_NS_TIMER_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_REMOVE_TIMER_HANDLE = SdlFfmNative.downcall(
            "SDL_RemoveTimer",
            FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT));

    @Override
    public long SDL_GetTicks() {
        try {
            return (long) SDL_GET_TICKS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_GetTicksNS() {
        try {
            return (long) SDL_GET_TICKS_NS_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_GetPerformanceCounter() {
        try {
            return (long) SDL_GET_PERFORMANCE_COUNTER_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_GetPerformanceFrequency() {
        try {
            return (long) SDL_GET_PERFORMANCE_FREQUENCY_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_Delay(int ms) {
        try {
            SDL_DELAY_HANDLE.invokeExact(ms);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_DelayNS(long ns) {
        try {
            SDL_DELAY_NS_HANDLE.invokeExact(ns);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_DelayPrecise(long ns) {
        try {
            SDL_DELAY_PRECISE_HANDLE.invokeExact(ns);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlTimerId SDL_AddTimer(int interval, SdlCallbacks.TimerCallback callback, SdlPointer userdata) {
        try {
            return new SdlTimerId(
                    (int) SDL_ADD_TIMER_HANDLE.invokeExact(
                            interval,
                            SdlFfmSupport.callback(callback),
                            SdlFfmSupport.segment(userdata.address())
                    )
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlTimerId SDL_AddTimerNS(long interval, SdlCallbacks.NSTimerCallback callback, SdlPointer userdata) {
        try {
            return new SdlTimerId(
                    (int) SDL_ADD_TIMER_NS_HANDLE.invokeExact(
                            interval,
                            SdlFfmSupport.callback(callback),
                            SdlFfmSupport.segment(userdata.address())
                    )
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_RemoveTimer(SdlTimerId timer) {
        try {
            return (boolean) SDL_REMOVE_TIMER_HANDLE.invokeExact(timer.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
