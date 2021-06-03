package dependencies

object Dependencies {

    object KotlinDep {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    }

    const val javax = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"

    object CoreDep {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtxVersion}"
    }

    object TestDep {
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestVersion}"
        const val mockitoKotlin =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}"
        const val androidxArchCore =
            "androidx.arch.core:core-testing:${Versions.androidxArchCoreVersion}"
    }

    object RetrofitDep {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val jacksonConverter = "com.squareup.retrofit2:converter-jackson:${Versions.retrofitVersion}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}"
    }
    object DaggerHiltDep {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}"
        const val hiltAndroidKapt = "com.google.dagger:hilt-compiler:${Versions.hiltAndroidVersion}"
        const val hiltKapt = "androidx.hilt:hilt-compiler:${Versions.hiltVersion}"
    }

    object CoroutinesDep {
        const val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCoreVersion}"
    }

    object LifeCycleDep {
        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleVersion}"
        const val liveDataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleVersion}"
        const val lifeCycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleVersion}"
    }

}