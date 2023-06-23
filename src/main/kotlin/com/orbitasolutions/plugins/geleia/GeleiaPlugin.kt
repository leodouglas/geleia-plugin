package com.orbitasolutions.plugins.geleia

import org.gradle.api.Plugin
import org.gradle.api.Project

open class GeleiaPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create("geleia", GeleiaExtension::class.java)
        target.tasks.create("geleia", GeleiaTask::class.java) {
            println("Welcome Geleia plugin")
        }
    }
}