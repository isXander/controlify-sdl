plugins {
    id("sdl-java")
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.1.0")
}

publishing {
    publications {
        register<MavenPublication>("api") {
            from(components["java"])
        }
    }
}
