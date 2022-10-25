import java.util.Properties

plugins {
    id("vn.kamereo.java-library") version "1.1.3"
    id("vn.kamereo.kotlin") version "1.1.3"
    id("io.gitlab.arturbosch.detekt")
}

val properties = Properties().apply {
    load(rootProject.file("../gradle.properties").reader())
}
val versionBuildTools: String by properties

dependencies {
    implementation(platform("vn.kamereo.platform:product-platform:$versionBuildTools"))
    implementation(project(":domain-utils"))
    testImplementation(platform("vn.kamereo.platform:test-platform:$versionBuildTools"))
}

detekt {
    toolVersion = detekt.toolVersion
    source = files("src/main/kotlin", "src/test/kotlin")
    parallel = true
    config = files(rootProject.file("../config/detekt/detekt.yml"))
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    this.jvmTarget = "11"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    this.jvmTarget = "11"
}
