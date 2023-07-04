plugins {
    kotlin("jvm") version "1.8.21"
    id("java-gradle-plugin")
    id("maven-publish")
}

group = "com.orbitasolutions"
version = "0.0.9-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}

repositories {

}

dependencies {
//   implementation("com.orbitasolutions:geleia:0.0.23-SNAPSHOT")
//    api(files("libs/geleia-jvm-0.0.28-SNAPSHOT.jar"))
    api(":geleia-jvm-0.0.28-SNAPSHOT")

    implementation("org.jetbrains.compose.desktop:desktop:1.2.2")
    implementation("org.jetbrains.compose.desktop:desktop-jvm-macos-arm64:1.2.2")
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
