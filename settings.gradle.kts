dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from("vn.kamereo.build:catalog:1.1.6")
        }
    }
}

rootProject.name = "email-service"

includeBuild("domain-models")
includeBuild("infrastructures")
includeBuild("features")
includeBuild("applications")
includeBuild("api")
