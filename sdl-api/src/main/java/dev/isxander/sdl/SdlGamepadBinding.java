package dev.isxander.sdl;

public record SdlGamepadBinding(int inputType, BindingValue input, int outputType, BindingValue output) {
    public sealed interface BindingValue permits Button, Axis, Hat {
    }

    public record Button(int button) implements BindingValue {
    }

    public record Axis(int axis, int axisMin, int axisMax) implements BindingValue {
    }

    public record Hat(int hat, int hatMask) implements BindingValue {
    }
}
