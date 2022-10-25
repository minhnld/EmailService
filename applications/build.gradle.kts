plugins {
    `kotlin-dsl` apply false
    id("io.gitlab.arturbosch.detekt") version ("1.21.0") apply false
}

fun Project.collect(taskName: String): List<Task> = subprojects.mapNotNull { it.tasks.findByName(taskName) }
fun Project.registerTaskDependsOnSubprojects(taskName: String, taskGroup: String) = tasks.register(taskName) {
    this.group = taskGroup
    this.dependsOn(*(this.project.collect(taskName)).toTypedArray())
}

registerTaskDependsOnSubprojects("assemble", "build")
registerTaskDependsOnSubprojects("build", "build")
registerTaskDependsOnSubprojects("bootJar", "build")
registerTaskDependsOnSubprojects("clean", "build")
registerTaskDependsOnSubprojects("test", "verification")
registerTaskDependsOnSubprojects("ktlintFormat", "formatting")
registerTaskDependsOnSubprojects("ktlintCheck", "verification")
registerTaskDependsOnSubprojects("detekt", "verification")
