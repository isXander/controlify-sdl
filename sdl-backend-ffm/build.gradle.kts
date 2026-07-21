plugins {
    id("sdl-java")
}

dependencies {
    api(project(":sdl-api"))
}

publishing {
    publications {
        register<MavenPublication>("ffm") {
            from(components["java"])
        }
    }
}
