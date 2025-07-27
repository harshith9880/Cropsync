// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.android.library") version "8.1.0" apply false
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2") // Use the latest version
    }
}