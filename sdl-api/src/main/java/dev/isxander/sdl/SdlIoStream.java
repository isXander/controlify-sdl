package dev.isxander.sdl;

import java.nio.ByteBuffer;

import dev.isxander.sdl.SdlRefs.LongRef;

public interface SdlIoStream {
    /// Everything is ready (no errors and not EOF).
    int SDL_IO_STATUS_READY = 0;
    /// Read or write I/O error
    int SDL_IO_STATUS_ERROR = 1;
    /// End of file
    int SDL_IO_STATUS_EOF = 2;
    /// Non blocking I/O, not ready
    int SDL_IO_STATUS_NOT_READY = 3;
    /// Tried to write a read-only buffer
    int SDL_IO_STATUS_READONLY = 4;
    /// Tried to read a write-only buffer
    int SDL_IO_STATUS_WRITEONLY = 5;
    /// Seek from the beginning of data
    int SDL_IO_SEEK_SET = 0;
    /// Seek relative to current read point
    int SDL_IO_SEEK_CUR = 1;
    /// Seek relative to the end of data
    int SDL_IO_SEEK_END = 2;

    /// Use this function to create a new SDL_IOStream structure for reading from
    /// and/or writing to a named file.
    ///
    /// @param file a UTF-8 string representing the filename to open.
    /// @param mode an ASCII string representing the mode to be used for opening
    ///             the file.
    /// @return a pointer to the SDL_IOStream structure that is created or NULL on
    ///         failure; call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlIoStreamHandle SDL_IOFromFile(String file, String mode);

    /// Use this function to prepare a read-write memory buffer for use with
    /// SDL_IOStream.
    ///
    /// @param memory a pointer to a buffer to feed an SDL_IOStream stream.
    /// @return a pointer to a new SDL_IOStream structure or NULL on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlIoStreamHandle SDL_IOFromMem(ByteBuffer memory);

    /// Use this function to prepare a read-only memory buffer for use with
    /// SDL_IOStream.
    ///
    /// @param memory a pointer to a read-only buffer to feed an SDL_IOStream stream.
    /// @return a pointer to a new SDL_IOStream structure or NULL on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlIoStreamHandle SDL_IOFromConstMem(ByteBuffer memory);

    /// Use this function to create an SDL_IOStream that is backed by dynamically
    /// allocated memory.
    ///
    /// @return a pointer to a new SDL_IOStream structure or NULL on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlIoStreamHandle SDL_IOFromDynamicMem();

    /// Create a custom SDL_IOStream.
    ///
    /// @param ioInterface the interface that implements this SDL_IOStream, initialized
    ///                    with SDL_INIT_INTERFACE().
    /// @param userdata the pointer that will be passed to the interface functions.
    /// @return a pointer to the allocated memory on success or NULL on failure;
    ///         call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlIoStreamHandle SDL_OpenIO(SdlIoInterface ioInterface, SdlPointer userdata);

    /// Close and free an allocated SDL_IOStream structure.
    ///
    /// @param context SDL_IOStream structure to close.
    /// @return true on success or false on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    boolean SDL_CloseIO(SdlIoStreamHandle context);

    /// Get the properties associated with an SDL_IOStream.
    ///
    /// @param context a pointer to an SDL_IOStream structure.
    /// @return a valid property ID on success or 0 on failure; call
    ///         SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlPropertiesId SDL_GetIOProperties(SdlIoStreamHandle context);

    /// Query the stream status of an SDL_IOStream.
    ///
    /// @param context the SDL_IOStream to query.
    /// @return an SDL_IOStatus enum with the current state.
    ///
    /// @since This function is available since SDL 3.2.0.
    int SDL_GetIOStatus(SdlIoStreamHandle context);

    /// Use this function to get the size of the data stream in an SDL_IOStream.
    ///
    /// @param context the SDL_IOStream to get the size of the data stream from.
    /// @return the size of the data stream in the SDL_IOStream on success or a
    ///         negative error code on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    long SDL_GetIOSize(SdlIoStreamHandle context);

    /// Seek within an SDL_IOStream data stream.
    ///
    /// @param context a pointer to an SDL_IOStream structure.
    /// @param offset an offset in bytes, relative to `whence` location; can be
    ///               negative.
    /// @param whence any of `SDL_IO_SEEK_SET`, `SDL_IO_SEEK_CUR`,
    ///               `SDL_IO_SEEK_END`.
    /// @return the final offset in the data stream after the seek or -1 on
    ///         failure; call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    long SDL_SeekIO(SdlIoStreamHandle context, long offset, int whence);

    /// Determine the current read/write offset in an SDL_IOStream data stream.
    ///
    /// @param context an SDL_IOStream data stream object from which to get the
    ///                current offset.
    /// @return the current offset in the stream, or -1 if the information can not
    ///         be determined.
    ///
    /// @since This function is available since SDL 3.2.0.
    long SDL_TellIO(SdlIoStreamHandle context);

    /// Read from a data source.
    ///
    /// @param context a pointer to an SDL_IOStream structure.
    /// @param destination a pointer to a buffer to read data into.
    /// @return the number of bytes read, or 0 on end of file or other failure;
    ///         call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    long SDL_ReadIO(SdlIoStreamHandle context, ByteBuffer destination);

    /// Write to an SDL_IOStream data stream.
    ///
    /// @param context a pointer to an SDL_IOStream structure.
    /// @param source a pointer to a buffer containing data to write.
    /// @return the number of bytes written, which will be less than `size` on
    ///         failure; call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    long SDL_WriteIO(SdlIoStreamHandle context, ByteBuffer source);

    /// Load all the data from an SDL data stream.
    ///
    /// @param src the SDL_IOStream to read all available data from.
    /// @param dataSize a pointer filled in with the number of bytes read, may be
    ///                 NULL.
    /// @param closeio if true, calls SDL_CloseIO() on `src` before returning, even
    ///                in the case of an error.
    /// @return the data or NULL on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlPointer SDL_LoadFile_IO(SdlIoStreamHandle src, LongRef dataSize, boolean closeio);

    /// Load all the data from a file path.
    ///
    /// @param file the path to read all available data from.
    /// @param dataSize if not NULL, will store the number of bytes read.
    /// @return the data or NULL on failure; call SDL_GetError() for more
    ///         information.
    ///
    /// @since This function is available since SDL 3.2.0.
    SdlPointer SDL_LoadFile(String file, LongRef dataSize);
}
