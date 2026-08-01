plugins {
    id("sdl-common")
    `java-library`
}

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()

    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}