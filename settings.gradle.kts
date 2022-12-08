pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "BitHelper"
include(":androidApp:app")
include(":shared")
include(":androidApp:form")
include(":androidApp:ui")
