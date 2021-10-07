pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://www.jetbrains.com/intellij-repository/releases")
        maven(url = "https://jetbrains.bintray.com/intellij-third-party-dependencies")
    }
}

rootProject.name = "KMM-FirstApp"
include(":androidApp")
include(":shared")