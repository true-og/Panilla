dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        val custom = System.getProperty("SELF_MAVEN_LOCAL_REPO")?.let {
            if (it.startsWith("~")) it.replaceFirst("^~".toRegex(), System.getProperty("user.home")) else it
        }
        if (custom != null) {
            val dir = File(custom)
            if (dir.isDirectory) {
                println("Using SELF_MAVEN_LOCAL_REPO at: $dir")
                maven { url = uri(dir) }
            } else {
                mavenLocal()
            }
        } else {
            mavenLocal()
        }
        mavenCentral()
        maven { url = uri("https://mvn.lumine.io/repository/maven-public/") }
    }
}

rootProject.name = "Panilla"

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

