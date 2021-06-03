import dependencies.PresentationDep

plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.hiltAndroid)
}

android {
    // It is required to use compile version 28 in this demo. I'd use >= 29 so the
    // app won't be rejected at play-store for production apps.
    compileSdkVersion(Config.Android.androidCompileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.Android.androidMinSdkVersion)
        targetSdkVersion(Config.Android.androidTargetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner(Config.testRunner)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Modules
    implementation(project(Modules.domain))

    implementation(PresentationDep.kotlin)
    implementation(PresentationDep.coroutineCore)

    PresentationDep.daggerHilt.forEach {
        implementation(it)
    }
    PresentationDep.daggerHiltKapt.forEach {
        kapt(it)
    }

    PresentationDep.lifeCycle.forEach {
        implementation(it)
    }

    testImplementation(PresentationDep.Test.junit)
    testImplementation(PresentationDep.Test.mockitoKotlin)
    testImplementation(PresentationDep.Test.mockitoInline)
    testImplementation(PresentationDep.Test.coroutines)
    testImplementation(PresentationDep.Test.androidxArchCore)
}
