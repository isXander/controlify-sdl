plugins {
    id("sdl-java")
}

dependencies {
    api(project(":sdl-api"))

    implementation(platform("org.lwjgl:lwjgl-bom:3.4.2"))
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-sdl")
}