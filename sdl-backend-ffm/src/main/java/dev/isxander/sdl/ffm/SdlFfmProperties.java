package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlPointer;
import dev.isxander.sdl.SdlProperties;
import dev.isxander.sdl.SdlPropertiesId;
import dev.isxander.sdl.SdlCallbacks.CleanupPropertyCallback;
import dev.isxander.sdl.SdlCallbacks.EnumeratePropertiesCallback;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_FLOAT;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class SdlFfmProperties implements SdlProperties {
    private static final MethodHandle SDL_GET_GLOBAL_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_GetGlobalProperties",
            FunctionDescriptor.of(
                    JAVA_INT));
    private static final MethodHandle SDL_CREATE_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_CreateProperties",
            FunctionDescriptor.of(
                    JAVA_INT));
    private static final MethodHandle SDL_COPY_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_CopyProperties",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    JAVA_INT));
    private static final MethodHandle SDL_LOCK_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_LockProperties",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT));
    private static final MethodHandle SDL_UNLOCK_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_UnlockProperties",
            FunctionDescriptor.ofVoid(
                    JAVA_INT));
    private static final MethodHandle SDL_SET_POINTER_PROPERTY_WITH_CLEANUP_HANDLE = SdlFfmNative.downcall(
            "SDL_SetPointerPropertyWithCleanup",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.VOID_POINTER,
                    SdlLayouts.SDL_CLEANUP_PROPERTY_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_SET_POINTER_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_SetPointerProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_SET_STRING_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_SetStringProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_SET_NUMBER_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_SetNumberProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    JAVA_LONG));
    private static final MethodHandle SDL_SET_FLOAT_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_SetFloatProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    JAVA_FLOAT));
    private static final MethodHandle SDL_SET_BOOLEAN_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_SetBooleanProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_HAS_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_HasProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_PROPERTY_TYPE_HANDLE = SdlFfmNative.downcall(
            "SDL_GetPropertyType",
            FunctionDescriptor.of(
                    JAVA_INT,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_POINTER_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_GetPointerProperty",
            FunctionDescriptor.of(
                    SdlLayouts.VOID_POINTER,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_GET_STRING_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_GetStringProperty",
            FunctionDescriptor.of(
                    SdlLayouts.UTF8_STRING,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_GET_NUMBER_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_GetNumberProperty",
            FunctionDescriptor.of(
                    JAVA_LONG,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    JAVA_LONG));
    private static final MethodHandle SDL_GET_FLOAT_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_GetFloatProperty",
            FunctionDescriptor.of(
                    JAVA_FLOAT,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    JAVA_FLOAT));
    private static final MethodHandle SDL_GET_BOOLEAN_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_GetBooleanProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING,
                    JAVA_BOOLEAN));
    private static final MethodHandle SDL_CLEAR_PROPERTY_HANDLE = SdlFfmNative.downcall(
            "SDL_ClearProperty",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_ENUMERATE_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_EnumerateProperties",
            FunctionDescriptor.of(
                    JAVA_BOOLEAN,
                    JAVA_INT,
                    SdlLayouts.SDL_ENUMERATE_PROPERTIES_CALLBACK,
                    SdlLayouts.VOID_POINTER));
    private static final MethodHandle SDL_DESTROY_PROPERTIES_HANDLE = SdlFfmNative.downcall(
            "SDL_DestroyProperties",
            FunctionDescriptor.ofVoid(
                    JAVA_INT));

    @Override
    public boolean SDL_ClearProperty(SdlPropertiesId props, String name) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_CLEAR_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_CopyProperties(SdlPropertiesId src, SdlPropertiesId dst) {
        try {
            return (boolean) SDL_COPY_PROPERTIES_HANDLE.invokeExact(src.value(), dst.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPropertiesId SDL_CreateProperties() {
        try {
            return new SdlPropertiesId((int) SDL_CREATE_PROPERTIES_HANDLE.invokeExact());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_DestroyProperties(SdlPropertiesId props) {
        try {
            SDL_DESTROY_PROPERTIES_HANDLE.invokeExact(props.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_EnumerateProperties(SdlPropertiesId props, EnumeratePropertiesCallback callback, SdlPointer userdata) {
        try {
            return (boolean) SDL_ENUMERATE_PROPERTIES_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.callback(callback), SdlFfmSupport.segment(userdata.address())
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_GetBooleanProperty(SdlPropertiesId props, String name, boolean defaultValue) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_GET_BOOLEAN_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena), defaultValue
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public float SDL_GetFloatProperty(SdlPropertiesId props, String name, float defaultValue) {
        try (Arena arena = Arena.ofConfined()) {
            return (float) SDL_GET_FLOAT_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena), defaultValue
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPropertiesId SDL_GetGlobalProperties() {
        try {
            return new SdlPropertiesId((int) SDL_GET_GLOBAL_PROPERTIES_HANDLE.invokeExact());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public long SDL_GetNumberProperty(SdlPropertiesId props, String name, long defaultValue) {
        try (Arena arena = Arena.ofConfined()) {
            return (long) SDL_GET_NUMBER_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena), defaultValue
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlPointer SDL_GetPointerProperty(SdlPropertiesId props, String name, SdlPointer defaultValue) {
        try (Arena arena = Arena.ofConfined()) {
            return new SdlPointer(
                ((MemorySegment) SDL_GET_POINTER_PROPERTY_HANDLE.invokeExact(
                        props.value(),
                        SdlFfmSupport.utf8(name, arena),
                        SdlFfmSupport.segment(defaultValue.address())
                    )).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_GetPropertyType(SdlPropertiesId props, String name) {
        try (Arena arena = Arena.ofConfined()) {
            return (int) SDL_GET_PROPERTY_TYPE_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_GetStringProperty(SdlPropertiesId props, String name, String defaultValue) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.string(
                (MemorySegment) SDL_GET_STRING_PROPERTY_HANDLE.invokeExact(
                    props.value(),
                    SdlFfmSupport.utf8(name, arena),
                    SdlFfmSupport.utf8(defaultValue, arena)
                )
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_HasProperty(SdlPropertiesId props, String name) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_HAS_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_LockProperties(SdlPropertiesId props) {
        try {
            return (boolean) SDL_LOCK_PROPERTIES_HANDLE.invokeExact(props.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetBooleanProperty(SdlPropertiesId props, String name, boolean value) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_BOOLEAN_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena), value
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetFloatProperty(SdlPropertiesId props, String name, float value) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_FLOAT_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena), value
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetNumberProperty(SdlPropertiesId props, String name, long value) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_NUMBER_PROPERTY_HANDLE.invokeExact(
                props.value(), SdlFfmSupport.utf8(name, arena), value
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetPointerProperty(SdlPropertiesId props, String name, SdlPointer value) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_POINTER_PROPERTY_HANDLE.invokeExact(
                props.value(),
                SdlFfmSupport.utf8(name, arena),
                SdlFfmSupport.segment(value.address())
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetPointerPropertyWithCleanup(SdlPropertiesId props, String name, SdlPointer value, CleanupPropertyCallback cleanup, SdlPointer userdata) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_POINTER_PROPERTY_WITH_CLEANUP_HANDLE.invokeExact(
                props.value(),
                SdlFfmSupport.utf8(name, arena),
                SdlFfmSupport.segment(value.address()),
                SdlFfmSupport.callback(cleanup),
                SdlFfmSupport.segment(userdata.address())
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public boolean SDL_SetStringProperty(SdlPropertiesId props, String name, String value) {
        try (Arena arena = Arena.ofConfined()) {
            return (boolean) SDL_SET_STRING_PROPERTY_HANDLE.invokeExact(
                props.value(),
                SdlFfmSupport.utf8(name, arena),
                SdlFfmSupport.utf8(value, arena)
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public void SDL_UnlockProperties(SdlPropertiesId props) {
        try {
            SDL_UNLOCK_PROPERTIES_HANDLE.invokeExact(props.value());
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
