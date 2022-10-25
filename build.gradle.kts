plugins {
    `kotlin-dsl` apply false
    kotlin("kapt") version "1.6.10" apply false
    id("nu.studer.jooq") version "4.1" apply false
    id("io.gitlab.arturbosch.detekt") version ("1.21.0") apply false
}

fun Project.collect(taskName: String): List<Task> = subprojects.mapNotNull { it.tasks.findByName(taskName) }
fun Project.registerTaskDependsOnSubprojects(taskName: String, taskGroup: String) = tasks.register(taskName) {
    this.group = taskGroup
    this.dependsOn(*(gradle.includedBuilds.map { it.task(":$taskName") }).toTypedArray())
}

registerTaskDependsOnSubprojects("assemble", "build")
registerTaskDependsOnSubprojects("build", "build")
registerTaskDependsOnSubprojects("clean", "build")
registerTaskDependsOnSubprojects("test", "verification")
registerTaskDependsOnSubprojects("ktlintFormat", "formatting")
registerTaskDependsOnSubprojects("ktlintCheck", "verification")
registerTaskDependsOnSubprojects("detekt", "verification")

apply(from = "install-git-hooks.gradle.kts")
