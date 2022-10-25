import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import java.util.Properties


plugins {
    id("idea")
    id("vn.kamereo.spring-boot-application") version "1.1.6"
    id("vn.kamereo.kotlin") version "1.1.6"
    id("io.gitlab.arturbosch.detekt")
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    kotlin("kapt")
}
val properties = Properties().apply {
    load(rootProject.file("../gradle.properties").reader())
}
val versionGrpcDefinitions: String by properties
val versionBuildTools: String by properties
val versionKotlinUtils: String by properties

dependencies {
    implementation("vn.kamereo.email-service:api")

    implementation(platform("vn.kamereo.platform:product-platform:$versionBuildTools"))

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("vn.kamereo:utils-spring:$versionKotlinUtils")
    implementation("vn.kamereo", "kommandbus", "0.2.2")

    implementation("vn.kamereo.email.features:send-email")
    implementation("vn.kamereo", "grpc-definitions-kotlin", versionGrpcDefinitions)
    api("vn.kamereo:utils-grpc:$versionKotlinUtils")
    api("vn.kamereo:utils-error:$versionKotlinUtils")

    implementation(libs.sentry.core)
    implementation(libs.sentry.spring.boot)

    implementation(libs.logback.classic)
    implementation(libs.janino)
    implementation(libs.logstash.logback.encoder)

    implementation(platform(libs.grpc.bom))
    implementation(platform(libs.protobuf.bom))
    implementation(libs.protobuf.java)
    implementation(libs.protobuf.java.util)
    implementation(libs.protobuf.kotlin)

    runtimeOnly(libs.grpc.netty.shaded)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    implementation(libs.grpc.kotlin.stub)

    testImplementation(platform("vn.kamereo.platform:test-platform:$versionBuildTools"))
}

detekt {
    toolVersion = detekt.toolVersion
    source = files("src/main/kotlin", "src/test/kotlin")
    parallel = true
    config = files(rootProject.file("../config/detekt/detekt.yml"))
}

tasks.withType<Detekt>().configureEach {
    this.jvmTarget = "11"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    this.jvmTarget = "11"
}
