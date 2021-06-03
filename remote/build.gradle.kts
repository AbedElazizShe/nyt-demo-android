import dependencies.RemoteDep

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(project(Modules.data))
    implementation(RemoteDep.kotlin)
    implementation(RemoteDep.javax)

    RemoteDep.retrofit.forEach { implementation(it) }

    testImplementation(RemoteDep.Test.junit)
    testImplementation(RemoteDep.Test.coroutines)
    testImplementation(RemoteDep.Test.mockitoKotlin)
    testImplementation(RemoteDep.Test.mockitoInline)
}
