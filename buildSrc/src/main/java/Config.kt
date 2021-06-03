object Config {

    object Android {
        const val androidMinSdkVersion = 21
        const val androidTargetSdkVersion = 28
        const val androidCompileSdkVersion = 28
    }

    object ClassPaths {
        const val androidGradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val jitPackUrl = "https://jitpack.io"
        const val googleUrl = "https://maven.google.com/"
        const val pluginGradle = "https://plugins.gradle.org/m2/"
        const val daggerHiltGradle =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroidVersion}"
    }

    object Plugins {
        const val android = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val kotlin = "kotlin"
        const val javaLibrary = "java-library"
        const val kotlinKapt = "kotlin-kapt"
        const val androidLibrary = "com.android.library"
        const val hiltAndroid = "dagger.hilt.android.plugin"
    }

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}
