plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    kotlin("kapt")
    id(Plugin.dsggerHilt)
}

android {
    namespace = "test.example.data"
    compileSdk = Config.defaultCompileSdkVersion

    defaultConfig {
        minSdk = Config.defaultMinSdkVersion

        testInstrumentationRunner = Config.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.javaCompileVersion
        targetCompatibility = Config.javaCompileVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation(Dep.core)
    implementation(Dep.coroutine)
    testImplementation(TestDep.junit)
    implementation(Dep.okhttp3)
    implementation(Dep.retrofit)
    implementation(Dep.moshiConverter)
    implementation(Dep.moshi)
    implementation(Dep.moshiKotlin)
    implementation(Dep.room)
    implementation(Dep.rooKtx)
    annotationProcessor(Dep.roomCompailer)
    kapt(Dep.roomCompailer)
    implementation(Dep.hilt)
    kapt(Dep.hiltCompiler)
    testImplementation(TestDep.junit)
    testImplementation(TestDep.mockito)
    testImplementation(TestDep.coroutine)

}