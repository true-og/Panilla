import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.Copy

plugins {
    java
    id("com.gradleup.shadow") version "8.3.8"
}

val pluginVersion = rootProject.version.toString()

dependencies {
    implementation(project(":panilla-api"))
    implementation(project(":panilla-craftbukkit-v1_8_R3"))
    implementation(project(":panilla-craftbukkit-v1_12_R1"))
    implementation(project(":panilla-craftbukkit-v1_13_R2"))
    implementation(project(":panilla-craftbukkit-v1_14_R1"))
    implementation(project(":panilla-craftbukkit-v1_15_R1"))
    implementation(project(":panilla-craftbukkit-v1_16_R1"))
    implementation(project(":panilla-craftbukkit-v1_16_R2"))
    implementation(project(":panilla-craftbukkit-v1_16_R3"))
    implementation(project(":panilla-craftbukkit-v1_17_R1"))
    implementation(project(":panilla-craftbukkit-v1_18_R1"))
    implementation(project(":panilla-craftbukkit-v1_18_R2"))
    implementation(project(":panilla-craftbukkit-v1_19_R1"))
    implementation(project(":panilla-craftbukkit-v1_19_R2"))
    implementation(project(":panilla-craftbukkit-v1_19_R3"))
    implementation("org.bukkit:bukkit-1.13:dev")
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot:1.19.4-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.named<Copy>("processResources") {
    filesMatching("**/plugin.yml") {
        expand(mapOf("version" to pluginVersion))
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveFileName.set("Panilla.jar")
    destinationDirectory.set(file("../target/bukkit"))
}

tasks.named("assemble") {
    dependsOn("shadowJar")
}

tasks.register<Exec>("runCopyJarScript") {
    group = "build"
    workingDir = rootDir
    commandLine("sh", "copyjar.sh", project.version.toString())
}

tasks.named("build") {
    finalizedBy("runCopyJarScript")
}

