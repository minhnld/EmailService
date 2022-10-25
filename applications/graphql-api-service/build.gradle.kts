import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    id("vn.kamereo.spring-boot-application") version "1.1.6"
    id("vn.kamereo.kotlin") version "1.1.6"
    id("idea")
    application

    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    kotlin("kapt")

    id("com.netflix.dgs.codegen") version "5.0.6"
    id("io.gitlab.arturbosch.detekt")
}

group = "vn.kamereo"
java.sourceCompatibility = JavaVersion.VERSION_11

val properties = Properties().apply {
    load(rootProject.file("../gradle.properties").reader())
}
val versionBuildTools: String by properties
val versionKotlinUtils: String by properties
val versionGrpcDefinitions: String by properties
val versionElasticApm: String by properties
val versionJooq: String by properties
val versionAuth0: String by properties

application {
    mainClass.set("vn.kamereo.internalservice.web.WebApplicationKt")
}

dependencies {
    // TODO: ENGR-3424
    implementation("co.elastic.apm:apm-agent-api:$versionElasticApm")
    // TODO: Remove the dependency on common-java
    implementation("vn.kamereo:common-java:0.0.121")
    // TODO: Remove the dependency on grpc-definitions if not necessary
    implementation("vn.kamereo:grpc-definitions:$versionGrpcDefinitions")

    implementation(platform("vn.kamereo.platform:product-platform:$versionBuildTools"))

    implementation("vn.kamereo.email.features:send-email")

    implementation(libs.bundles.kotlin.stdlib)
    implementation(libs.bundles.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.sentry.spring.kotlin)
    implementation(libs.bundles.log4j)
    implementation(libs.bundles.kamereo.logging)
    implementation(libs.microutils.kotlin.logging)

    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.1.1"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")
    implementation("com.auth0:java-jwt:3.15.0")

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("vn.kamereo:utils-error:$versionKotlinUtils")
    implementation("vn.kamereo:utils-spring:$versionKotlinUtils")
    implementation("vn.kamereo", "kommandbus", "0.2.2")

    testImplementation(platform("vn.kamereo.platform:test-platform:$versionBuildTools"))
    testImplementation(libs.bundles.junit)
    testImplementation(libs.bundles.kotlin.test)
    testImplementation(libs.kotlin.reflect)
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-Xuse-experimental=kotlin.Experimental",
            "-Xjvm-default=all-compatibility"
        )
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

tasks.register("resolveDependencies") {
    group = "build setup"
    description = "Resolve and prefetch dependencies"
    doLast {
        fun resolve(configContainer: ConfigurationContainer) {
            configContainer
                .filter { c: Configuration -> c.isCanBeResolved }
                .forEach { c -> c.resolve() }
        }
        project.rootProject.allprojects.forEach { subProject ->
            resolve(subProject.buildscript.configurations)
            resolve(subProject.configurations)
        }
    }
}

tasks.generateJava {
    // List of directories containing schema files
    schemaPaths = listOf("$projectDir/src/main/resources/schema").toMutableList()
    // The package name to use to generate sources
    packageName = "vn.kamereo.graphql_api_service.web.graphql"
    language = "JAVA"
    generateInterfaces = true
    generateDataTypes = true
    // Enable generating the type safe query API
    // https://github.com/Netflix/dgs-codegen/issues/52
    generateClient = false
    // Enable this for partially resolving entity fields
    // there is an issue that generated code is not compilable when there is [A!]!
    kotlinAllFieldsOptional = false

    typeMapping = mutableMapOf(
        "NonEmptyString" to "java.lang.String",
        "Money" to "java.math.BigDecimal",
        "Quantity" to "java.math.BigDecimal",
        "Url" to "java.net.URL",
        "DateTime" to "java.time.ZonedDateTime",
        "LocalTime" to "java.time.LocalTime",
        "Binary" to "kotlin.ByteArray"
    )
}

detekt {
    toolVersion = detekt.toolVersion
    source = files("src/main/kotlin", "src/test/kotlin")
    parallel = true
    config = files(
        rootProject.file("../config/detekt/detekt.yml"),
        project.file("config/detekt/detekt.yml")
    )
}
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    this.jvmTarget = "11"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    this.jvmTarget = "11"
}
