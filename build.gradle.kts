plugins {
    id("eclipse")
}

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
}

tasks.register<Delete>("clean") {
    delete("target")
}

