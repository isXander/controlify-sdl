plugins {
    id("sdl-java")
}

dependencies {
    api(project(":sdl-api"))
    compileOnly("org.jetbrains:annotations:26.1.0")
}

publishing {
    publications {
        register<MavenPublication>("ffm") {
            from(components["java"])
        }
    }
}
