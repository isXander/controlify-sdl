plugins {
    id("sdl-java")
}

base.archivesName = "controlify-sdl-api"

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
