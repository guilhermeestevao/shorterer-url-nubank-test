plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
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

dependencies {

    implementation(Dep.core)
    testImplementation(TestDep.junit)
    implementation(Dep.coroutine)
    androidTestImplementation(TestDep.junitExt)
    androidTestImplementation(TestDep.expresso)

}