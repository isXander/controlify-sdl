plugins {
    id("sdl-common")
    `java-library`
}

repositories {
    mavenCentral()
}

java {
    withSourcesJar()

    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}