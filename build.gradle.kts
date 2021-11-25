// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.0.0")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}