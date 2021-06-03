package dependencies

object RemoteDep {

    const val kotlin = Dependencies.KotlinDep.kotlin

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val coroutines = Dependencies.TestDep.coroutinesTest
        const val mockitoKotlin = Dependencies.TestDep.mockitoKotlin
        const val mockitoInline = Dependencies.TestDep.mockitoInline
    }

    val retrofit = listOf(
        Dependencies.RetrofitDep.retrofit,
        Dependencies.RetrofitDep.gsonConverter,
        Dependencies.RetrofitDep.jacksonConverter,
        Dependencies.RetrofitDep.loggingInterceptor
    )

    const val javax = Dependencies.javax
}
