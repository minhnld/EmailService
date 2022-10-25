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
val versionGrpcDefinitions: String by properties
val versionKotlinUtils: String by properties
val versionKommandBus: String by properties
val versionElasticApm: String by properties

dependencies {
    implementation(platform("vn.kamereo.platform:product-platform:$versionBuildTools"))
    api("vn.kamereo.email.infrastructures:sendgrid-email-service")
    api("vn.kamereo.email.domain-models:email-domain")
    api("vn.kamereo.email.domain-models:domain-utils")
    implementation(libs.bundles.kotlin.stdlib)
    implementation(libs.bundles.kotlinx.coroutines)
    implementation(libs.bundles.kamereo.logging)
    implementation(libs.microutils.kotlin.logging)
    implementation(libs.sentry.core)

    implementation("org.springframework:spring-beans")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-autoconfigure")

    api("vn.kamereo:kommandbus:$versionKommandBus")
    api("vn.kamereo:utils-error:$versionKotlinUtils")

    api("co.elastic.apm:apm-agent-api:$versionElasticApm")
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
