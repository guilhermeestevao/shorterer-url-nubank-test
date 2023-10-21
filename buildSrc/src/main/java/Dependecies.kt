import org.gradle.api.JavaVersion

object Versions {
    val javaCompileVersion = JavaVersion.VERSION_17
    val jvmTarget = "17"
    val defaultCompileSdkVersion = 34
    val defaultTargetSdkVersion = 34
    val defaultMinSdkVersion = 26

    val coreKtx = "1.9.0"
    val lifecycleRuntimeKtx = "2.6.2"

    val activityCompose = "1.8.0"
    val composeBom = "2023.03.00"

    val okhttp = "4.9.0"
    val retrofit = "2.9.0"
    val moshi = "1.13.0"
    val coroutines = "1.5.0"
    val hilt = "2.44"

    val junit = "4.13.2"
    val junitExt = "1.1.5"
    val espresso = "3.5.1"
}

object Dep {
    val core = "androidx.core:core-ktx:${Versions.coreKtx}"
    val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    val composeUi = "androidx.compose.ui:ui"
    val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    val composeMaterial = "androidx.compose.material3:material3"
    val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}

object TestDep {
    val junit = "junit:junit:${Versions.junit}"
    val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    val expresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    val composeUiTest = "androidx.compose.ui:ui-test-junit4"
    val composeUiTooling = "androidx.compose.ui:ui-tooling"
    val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
}