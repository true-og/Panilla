import org.gradle.api.artifacts.component.ModuleComponentSelector

plugins {
    id("eclipse")
}

val hasBootstrapRepo = System.getProperty("SELF_MAVEN_LOCAL_REPO")
    ?.takeIf { it.isNotBlank() }
    ?.let { custom ->
        val resolvedPath = if (custom.startsWith("~")) {
            custom.replaceFirst("^~".toRegex(), System.getProperty("user.home"))
        } else {
            custom
        }
        file(resolvedPath).isDirectory
    }
    ?: false

allprojects {
    group = "com.ruinscraft"
    version = "1.9.0"
}

subprojects {
    apply(plugin = "java")

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    if (!hasBootstrapRepo) {
        configurations.configureEach {
            resolutionStrategy.dependencySubstitution {
                all {
                    val requestedModule = requested as? ModuleComponentSelector ?: return@all
                    if (requestedModule.group == "org.spigotmc" && requestedModule.module == "spigot") {
                        useTarget(
                            "org.bukkit:craftbukkit:${requestedModule.version}",
                            "TrueOG Bootstrap is unavailable, so fall back to mirrored CraftBukkit artifacts for server internals."
                        )
                    }
                }
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
    delete("target")
}
