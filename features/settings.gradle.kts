dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from("vn.kamereo.build:catalog:1.1.6")
        }
    }
}

includeBuild("../domain-models")
includeBuild("../infrastructures")

rootProject.name = "vn.kamereo.email.features"

include("send-email")

