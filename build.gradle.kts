// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.5.10")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
        classpath(Config.ClassPaths.daggerHiltGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = Config.ClassPaths.googleUrl)
        maven(url = Config.ClassPaths.pluginGradle)
        maven(url = Config.ClassPaths.jitPackUrl)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}