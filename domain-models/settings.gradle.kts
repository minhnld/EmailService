dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from("vn.kamereo.build:catalog:1.1.6")
        }
    }
}
rootProject.name = "vn.kamereo.email.domain-models"

include("domain-utils")
include("time-domain")
include("email-domain")

