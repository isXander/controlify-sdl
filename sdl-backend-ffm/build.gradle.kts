plugins {
    id("sdl-java")
}

base.archivesName = "controlify-sdl-backend-ffm"

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
