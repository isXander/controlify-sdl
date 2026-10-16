package dev.isxander.sdl;

public interface SdlTimer {
    long SDL_GetTicks();

    long SDL_GetTicksNS();

    long SDL_GetPerformanceCounter();

    long SDL_GetPerformanceFrequency();

    void SDL_Delay(int ms);

    void SDL_DelayNS(long ns);

    void SDL_DelayPrecise(long ns);

    SdlTimerId SDL_AddTimer(int interval, SdlCallbacks.TimerCallback callback, SdlPointer userdata);

    SdlTimerId SDL_AddTimerNS(long interval, SdlCallbacks.NSTimerCallback callback, SdlPointer userdata);

    boolean SDL_RemoveTimer(SdlTimerId timer);
}
