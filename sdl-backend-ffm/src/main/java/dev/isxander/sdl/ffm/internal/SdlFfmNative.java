package dev.isxander.sdl.ffm.internal;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.PaddingLayout;
import java.lang.foreign.SequenceLayout;
import java.lang.foreign.StructLayout;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Objects;

public final class SdlFfmNative {
    public static final Linker LINKER = Linker.nativeLinker();

    private static volatile LibraryBinding libraryBinding;

    private SdlFfmNative() {
    }

    public static synchronized void configure(SymbolLookup symbolLookup, String description) {
        Objects.requireNonNull(symbolLookup, "symbolLookup");
        Objects.requireNonNull(description, "description");

        if (libraryBinding == null) {
            libraryBinding = new LibraryBinding(symbolLookup, description);
        } else if (!libraryBinding.description().equals(description)) {
            throw new IllegalStateException(
                    "SDL FFM is already bound to " + libraryBinding.description()
                            + " and cannot be rebound to " + description);
        }
    }

    public static MethodHandle downcall(String symbol, FunctionDescriptor descriptor) {
        LibraryBinding binding = libraryBinding;
        if (binding == null) {
            throw new IllegalStateException("SDL FFM must be created through SdlFfmLoader");
        }
        return LINKER.downcallHandle(binding.symbolLookup().findOrThrow(symbol), descriptor);
    }

    public static MethodHandle upcallHandle(Class<?> type, String method, FunctionDescriptor descriptor) {
        try {
            return MethodHandles.lookup().findVirtual(type, method, descriptor.toMethodType());
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(exception);
        }
    }

    public static MemoryLayout align(MemoryLayout layout, long alignment) {
        return switch (layout) {
            case PaddingLayout padding -> padding;
            case ValueLayout value -> value.withByteAlignment(alignment);
            case GroupLayout group -> {
                MemoryLayout[] members = group.memberLayouts().stream()
                        .map(member -> align(member, alignment))
                        .toArray(MemoryLayout[]::new);
                yield group instanceof StructLayout
                        ? MemoryLayout.structLayout(members)
                        : MemoryLayout.unionLayout(members);
            }
            case SequenceLayout sequence -> MemoryLayout.sequenceLayout(
                    sequence.elementCount(), align(sequence.elementLayout(), alignment));
        };
    }

    private record LibraryBinding(SymbolLookup symbolLookup, String description) {
    }
}
