// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    alias(libs.plugins.dagger.hilt.google) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.baselineprofile) apply false
    alias(libs.plugins.google.service) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
//    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}