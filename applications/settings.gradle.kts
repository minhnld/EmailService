dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from("vn.kamereo.build:catalog:1.1.6")
        }
    }
}
rootProject.name = "vn.kamereo.email.application"
includeBuild("../domain-models")
includeBuild("../infrastructures")
includeBuild("../features")
includeBuild("../api")

include("grpc-service")
include("graphql-api-service")
