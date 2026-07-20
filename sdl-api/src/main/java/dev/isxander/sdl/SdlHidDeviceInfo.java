package dev.isxander.sdl;

public record SdlHidDeviceInfo(
        String path,
        int vendorId,
        int productId,
        String serialNumber,
        int releaseNumber,
        String manufacturerString,
        String productString,
        int usagePage,
        int usage,
        int interfaceNumber,
        int interfaceClass,
        int interfaceSubclass,
        int interfaceProtocol,
        int busType
) {
}
