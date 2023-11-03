import org.gradle.api.JavaVersion

object Config {
    val javaCompileVersion = JavaVersion.VERSION_17
    val jvmTarget = "17"
    val defaultCompileSdkVersion = 34
    val defaultTargetSdkVersion = 34
    val defaultMinSdkVersion = 26
    val versionCode = 1
    val versionName = "1.0"
    val applicationId = "test.example.takehome"
    val testRunner = "test.example.takehome.ShortenerUrlAppTestRunner"
    val kotlinCompilerExtensionVersion = "1.4.3"
}

object Dep {
    val core = "androidx.core:core-ktx:$coreKtxVersion"
    val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKtxVersion"
    val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
    val composeBom = "androidx.compose:compose-bom:$composeBomVersion"
    val composeUi = "androidx.compose.ui:ui"
    val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose"
    val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    val composeMaterial = "androidx.compose.material3:material3"
    val navigationCompose = "androidx.navigation:navigation-compose:$navigationVerson"
    val okhttp3 = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
    val room = "androidx.room:room-runtime:$roomVersion"
    val rooKtx = "androidx.room:room-ktx:$roomVersion"
    val roomCompailer = "androidx.room:room-compiler:$roomVersion"
    val navigation = "androidx.navigation:navigation-runtime-ktx:$navigationVerson"
}

object TestDep {
    val junit = "junit:junit:$junitVersion"
    val junitExt = "androidx.test.ext:junit:$junitExtVersion"
    val expresso = "androidx.test.espresso:espresso-core:$espressoVersion"
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
    val composeUiTest = "androidx.compose.ui:ui-test-junit4"
    val composeUiTooling = "androidx.compose.ui:ui-tooling"
    val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    val mockito = "org.mockito.kotlin:mockito-kotlin:$mockitoVersion"
    val testCore = "androidx.test:core:$androidTestCoreVersion"
    val testRunner = "androidx.test:runner:$androidTestCoreVersion"
    val testRules = "androidx.test:rules:$androidTestCoreVersion"
    val orchestrator = "androidx.test:orchestrator:$androidTestOrchestratorVersion"
    val hilt = "com.google.dagger:hilt-android-testing:$hiltVersion"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
}

object Plugin {
    val androidApp = "com.android.application"
    val androidLibrary = "com.android.library"
    val kotlinAndroid = "org.jetbrains.kotlin.android"
    val dsggerHilt = "com.google.dagger.hilt.android"
}

val androidPluginVersion = "8.1.2"
val daggerHiltPluginVersion = "2.44"
val androidKotlinPluginVersion = "1.8.10"

private const val coreKtxVersion = "1.9.0"
private const val lifecycleRuntimeKtxVersion = "2.6.2"
private const val activityComposeVersion = "1.8.0"
private const val composeBomVersion = "2023.03.00"
private const val okhttpVersion = "4.9.0"
private const val retrofitVersion = "2.9.0"
private const val moshiVersion = "1.13.0"
private const val coroutinesVersion = "1.5.0"
private const val hiltVersion = "2.44"
private const val junitVersion = "4.13.2"
private const val junitExtVersion = "1.1.5"
private const val espressoVersion = "3.5.1"
private const val roomVersion = "2.5.0"
private const val navigationVerson = "2.5.3"
private const val hiltNavigationComposeVersion = "1.0.0"
private const val mockitoVersion =  "4.0.0"
private const val androidTestCoreVersion = "1.4.0"
private const val androidTestOrchestratorVersion = "1.4.1"