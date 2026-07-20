package dev.isxander.sdl.ffm;

import dev.isxander.sdl.SdlHidApi;
import dev.isxander.sdl.SdlHidDeviceHandle;
import dev.isxander.sdl.SdlHidDeviceInfo;
import dev.isxander.sdl.ffm.internal.SdlFfmNative;
import dev.isxander.sdl.ffm.internal.SdlLayouts;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import java.nio.ByteBuffer;
import java.util.List;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;
import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
import static java.lang.foreign.ValueLayout.JAVA_SHORT;

final class SdlFfmHidApi implements SdlHidApi {
    private static final MethodHandle SDL_HID_INIT_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_init",
            FunctionDescriptor.of(
                    JAVA_INT));
    private static final MethodHandle SDL_HID_EXIT_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_exit",
            FunctionDescriptor.of(
                    JAVA_INT));
    private static final MethodHandle SDL_HID_DEVICE_CHANGE_COUNT_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_device_change_count",
            FunctionDescriptor.of(
                    JAVA_INT));
    private static final MethodHandle SDL_HID_ENUMERATE_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_enumerate",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_HID_DEVICE_INFO,
                    JAVA_SHORT,
                    JAVA_SHORT));
    private static final MethodHandle SDL_HID_FREE_ENUMERATION_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_free_enumeration",
            FunctionDescriptor.ofVoid(
                    SdlLayouts.SDL_HID_DEVICE_INFO));
    private static final MethodHandle SDL_HID_OPEN_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_open",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_HID_DEVICE,
                    JAVA_SHORT,
                    JAVA_SHORT,
                    SdlLayouts.WIDE_STRING));
    private static final MethodHandle SDL_HID_OPEN_PATH_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_open_path",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.UTF8_STRING));
    private static final MethodHandle SDL_HID_WRITE_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_write",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_READ_TIMEOUT_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_read_timeout",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_LONG,
                    JAVA_INT));
    private static final MethodHandle SDL_HID_READ_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_read",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_SET_NONBLOCKING_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_set_nonblocking",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    JAVA_INT));
    private static final MethodHandle SDL_HID_SEND_FEATURE_REPORT_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_send_feature_report",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_GET_FEATURE_REPORT_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_get_feature_report",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_CLOSE_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_close",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE));
    private static final MethodHandle SDL_HID_GET_MANUFACTURER_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_get_manufacturer_string",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.WIDE_STRING,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_GET_PRODUCT_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_get_product_string",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.WIDE_STRING,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_GET_SERIAL_NUMBER_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_get_serial_number_string",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.WIDE_STRING,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_GET_INDEXED_STRING_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_get_indexed_string",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    JAVA_INT,
                    SdlLayouts.WIDE_STRING,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_GET_DEVICE_INFO_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_get_device_info",
            FunctionDescriptor.of(
                    SdlLayouts.SDL_HID_DEVICE_INFO,
                    SdlLayouts.SDL_HID_DEVICE));
    private static final MethodHandle SDL_HID_GET_REPORT_DESCRIPTOR_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_get_report_descriptor",
            FunctionDescriptor.of(
                    JAVA_INT,
                    SdlLayouts.SDL_HID_DEVICE,
                    SdlLayouts.UINT8_POINTER,
                    JAVA_LONG));
    private static final MethodHandle SDL_HID_BLE_SCAN_HANDLE = SdlFfmNative.downcall(
            "SDL_hid_ble_scan",
            FunctionDescriptor.ofVoid(
                    JAVA_BOOLEAN));

    @Override
    public void SDL_hid_ble_scan(boolean active) {
        try {
            SDL_HID_BLE_SCAN_HANDLE.invokeExact(active);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_close(SdlHidDeviceHandle device) {
        try {
            return (int) SDL_HID_CLOSE_HANDLE.invokeExact(SdlFfmSupport.segment(device.address()));
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_device_change_count() {
        try {
            return (int) SDL_HID_DEVICE_CHANGE_COUNT_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public List<SdlHidDeviceInfo> SDL_hid_enumerate(int vendorId, int productId) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.hidEnumerate(vendorId, productId, arena);
        }
    }

    @Override
    public int SDL_hid_exit() {
        try {
            return (int) SDL_HID_EXIT_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlHidDeviceInfo SDL_hid_get_device_info(SdlHidDeviceHandle device) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.hidInfo(
                (MemorySegment) SDL_HID_GET_DEVICE_INFO_HANDLE.invokeExact(SdlFfmSupport.segment(device.address())), arena
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_get_feature_report(SdlHidDeviceHandle device, ByteBuffer data) {
        try {
            return (int) SDL_HID_GET_FEATURE_REPORT_HANDLE.invokeExact(
                SdlFfmSupport.segment(device.address()), MemorySegment.ofBuffer(data), (long) data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_hid_get_indexed_string(SdlHidDeviceHandle device, int stringIndex, int maxLength) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.hidIndexedString(device, stringIndex, maxLength, arena);
        }
    }

    @Override
    public String SDL_hid_get_manufacturer_string(SdlHidDeviceHandle device, int maxLength) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.hidManufacturerString(device, maxLength, arena);
        }
    }

    @Override
    public String SDL_hid_get_product_string(SdlHidDeviceHandle device, int maxLength) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.hidProductString(device, maxLength, arena);
        }
    }

    @Override
    public int SDL_hid_get_report_descriptor(SdlHidDeviceHandle device, ByteBuffer buffer) {
        try {
            return (int) SDL_HID_GET_REPORT_DESCRIPTOR_HANDLE.invokeExact(
                SdlFfmSupport.segment(device.address()), MemorySegment.ofBuffer(buffer), (long) buffer.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public String SDL_hid_get_serial_number_string(SdlHidDeviceHandle device, int maxLength) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.hidSerialNumberString(device, maxLength, arena);
        }
    }

    @Override
    public int SDL_hid_init() {
        try {
            return (int) SDL_HID_INIT_HANDLE.invokeExact();
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public SdlHidDeviceHandle SDL_hid_open(int vendorId, int productId, String serialNumber) {
        try (Arena arena = Arena.ofConfined()) {
            return SdlFfmSupport.hidOpen(vendorId, productId, serialNumber, arena);
        }
    }

    @Override
    public SdlHidDeviceHandle SDL_hid_open_path(String path) {
        try (Arena arena = Arena.ofConfined()) {
            return new SdlHidDeviceHandle(
                    ((MemorySegment) SDL_HID_OPEN_PATH_HANDLE.invokeExact(
                             SdlFfmSupport.utf8(path, arena))).address()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_read(SdlHidDeviceHandle device, ByteBuffer data) {
        try {
            return (int) SDL_HID_READ_HANDLE.invokeExact(
                SdlFfmSupport.segment(device.address()), MemorySegment.ofBuffer(data), (long) data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_read_timeout(SdlHidDeviceHandle device, ByteBuffer data, int milliseconds) {
        try {
            return (int) SDL_HID_READ_TIMEOUT_HANDLE.invokeExact(
                SdlFfmSupport.segment(device.address()), MemorySegment.ofBuffer(data), (long) data.remaining(), milliseconds
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_send_feature_report(SdlHidDeviceHandle device, ByteBuffer data) {
        try {
            return (int) SDL_HID_SEND_FEATURE_REPORT_HANDLE.invokeExact(
                SdlFfmSupport.segment(device.address()), MemorySegment.ofBuffer(data), (long) data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_set_nonblocking(SdlHidDeviceHandle device, int nonblock) {
        try {
            return (int) SDL_HID_SET_NONBLOCKING_HANDLE.invokeExact(SdlFfmSupport.segment(device.address()), nonblock);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    @Override
    public int SDL_hid_write(SdlHidDeviceHandle device, ByteBuffer data) {
        try {
            return (int) SDL_HID_WRITE_HANDLE.invokeExact(
                SdlFfmSupport.segment(device.address()), MemorySegment.ofBuffer(data), (long) data.remaining()
            );
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_hid_enumerate(short vendorId, short productId) {
        try {
            return (MemorySegment) SDL_HID_ENUMERATE_HANDLE.invokeExact((short) vendorId, (short) productId);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static void SDL_hid_free_enumeration(MemorySegment devices) {
        try {
            SDL_HID_FREE_ENUMERATION_HANDLE.invokeExact(devices);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static MemorySegment SDL_hid_open(short vendorId, short productId, MemorySegment serialNumber) {
        try {
            return (MemorySegment) SDL_HID_OPEN_HANDLE.invokeExact((short) vendorId, (short) productId, serialNumber);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static int SDL_hid_get_manufacturer_string(MemorySegment device, MemorySegment string, long maxLength) {
        try {
            return (int) SDL_HID_GET_MANUFACTURER_STRING_HANDLE.invokeExact(device, string, (long) maxLength);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static int SDL_hid_get_product_string(MemorySegment device, MemorySegment string, long maxLength) {
        try {
            return (int) SDL_HID_GET_PRODUCT_STRING_HANDLE.invokeExact(device, string, (long) maxLength);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static int SDL_hid_get_serial_number_string(MemorySegment device, MemorySegment string, long maxLength) {
        try {
            return (int) SDL_HID_GET_SERIAL_NUMBER_STRING_HANDLE.invokeExact(device, string, (long) maxLength);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }

    static int SDL_hid_get_indexed_string(MemorySegment device, int stringIndex, MemorySegment string, long maxLength) {
        try {
            return (int) SDL_HID_GET_INDEXED_STRING_HANDLE.invokeExact(device, stringIndex, string, (long) maxLength);
        } catch (Error | RuntimeException exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new AssertionError("Unexpected exception from SDL downcall", throwable);
        }
    }
}
