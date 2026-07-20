package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlGuid;
import dev.isxander.sdl.SdlGuidApi;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlGuidLayout;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;

final class SdlFfmGuidApi implements SdlGuidApi {
    private static final MethodHandle SDL_GUID_TO_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_GUIDToString",
            FunctionDescriptor.ofVoid(
                    SdlGuidLayout.layout(),
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT));
    private static final MethodHandle SDL_STRING_TO_GUID_HANDLE = SdlFfmNative.downcall(
            "SDL_StringToGUID",
            FunctionDescriptor.of(
                    SdlGuidLayout.layout(),
                    SdlLayouts.UTF8_STRING));

    @Override
    public String SDL_GUIDToString(SdlGuid guid) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.guidToString(guid, arena);
        }
    }

    @Override
    public SdlGuid SDL_StringToGUID(String guid) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.guid(
                (MemorySegment) SDL_STRING_TO_GUID_HANDLE.invokeExact(
                    (SegmentAllocator) arena, SdlFfmSupport.utf8(guid, arena)
                )
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static void SDL_GUIDToString(MemorySegment guid, MemorySegment output, int outputLength) {
        try {
            SDL_GUID_TO_STRING_HANDLE.invokeExact(guid, output, outputLength);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
