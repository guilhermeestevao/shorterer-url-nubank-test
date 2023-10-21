import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "test.example.takehome"
    compileSdk = Versions.defaultCompileSdkVersion

    defaultConfig {
        applicationId = "test.example.takehome"
        minSdk = Versions.defaultMinSdkVersion
        targetSdk = Versions.defaultTargetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = Versions.javaCompileVersion
        targetCompatibility = Versions.javaCompileVersion
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Dep.core)
    implementation(Dep.lifecycle)
    implementation(Dep.activityCompose)
    implementation(platform(Dep.composeBom))
    implementation(Dep.composeUi)
    implementation(Dep.composeUiGraphics)
    implementation(Dep.composeUiToolingPreview)
    implementation(Dep.composeMaterial)
    implementation(Dep.okhttp3)
    implementation(Dep.retrofit)
    implementation(Dep.moshiConverter)
    implementation(Dep.moshi)
    implementation(Dep.moshiKotlin)
    implementation(Dep.coroutine)
    implementation(Dep.hilt)
    kapt(Dep.hiltCompiler)

    testImplementation(TestDep.junit)
    androidTestImplementation(TestDep.junitExt)
    androidTestImplementation(TestDep.expresso)
    androidTestImplementation(platform(Dep.composeBom))
    androidTestImplementation(TestDep.composeUiTest)
    debugImplementation(TestDep.composeUiTooling)
    debugImplementation(TestDep.composeUiTestManifest)
    debugImplementation(TestDep.coroutine)

}