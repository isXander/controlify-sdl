plugins {
    `maven-publish`
}

group = "dev.isxander.sdl"

val sdlVersionProvider = providers.fileContents(rootProject.layout.projectDirectory.file("SDL_TARGET")).asText
val sdlVersion = sdlVersionProvider.get()

val buildNumber = System.getenv("GITHUB_RUN_NUMBER") ?: "local"

version = "$sdlVersion-$buildNumber"

publishing {
    repositories {
        maven("https://maven.isxander.dev/releases") {
            name = "XanderMaven"
            credentials(PasswordCredentials::class)
        }
    }
}