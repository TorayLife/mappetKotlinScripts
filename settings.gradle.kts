
rootProject.name = "mappetKotlinScripts"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://maven.minecraftforge.net/") }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "cz.habarta.typescript-generator") {
                useModule("cz.habarta.typescript-generator:typescript-generator-gradle-plugin:${requested.version ?: "+"}")
            }
        }
    }
}