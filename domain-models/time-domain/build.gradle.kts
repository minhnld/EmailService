import java.util.Properties

plugins {
    id("vn.kamereo.java-library") version "1.1.6"
    id("vn.kamereo.kotlin") version "1.1.6"
    id("io.gitlab.arturbosch.detekt")
}

val properties = Properties().apply {
    load(rootProject.file("../gradle.properties").reader())
}
val versionBuildTools: String by properties
val versionKotlinUtils: String by properties

dependencies {
    implementation(platform("vn.kamereo.platform:product-platform:$versionBuildTools"))

    testImplementation(platform("vn.kamereo.platform:test-platform:$versionBuildTools"))
    testImplementation(libs.bundles.junit)
    testImplementation(libs.bundles.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation("org.assertj:assertj-core")
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
