plugins {
    id("sdl-common")
}

val sdlVersionProvider = providers.fileContents(rootProject.layout.projectDirectory.file("SDL_TARGET")).asText
val sdlVersion = sdlVersionProvider.get()

version = sdlVersion

publishing {
    publications {
        register<MavenPublication>("natives") {
            val nativesPaths = project.files(
                "libs/natives/windows-x86_64",
                "libs/natives/windows-aarch64",
                "libs/natives/linux-x86_64",
                "libs/natives/linux-aarch64",
                "libs/natives/macos-aarch64",
                "libs/natives/macos-x86_64",
            )
            for (nativeFolder in nativesPaths) {
                if (!nativeFolder.exists()) {
                    continue
                }

                for (nativeFile in nativeFolder.listFiles() ?: emptyArray()) {
                    artifact(nativeFile) {
                        classifier = nativeFolder.name
                    }
                }
            }
        }
    }
}
