import org.gradle.api.tasks.SourceSetContainer
import org.gradle.jvm.tasks.Jar
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JvmVendorSpec

plugins {
    java
}

val pluginVersion = rootProject.version.toString()

val shadedProjects = listOf(
    project(":panilla-api"),
    project(":panilla-craftbukkit-v1_8_R3"),
    project(":panilla-craftbukkit-v1_12_R1"),
    project(":panilla-craftbukkit-v1_13_R2"),
    project(":panilla-craftbukkit-v1_14_R1"),
    project(":panilla-craftbukkit-v1_15_R1"),
    project(":panilla-craftbukkit-v1_16_R1"),
    project(":panilla-craftbukkit-v1_16_R2"),
    project(":panilla-craftbukkit-v1_16_R3"),
    project(":panilla-craftbukkit-v1_17_R1"),
    project(":panilla-craftbukkit-v1_18_R1"),
    project(":panilla-craftbukkit-v1_18_R2"),
    project(":panilla-craftbukkit-v1_19_R1"),
    project(":panilla-craftbukkit-v1_19_R2"),
    project(":panilla-craftbukkit-v1_19_R3")
)

fun Project.mainSourceSetOutput() = extensions.getByType<SourceSetContainer>().named("main").map { it.output }

dependencies {
    shadedProjects.forEach { implementation(it) }

    compileOnly("org.bukkit:bukkit-1.13:dev")
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot:1.19.4-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.GRAAL_VM)
    }
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(mapOf("version" to pluginVersion))
    }
}

val pluginJar by tasks.registering(Jar::class) {
    group = LifecycleBasePlugin.BUILD_GROUP
    description = "Assembles the deployable Panilla-OG plugin jar."

    archiveFileName.set("Panilla-OG-${pluginVersion}.jar")
    destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    dependsOn(tasks.classes)
    dependsOn(shadedProjects.map { "${it.path}:classes" })

    from(mainSourceSetOutput())
    shadedProjects.forEach { dependencyProject ->
        from(dependencyProject.mainSourceSetOutput())
    }
}

tasks.named<Jar>("jar") {
    enabled = false
}

tasks.assemble {
    dependsOn(pluginJar)
}

tasks.build {
    dependsOn(pluginJar)
}
