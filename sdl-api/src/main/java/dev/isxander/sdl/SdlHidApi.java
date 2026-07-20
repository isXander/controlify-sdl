package dev.isxander.sdl;

import java.nio.ByteBuffer;
import java.util.List;

public interface SdlHidApi {
    int SDL_HID_API_BUS_UNKNOWN = 0x00;
    int SDL_HID_API_BUS_USB = 0x01;
    int SDL_HID_API_BUS_BLUETOOTH = 0x02;
    int SDL_HID_API_BUS_I2C = 0x03;
    int SDL_HID_API_BUS_SPI = 0x04;

    int SDL_hid_init();

    int SDL_hid_exit();

    int SDL_hid_device_change_count();

    List<SdlHidDeviceInfo> SDL_hid_enumerate(int vendorId, int productId);

    SdlHidDeviceHandle SDL_hid_open(int vendorId, int productId, String serialNumber);

    SdlHidDeviceHandle SDL_hid_open_path(String path);

    int SDL_hid_write(SdlHidDeviceHandle device, ByteBuffer data);

    int SDL_hid_read_timeout(SdlHidDeviceHandle device, ByteBuffer data, int milliseconds);

    int SDL_hid_read(SdlHidDeviceHandle device, ByteBuffer data);

    int SDL_hid_set_nonblocking(SdlHidDeviceHandle device, int nonblock);

    int SDL_hid_send_feature_report(SdlHidDeviceHandle device, ByteBuffer data);

    int SDL_hid_get_feature_report(SdlHidDeviceHandle device, ByteBuffer data);

    int SDL_hid_close(SdlHidDeviceHandle device);

    String SDL_hid_get_manufacturer_string(SdlHidDeviceHandle device, int maxLength);

    String SDL_hid_get_product_string(SdlHidDeviceHandle device, int maxLength);

    String SDL_hid_get_serial_number_string(SdlHidDeviceHandle device, int maxLength);

    String SDL_hid_get_indexed_string(SdlHidDeviceHandle device, int stringIndex, int maxLength);

    SdlHidDeviceInfo SDL_hid_get_device_info(SdlHidDeviceHandle device);

    int SDL_hid_get_report_descriptor(SdlHidDeviceHandle device, ByteBuffer buffer);

    void SDL_hid_ble_scan(boolean active);
}
