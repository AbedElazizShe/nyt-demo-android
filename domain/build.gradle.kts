import dependencies.DomainDep

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(DomainDep.kotlin)
    implementation(DomainDep.coroutineCore)

    implementation(DomainDep.javax)

    testImplementation(DomainDep.Test.junit)
    testImplementation(DomainDep.Test.coroutines)
    testImplementation(DomainDep.Test.mockitoKotlin)
    testImplementation(DomainDep.Test.mockitoInline)
}
