plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    kotlin("kapt")
    id(Plugin.dsggerHilt)
}

android {
    namespace = "test.example.presentation"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation(project(":domain"))
    implementation(Dep.core)
    implementation(Dep.lifecycle)
    implementation(Dep.activityCompose)
    implementation(platform(Dep.composeBom))
    implementation(Dep.composeUi)
    implementation(Dep.composeUiGraphics)
    implementation(Dep.composeUiToolingPreview)
    implementation(Dep.composeMaterial)
    implementation(Dep.composeViewModel)
    implementation(Dep.hilt)
    kapt(Dep.hiltCompiler)
    testImplementation(TestDep.junit)
    androidTestImplementation(TestDep.junitExt)
    androidTestImplementation(TestDep.expresso)

}