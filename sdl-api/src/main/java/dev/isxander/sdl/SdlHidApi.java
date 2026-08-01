package dev.isxander.sdl;

import java.nio.ByteBuffer;
import java.util.List;

public interface SdlHidApi {
    /// Unknown bus type
    int SDL_HID_API_BUS_UNKNOWN = 0x00;
    /// USB bus
    ///
    /// Specifications:
    /// https://usb.org/hid
    int SDL_HID_API_BUS_USB = 0x01;
    /// Bluetooth or Bluetooth LE bus
    ///
    /// Specifications:
    /// https://www.bluetooth.com/specifications/specs/human-interface-device-profile-1-1-1/
    /// https://www.bluetooth.com/specifications/specs/hid-service-1-0/
    /// https://www.bluetooth.com/specifications/specs/hid-over-gatt-profile-1-0/
    int SDL_HID_API_BUS_BLUETOOTH = 0x02;
    /// I2C bus
    ///
    /// Specifications:
    /// https://docs.microsoft.com/previous-versions/windows/hardware/design/dn642101(v=vs.85)
    int SDL_HID_API_BUS_I2C = 0x03;
    /// SPI bus
    ///
    /// Specifications:
    /// https://www.microsoft.com/download/details.aspx?id=103325
    int SDL_HID_API_BUS_SPI = 0x04;

    /// Initialize the HIDAPI library.
    ///
    /// This function initializes the HIDAPI library. Calling it is not strictly
    /// necessary, as it will be called automatically by SDL_hid_enumerate() and
    /// any of the SDL_hid_open_*() functions if it is needed. This function should
    /// be called at the beginning of execution however, if there is a chance of
    /// HIDAPI handles being opened by different threads simultaneously.
    ///
    /// Each call to this function should have a matching call to SDL_hid_exit()
    ///
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_hid_exit`.
    ///
    int SDL_hid_init();

    /// Finalize the HIDAPI library.
    ///
    /// This function frees all of the static data associated with HIDAPI. It
    /// should be called at the end of execution to avoid memory leaks.
    ///
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_hid_init`.
    ///
    int SDL_hid_exit();

    /// Check to see if devices may have been added or removed.
    ///
    /// Enumerating the HID devices is an expensive operation, so you can call this
    /// to see if there have been any system device changes since the last call to
    /// this function. A change in the counter returned doesn't necessarily mean
    /// that anything has changed, but you can call SDL_hid_enumerate() to get an
    /// updated device list.
    ///
    /// Calling this function for the first time may cause a thread or other system
    /// resource to be allocated to track device change notifications.
    ///
    /// @return a change counter that is incremented with each potential device
    ///          change, or 0 if device change detection isn't available.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_hid_enumerate`.
    ///
    int SDL_hid_device_change_count();

    /// Enumerate the HID Devices.
    ///
    /// This function returns a linked list of all the HID devices attached to the
    /// system which match vendor_id and product_id. If `vendor_id` is set to 0
    /// then any vendor matches. If `product_id` is set to 0 then any product
    /// matches. If `vendor_id` and `product_id` are both set to 0, then all HID
    /// devices will be returned.
    ///
    /// By default SDL will only enumerate controllers, to reduce risk of hanging
    /// or crashing on bad drivers, but SDL_HINT_HIDAPI_ENUMERATE_ONLY_CONTROLLERS
    /// can be set to "0" to enumerate all HID devices.
    ///
    /// @param vendorId the Vendor ID (VID) of the types of device to open, or 0
    ///                  to match any vendor.
    /// @param productId the Product ID (PID) of the types of device to open, or 0
    ///                   to match any product.
    /// @return a pointer to a linked list of type SDL_hid_device_info, containing
    ///          information about the HID devices attached to the system, or NULL
    ///          in the case of failure. Free this linked list by calling
    ///          SDL_hid_free_enumeration().
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    /// See `SDL_hid_device_change_count`.
    ///
    List<SdlHidDeviceInfo> SDL_hid_enumerate(int vendorId, int productId);

    /// Open a HID device using a Vendor ID (VID), Product ID (PID) and optionally
    /// a serial number.
    ///
    /// If `serial_number` is NULL, the first device with the specified VID and PID
    /// is opened.
    ///
    /// @param vendorId the Vendor ID (VID) of the device to open.
    /// @param productId the Product ID (PID) of the device to open.
    /// @param serialNumber the Serial Number of the device to open (Optionally
    ///                      NULL).
    /// @return a pointer to a SDL_hid_device object on success or NULL on
    ///          failure; call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    SdlHidDeviceHandle SDL_hid_open(int vendorId, int productId, String serialNumber);

    /// Open a HID device by its path name.
    ///
    /// The path name be determined by calling SDL_hid_enumerate(), or a
    /// platform-specific path name can be used (eg: /dev/hidraw0 on Linux).
    ///
    /// @param path the path name of the device to open.
    /// @return a pointer to a SDL_hid_device object on success or NULL on
    ///          failure; call SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    SdlHidDeviceHandle SDL_hid_open_path(String path);

    /// Write an Output report to a HID device.
    ///
    /// The first byte of `data` must contain the Report ID. For devices which only
    /// support a single report, this must be set to 0x0. The remaining bytes
    /// contain the report data. Since the Report ID is mandatory, calls to
    /// SDL_hid_write() will always contain one more byte than the report contains.
    /// For example, if a hid report is 16 bytes long, 17 bytes must be passed to
    /// SDL_hid_write(), the Report ID (or 0x0, for devices with a single report),
    /// followed by the report data (16 bytes). In this example, the length passed
    /// in would be 17.
    ///
    /// SDL_hid_write() will send the data on the first OUT endpoint, if one
    /// exists. If it does not, it will send the data through the Control Endpoint
    /// (Endpoint 0).
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param data the data to send, including the report number as the first
    ///             byte.
    /// @return the actual number of bytes written and -1 on on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_write(SdlHidDeviceHandle device, ByteBuffer data);

    /// Read an Input report from a HID device with timeout.
    ///
    /// Input reports are returned to the host through the INTERRUPT IN endpoint.
    /// The first byte will contain the Report number if the device uses numbered
    /// reports.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param data a buffer to put the read data into.
    /// The number of bytes to read. For devices with multiple
    ///               reports, make sure to read an extra byte for the report
    ///               number.
    /// @param milliseconds timeout in milliseconds or -1 for blocking wait.
    /// @return the actual number of bytes read and -1 on on failure; call
    ///          SDL_GetError() for more information. If no packet was available to
    ///          be read within the timeout period, this function returns 0.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_read_timeout(SdlHidDeviceHandle device, ByteBuffer data, int milliseconds);

    /// Read an Input report from a HID device.
    ///
    /// Input reports are returned to the host through the INTERRUPT IN endpoint.
    /// The first byte will contain the Report number if the device uses numbered
    /// reports.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param data a buffer to put the read data into.
    /// The number of bytes to read. For devices with multiple
    ///               reports, make sure to read an extra byte for the report
    ///               number.
    /// @return the actual number of bytes read and -1 on failure; call
    ///          SDL_GetError() for more information. If no packet was available to
    ///          be read and the handle is in non-blocking mode, this function
    ///          returns 0.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_read(SdlHidDeviceHandle device, ByteBuffer data);

    /// Set the device handle to be non-blocking.
    ///
    /// In non-blocking mode calls to SDL_hid_read() will return immediately with a
    /// value of 0 if there is no data to be read. In blocking mode, SDL_hid_read()
    /// will wait (block) until there is data to read before returning.
    ///
    /// Nonblocking can be turned on and off at any time.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param nonblock enable or not the nonblocking reads - 1 to enable
    ///                 nonblocking - 0 to disable nonblocking.
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_set_nonblocking(SdlHidDeviceHandle device, int nonblock);

    /// Send a Feature report to the device.
    ///
    /// Feature reports are sent over the Control endpoint as a Set_Report
    /// transfer. The first byte of `data` must contain the Report ID. For devices
    /// which only support a single report, this must be set to 0x0. The remaining
    /// bytes contain the report data. Since the Report ID is mandatory, calls to
    /// SDL_hid_send_feature_report() will always contain one more byte than the
    /// report contains. For example, if a hid report is 16 bytes long, 17 bytes
    /// must be passed to SDL_hid_send_feature_report(): the Report ID (or 0x0, for
    /// devices which do not use numbered reports), followed by the report data (16
    /// bytes). In this example, the length passed in would be 17.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param data the data to send, including the report number as the first
    ///             byte.
    /// The length in bytes of the data to send, including the report
    ///               number.
    /// @return the actual number of bytes written and -1 on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_send_feature_report(SdlHidDeviceHandle device, ByteBuffer data);

    /// Get a feature report from a HID device.
    ///
    /// Set the first byte of `data` to the Report ID of the report to be read.
    /// Make sure to allow space for this extra byte in `data`. Upon return, the
    /// first byte will still contain the Report ID, and the report data will start
    /// in data[1].
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param data a buffer to put the read data into, including the Report ID.
    ///             Set the first byte of `data` to the Report ID of the report to
    ///             be read, or set it to zero if your device does not use numbered
    ///             reports.
    /// The number of bytes to read, including an extra byte for the
    ///               report ID. The buffer can be longer than the actual report.
    /// @return the number of bytes read plus one for the report ID (which is
    ///          still in the first byte), or -1 on on failure; call SDL_GetError()
    ///          for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_get_feature_report(SdlHidDeviceHandle device, ByteBuffer data);

    /// Close a HID device.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_close(SdlHidDeviceHandle device);

    /// Get The Manufacturer String from a HID device.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param maxLength the length of the buffer in multiples of wchar_t.
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    String SDL_hid_get_manufacturer_string(SdlHidDeviceHandle device, int maxLength);

    /// Get The Product String from a HID device.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param maxLength the length of the buffer in multiples of wchar_t.
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    String SDL_hid_get_product_string(SdlHidDeviceHandle device, int maxLength);

    /// Get The Serial Number String from a HID device.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param maxLength the length of the buffer in multiples of wchar_t.
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    String SDL_hid_get_serial_number_string(SdlHidDeviceHandle device, int maxLength);

    /// Get a string from a HID device, based on its string index.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param stringIndex the index of the string to get.
    /// The length of the buffer in multiples of wchar_t.
    /// @return 0 on success or a negative error code on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    String SDL_hid_get_indexed_string(SdlHidDeviceHandle device, int stringIndex, int maxLength);

    /// Get the device info from a HID device.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @return a pointer to the SDL_hid_device_info for this hid_device or NULL
    ///          on failure; call SDL_GetError() for more information. This struct
    ///          is valid until the device is closed with SDL_hid_close().
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    SdlHidDeviceInfo SDL_hid_get_device_info(SdlHidDeviceHandle device);

    /// Get a report descriptor from a HID device.
    ///
    /// User has to provide a preallocated buffer where descriptor will be copied
    /// to. The recommended size for a preallocated buffer is 4096 bytes.
    ///
    /// @param device a device handle returned from SDL_hid_open().
    /// @param buffer the buffer to copy descriptor into.
    /// The size of the buffer in bytes.
    /// @return the number of bytes actually copied or -1 on failure; call
    ///          SDL_GetError() for more information.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    int SDL_hid_get_report_descriptor(SdlHidDeviceHandle device, ByteBuffer buffer);

    /// Start or stop a BLE scan on iOS and tvOS to pair Steam Controllers.
    ///
    /// @param active true to start the scan, false to stop the scan.
    ///
    /// @since This function is available since SDL 3.2.0.
    ///
    void SDL_hid_ble_scan(boolean active);
}
