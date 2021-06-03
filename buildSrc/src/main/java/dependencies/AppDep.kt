package dependencies

object AppDep {

    const val kotlin = Dependencies.KotlinDep.kotlin
    const val coreKtx = Dependencies.CoreDep.coreKtx
    const val appCompat = Dependencies.CoreDep.appCompat
    const val material = Dependencies.CoreDep.material
    const val constraintLayout = Dependencies.CoreDep.constraintLayout
    const val activityKtx = Dependencies.CoreDep.activityKtx

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val junitExt = Dependencies.TestDep.junitExt
    }

    const val DaggerHilt =   Dependencies.DaggerHiltDep.hiltAndroid
    const val DaggerHiltKapt =  Dependencies.DaggerHiltDep.hiltAndroidKapt
}
