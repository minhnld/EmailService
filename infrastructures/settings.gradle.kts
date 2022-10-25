dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from("vn.kamereo.build:catalog:1.1.6")
        }
    }
}
rootProject.name = "vn.kamereo.email.infrastructures"

includeBuild("../domain-models")

include("sendgrid-email-service")
