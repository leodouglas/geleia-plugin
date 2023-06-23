package com.orbitasolutions.plugins.geleia

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import com.orbitasolutions.geleia.Main

open class GeleiaTask : DefaultTask() {

    @TaskAction
    fun run() = Main().open()
}

fun main() {
    Main().open()
}