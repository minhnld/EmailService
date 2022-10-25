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

    api("vn.kamereo.email.domain-models:email-domain")
    api("vn.kamereo.email.domain-models:domain-utils")
    api("vn.kamereo.email.domain-models:time-domain")

    implementation("com.sendgrid", "sendgrid-java", "4.4.1")

    implementation(libs.bundles.kotlin.stdlib)
    implementation(libs.bundles.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.sentry.spring.kotlin)
    implementation(libs.bundles.kamereo.logging)
    implementation(libs.microutils.kotlin.logging)

    api("org.springframework:spring-beans")
    api("org.springframework:spring-context")
    api("org.springframework:spring-context-support")

    api("org.springframework.boot:spring-boot")
    api("org.springframework.boot:spring-boot-autoconfigure")
    api("vn.kamereo:utils-error:$versionKotlinUtils")

    testImplementation(platform("vn.kamereo.platform:test-platform:$versionBuildTools"))
}

detekt {
    toolVersion = detekt.toolVersion
    source = files("src/main/kotlin", "src/test/kotlin")
    parallel = true
    config = files(
        rootProject.file("../config/detekt/detekt.yml"),
        rootProject.file("./config/detekt/detekt.yml")
    )
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    this.jvmTarget = "11"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    this.jvmTarget = "11"
}
