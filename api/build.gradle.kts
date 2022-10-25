import com.google.protobuf.gradle.*

val versionBuildTools = "1.1.3"

plugins {
    id("vn.kamereo.java-library") version "1.1.3"
    id("vn.kamereo.kotlin") version "1.1.3"
    id("com.google.protobuf") version "0.8.19"
}

version = "internal"
group = "vn.kamereo.email-service"

dependencies {
    implementation(platform("vn.kamereo.platform:product-platform:$versionBuildTools"))

    api(platform(libs.grpc.bom))
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")

    api(libs.protobuf.kotlin)

    api(libs.grpc.protobuf)
    api(libs.grpc.stub)
    api(libs.grpc.kotlin.stub)

    implementation(kotlin("stdlib"))
    testImplementation(platform("vn.kamereo.platform:test-platform:$versionBuildTools"))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.47.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}
