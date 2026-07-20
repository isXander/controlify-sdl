pluginManagement {
    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "controlify-sdl"

include("sdl-api")
include("sdl-backend-ffm")
include("sdl-backend-lwjgl")
include("sdl-natives")