val homeMavenRepo = uri("file://${System.getProperty("user.home")}/.m2/repository")
val configuredLocalRepo = System.getProperty("SELF_MAVEN_LOCAL_REPO")
    ?.takeIf { it.isNotBlank() }
    ?.let { custom ->
        val resolvedPath = if (custom.startsWith("~")) {
            custom.replaceFirst("^~".toRegex(), System.getProperty("user.home"))
        } else {
            custom
        }
        file(resolvedPath).takeIf { it.isDirectory }
    }

fun orgSpigotContentFilter(repository: org.gradle.api.artifacts.repositories.MavenArtifactRepository) {
    repository.content {
        includeGroup("org.spigotmc")
        includeGroup("org.bukkit")
    }
}

fun mojangLibrariesContentFilter(repository: org.gradle.api.artifacts.repositories.MavenArtifactRepository) {
    repository.content {
        includeGroup("com.mojang")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven {
            name = "UserLocalMaven"
            url = homeMavenRepo
        }

        configuredLocalRepo?.let { dir ->
            println("Using SELF_MAVEN_LOCAL_REPO at: ${dir.absolutePath}")
            maven {
                name = "SelfLocalMaven"
                url = uri(dir)
            }
        } ?: run {
            println("TrueOG Bootstrap not found; using public Spigot mirrors alongside ~/.m2 for dependencies.")
            mavenLocal()
        }

        mavenCentral()
        maven("https://libraries.minecraft.net/") {
            name = "MojangLibraries"
            mojangLibrariesContentFilter(this)
        }
        maven { url = uri("https://mvn.lumine.io/repository/maven-public/") }
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
            name = "SpigotSnapshots"
            orgSpigotContentFilter(this)
        }
        maven("https://repo.loohpjames.com/repository/") {
            name = "LoohpMirror"
            orgSpigotContentFilter(this)
        }
        maven("https://maven.elmakers.com/repository/") {
            name = "ElMakersMirror"
            orgSpigotContentFilter(this)
        }
    }
}

rootProject.name = "Panilla-OG"

include(
    ":panilla-api",
    ":panilla-craftbukkit-v1_8_R3",
    ":panilla-craftbukkit-v1_12_R1",
    ":panilla-craftbukkit-v1_13_R2",
    ":panilla-craftbukkit-v1_14_R1",
    ":panilla-craftbukkit-v1_15_R1",
    ":panilla-craftbukkit-v1_16_R1",
    ":panilla-craftbukkit-v1_16_R2",
    ":panilla-craftbukkit-v1_16_R3",
    ":panilla-craftbukkit-v1_17_R1",
    ":panilla-craftbukkit-v1_18_R1",
    ":panilla-craftbukkit-v1_18_R2",
    ":panilla-craftbukkit-v1_19_R1",
    ":panilla-craftbukkit-v1_19_R2",
    ":panilla-craftbukkit-v1_19_R3",
    ":panilla-bukkit"
)

project(":panilla-api").projectDir = file("api")
project(":panilla-craftbukkit-v1_8_R3").projectDir = file("craftbukkit-v1_8_R3")
project(":panilla-craftbukkit-v1_12_R1").projectDir = file("craftbukkit-v1_12_R1")
project(":panilla-craftbukkit-v1_13_R2").projectDir = file("craftbukkit-v1_13_R2")
project(":panilla-craftbukkit-v1_14_R1").projectDir = file("craftbukkit-v1_14_R1")
project(":panilla-craftbukkit-v1_15_R1").projectDir = file("craftbukkit-v1_15_R1")
project(":panilla-craftbukkit-v1_16_R1").projectDir = file("craftbukkit-v1_16_R1")
project(":panilla-craftbukkit-v1_16_R2").projectDir = file("craftbukkit-v1_16_R2")
project(":panilla-craftbukkit-v1_16_R3").projectDir = file("craftbukkit-v1_16_R3")
project(":panilla-craftbukkit-v1_17_R1").projectDir = file("craftbukkit-v1_17_R1")
project(":panilla-craftbukkit-v1_18_R1").projectDir = file("craftbukkit-v1_18_R1")
project(":panilla-craftbukkit-v1_18_R2").projectDir = file("craftbukkit-v1_18_R2")
project(":panilla-craftbukkit-v1_19_R1").projectDir = file("craftbukkit-v1_19_R1")
project(":panilla-craftbukkit-v1_19_R2").projectDir = file("craftbukkit-v1_19_R2")
project(":panilla-craftbukkit-v1_19_R3").projectDir = file("craftbukkit-v1_19_R3")
project(":panilla-bukkit").projectDir = file("bukkit")
