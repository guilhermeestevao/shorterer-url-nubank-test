// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugin.androidApp) version androidPluginVersion apply false
    id(Plugin.kotlinAndroid) version androidKotlinPluginVersion apply false
    id(Plugin.dsggerHilt) version daggerHiltPluginVersion apply false
    id(Plugin.androidLibrary) version androidPluginVersion apply false
}