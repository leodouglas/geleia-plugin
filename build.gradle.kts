plugins {
    kotlin("jvm") version "1.8.21"
    id("java-gradle-plugin")
    id("maven-publish")
}

group = "com.orbitasolutions"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
   implementation("com.orbitasolutions:geleia:0.0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(11)
}

gradlePlugin {
    plugins {
        create("geleia") {
            id = "com.orbitasolutions.geleia"
            implementationClass = "com.orbitasolutions.plugins.geleia.GeleiaPlugin"
        }
    }
}
