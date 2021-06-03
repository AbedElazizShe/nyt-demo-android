import dependencies.AppDep
import java.io.FileInputStream
import java.util.*

plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.hiltAndroid)
}

val keys = rootProject.file("keys.properties")
val keysProperty = Properties()
keysProperty.load(FileInputStream(keys))

android {
    // It is required to use compile version 28 in this demo. I'd use >= 29 so the
    // app won't be rejected at play-store for production apps.
    compileSdkVersion(Config.Android.androidCompileSdkVersion)

    defaultConfig {
        applicationId = "com.sicpa.nytimedemo"
        minSdkVersion(Config.Android.androidMinSdkVersion)
        targetSdkVersion(Config.Android.androidTargetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String", "API_KEY",
            keysProperty["API_KEY"].toString()
        )
        buildConfigField(
            "String",
            "BASE_URL",
            "\"" + "https://api.nytimes.com/svc/" + "\""
        )
        testInstrumentationRunner(Config.testRunner)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(
        fileTree(
            mapOf(
                "dir" to "libs",
                "include" to listOf("*.jar")
            )
        )
    )

    // Modules
    implementation(project(Modules.remote))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.presentation))

    implementation(AppDep.kotlin)
    implementation(AppDep.coreKtx)
    implementation(AppDep.appCompat)
    implementation(AppDep.material)
    implementation(AppDep.constraintLayout)
    implementation(AppDep.activityKtx)

    implementation(AppDep.DaggerHilt)
    kapt(AppDep.DaggerHiltKapt)

    testImplementation(AppDep.Test.junit)
    androidTestImplementation(AppDep.Test.junitExt)
}
