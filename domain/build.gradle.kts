plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    kotlin("kapt")
    id(Plugin.dsggerHilt)
}

android {
    namespace = "test.example.domain"
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

    implementation(Dep.coroutine)
    implementation(Dep.hilt)
    kapt(Dep.hiltCompiler)
    testImplementation(TestDep.junit)
    androidTestImplementation(TestDep.junitExt)
    androidTestImplementation(TestDep.expresso)

}