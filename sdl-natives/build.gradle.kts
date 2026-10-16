plugins {
    id("sdl-common")
    java
    id("me.modmuss50.mod-publish-plugin") version "2.0.1"
    id("dev.isxander.mtk.manifests") version "0.1.3"
}

val sdlVersionProvider = providers.fileContents(rootProject.layout.projectDirectory.file("SDL_TARGET")).asText
val sdlVersion = sdlVersionProvider.get()
version = sdlVersion

val nativesPaths = project.files(
    "libs/natives/windows-x86_64",
    "libs/natives/windows-aarch64",
    "libs/natives/linux-x86_64",
    "libs/natives/linux-aarch64",
    "libs/natives/macos-aarch64",
    "libs/natives/macos-x86_64",
)

tasks.jar {
    from(nativesPaths)
}

manifests {
    val common = manifest {
        modId = "controlify_natives"
        displayName = "Controlify Natives"
        version = sdlVersion
        description = "Supplimentary, updated natives to backport new controller support and fixes to older Minecraft versions. For use with Controlify."
        licenses.add("Zlib")
        dependency("controlify", REQUIRED, "*")
    }
    fabricModJson(sourceSets.main.get()) {
        from(common)
    }
    neoForgeModsToml(sourceSets.main.get()) {
        from(common)
    }
}

publishMods {
    file = tasks.jar.flatMap { it.archiveFile }

    displayName = "Controlify Natives $sdlVersion"
    version = sdlVersion
    modLoaders.addAll("fabric", "neoforge")
    type = STABLE

    val mcVersions = manifests.minecraftReleasesMatching("[26.1,26.3)")

    modrinth {
        projectId = "s6g711D6"
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        minecraftVersions = mcVersions
    }

    curseforge {
        projectId = "1619432"
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        minecraftVersions = mcVersions
    }
}

publishing {
    publications {
        register<MavenPublication>("natives") {
            artifact(tasks.jar)

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
