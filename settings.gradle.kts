pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

apply { from("flutter_settings.gradle") }

rootProject.name = "DouYinProject"
include(":app")
include(":flutter_nodule")

