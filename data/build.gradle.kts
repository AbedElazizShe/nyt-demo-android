import dependencies.DataDep

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(project(Modules.domain))
    implementation(DataDep.kotlin)
    implementation(DataDep.coroutineCore)
    implementation(DataDep.javax)

    testImplementation(DataDep.Test.junit)
    testImplementation(DataDep.Test.coroutines)
    testImplementation(DataDep.Test.mockitoKotlin)
    testImplementation(DataDep.Test.mockitoInline)
}
