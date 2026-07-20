// FFM binding source for SDL 3.4.12.

package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;



/**
 * {@snippet lang=c :
 * typedef bool (*SdlEventFilter)(void *, SdlEventLayout *)
 * }
 */
public final class SdlEventFilter {

    private SdlEventFilter() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        boolean apply(MemorySegment userdata, MemorySegment event);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
        JAVA_BOOLEAN,
        SdlLayouts.VOID_POINTER,
        SdlLayouts.SDL_EVENT
    );

    /**
     * The descriptor of this function pointer
     */
    public static FunctionDescriptor descriptor() {
        return $DESC;
    }

    private static final MethodHandle UP$MH = SdlFfmNative.upcallHandle(SdlEventFilter.Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(SdlEventFilter.Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static boolean invoke(MemorySegment funcPtr, MemorySegment userdata, MemorySegment event) {
        try {
            return (boolean) DOWN$MH.invokeExact(funcPtr, userdata, event);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL call", throwable);
        }
    }
}
