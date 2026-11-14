package dev.isxander.sdl;

import org.jetbrains.annotations.Nullable;

import java.nio.ByteOrder;

public interface SdlStdinc {
    /// A value to represent littleendian byteorder.
    ///
    /// This is used with the preprocessor macro SDL_BYTEORDER, to determine a
    /// platform's byte ordering:
    ///
    /// ```c
    /// #if SDL_BYTEORDER == SDL_LIL_ENDIAN
    /// SDL_Log("This system is littleendian.");
    /// #endif
    /// ```
    ///
    /// @since This macro is available since SDL 3.2.0.
    ///
    /// See `SDL_BYTEORDER`.
    /// See `SDL_BIG_ENDIAN`.
    int SDL_LIL_ENDIAN = 1234;

    /// A value to represent bigendian byteorder.
    ///
    /// This is used with the preprocessor macro SDL_BYTEORDER, to determine a
    /// platform's byte ordering:
    ///
    /// ```c
    /// #if SDL_BYTEORDER == SDL_BIG_ENDIAN
    /// SDL_Log("This system is bigendian.");
    /// #endif
    /// ```
    ///
    /// @since This macro is available since SDL 3.2.0.
    ///
    /// See `SDL_BYTEORDER`.
    /// See `SDL_LIL_ENDIAN`.
    int SDL_BIG_ENDIAN = 4321;

    /// A macro that reports the target system's byte order.
    ///
    /// This is set to either SDL_LIL_ENDIAN or SDL_BIG_ENDIAN (and maybe other
    /// values in the future, if something else becomes popular). This can be
    /// tested with the preprocessor, so decisions can be made at compile time.
    ///
    /// ```c
    /// #if SDL_BYTEORDER == SDL_BIG_ENDIAN
    /// SDL_Log("This system is bigendian.");
    /// #endif
    /// ```
    ///
    /// @since This macro is available since SDL 3.2.0.
    ///
    /// See `SDL_LIL_ENDIAN`.
    /// See `SDL_BIG_ENDIAN`.
    int SDL_BYTEORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN
            ? SDL_BIG_ENDIAN : SDL_LIL_ENDIAN;

    /// Allocate uninitialized memory.
    ///
    /// @param size the size to allocate.
    /// @return a pointer to the allocated memory, or NULL if allocation failed.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlPointer SDL_malloc(long size);

    /// Allocate memory initialized to zero.
    ///
    /// @param nmemb the number of elements in the array.
    /// @param size the size of each element of the array.
    /// @return a pointer to the allocated array, or NULL if allocation failed.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlPointer SDL_calloc(long nmemb, long size);

    /// Change the size of allocated memory.
    ///
    /// @param memory a pointer to allocated memory to reallocate, or NULL.
    /// @param size the new size of the memory.
    /// @return a pointer to the newly allocated memory, or NULL if allocation
    ///         failed.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlPointer SDL_realloc(SdlPointer memory, long size);

    /// Free allocated memory.
    ///
    /// @param memory a pointer to allocated memory, or NULL.
    ///
    /// @since This function is available since SDL 3.2.0.
    void SDL_free(SdlPointer memory);

    /// Get the number of outstanding memory allocations.
    ///
    /// @return the number of allocations or -1 if allocation counting is
    ///         disabled.
    ///
    /// @since This function is available since SDL 3.2.0.
    int SDL_GetNumAllocations();

    /// Get the value of a variable in the environment.
    ///
    /// This function bypasses SDL's cached copy of the environment and is not
    /// thread-safe.
    ///
    /// On some platforms, this may make case-insensitive matches, while other
    /// platforms are case-sensitive. It is best to be precise with strings used
    /// for queries through this interface. SDL_getenv is always case-sensitive,
    /// however.
    ///
    /// @param name the name of the variable to get
    /// @return a pointer to the value of the variable or NULL if it can't be found.
    ///
    /// @since This function is available since SDL 3.2.0.
    @Nullable String SDL_getenv_unsafe(String name);

    /// Set the value of a variable in the environment.
    ///
    /// @param name the name of the variable to set.
    /// @param value the value of the variable to set.
    /// @param overwrite 1 to overwrite the variable if it exists, 0 to return
    ///                  success without setting the variable if it already exists.
    /// @return 0 on success, -1 on error.
    ///
    /// This function is not thread safe, consider using
    /// SDL_SetEnvironmentVariable() instead.
    ///
    /// @since This function is available since SDL 3.2.0.
    int SDL_setenv_unsafe(String name, String value, boolean overwrite);

    /// Clear a variable from the environment.
    ///
    /// @param name the name of the variable to unset.
    /// @return 0 on success, -1 on error.
    ///
    /// This function is not thread safe, consider using
    /// SDL_UnsetEnvironmentVariable() instead.
    ///
    /// @since This function is available since SDL 3.2.0.
    int SDL_unsetenv_unsafe(String name);
}
