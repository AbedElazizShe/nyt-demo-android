package dependencies

object DomainDep {

    const val kotlin = Dependencies.KotlinDep.kotlin
    const val coroutineCore = Dependencies.CoroutinesDep.coroutineCore

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val coroutines = Dependencies.TestDep.coroutinesTest
        const val mockitoKotlin = Dependencies.TestDep.mockitoKotlin
        const val mockitoInline = Dependencies.TestDep.mockitoInline
    }

    const val javax = Dependencies.javax
}
