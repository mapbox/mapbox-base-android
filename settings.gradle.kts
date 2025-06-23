include(":common", ":annotations", ":annotations-processor", ":liblogger", ":libloader", ":examples")
rootProject.name = "Mapbox Base Android"

enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    versionCatalogs {
        create("baseLibs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}