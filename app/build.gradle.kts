plugins {
    id(Plugin.androidApp)
    id(Plugin.kotlinAndroid)
    kotlin("kapt")
    id(Plugin.dsggerHilt)
}

android {
    namespace = Config.applicationId
    compileSdk = Config.defaultCompileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.defaultMinSdkVersion
        targetSdk = Config.defaultTargetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testRunner
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

    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(project(":data"))
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