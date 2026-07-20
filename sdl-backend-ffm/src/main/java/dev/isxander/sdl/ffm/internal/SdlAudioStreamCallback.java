// FFM binding source for SDL 3.4.12.

package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;



/**
 * {@snippet lang=c :
 * typedef void (*SdlAudioStreamCallback)(void *, SDL_AudioStream *, int, int)
 * }
 */
public final class SdlAudioStreamCallback {

    private SdlAudioStreamCallback() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        void apply(MemorySegment userdata, MemorySegment stream, int additional_amount, int total_amount);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.ofVoid(
        SdlLayouts.VOID_POINTER,
        SdlLayouts.SDL_AUDIO_STREAM,
        JAVA_INT,
        JAVA_INT
    );

    /**
     * The descriptor of this function pointer
     */
    public static FunctionDescriptor descriptor() {
        return $DESC;
    }

    private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(SdlAudioStreamCallback.Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(SdlAudioStreamCallback.Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static void invoke(MemorySegment funcPtr, MemorySegment userdata, MemorySegment stream, int additional_amount, int total_amount) {
        try {
             DOWN$MH.invokeExact(funcPtr, userdata, stream, additional_amount, total_amount);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL call", throwable);
        }
    }
}
