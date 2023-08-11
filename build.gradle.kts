import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "1.8.21"
    id("java-gradle-plugin")
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.8.20"
    signing
}

apply(plugin = "signing")

group = "com.orbitasolutions"
version = "0.2.8"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.orbitasolutions:geleia:0.2.8")
    implementation("org.jetbrains.compose.desktop:desktop:1.2.2")
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

val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    val dokkaHtml by tasks.getting(DokkaTask::class)

    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

tasks {
    task("publishGprPublicationToGeleiaRepository").outputs.cacheIf { true }
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    afterEvaluate {
        publications {
            withType<MavenPublication> {
                updatePom(pom)
                the<SigningExtension>().sign(this)
            }
        }
    }
    repositories {
        maven {
            name = "sonatypeStaging"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials(PasswordCredentials::class)
        }
    }
}

fun updatePom(pom: MavenPom?) {
    if (pom != null) {
        with(pom) {
            val projectGitUrl = "https://github.com/leodouglas/geleia-plugin"
            name.set(rootProject.name)
            description.set("A maven plugin to geleia project")
            url.set(projectGitUrl)
            inceptionYear.set("2023")
            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("orbitasolutions.com")
                    name.set("Leo Douglas Padilha")
                    email.set("leodouglas@gmail.com")
                    url.set("https://www.orbitasolutions.com")
                }
            }
            issueManagement {
                system.set("GitHub")
                url.set("$projectGitUrl/issues")
            }
            scm {
                connection.set("scm:git:$projectGitUrl")
                developerConnection.set("scm:git:$projectGitUrl")
                url.set(projectGitUrl)
            }

        }
    }

}

signing {
    sign(configurations.archives.get())
}