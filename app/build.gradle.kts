plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.sep.quiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sep.quiz"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    with(libs) {
        with(androidx) {
            implementation(core.ktx)
            implementation(lifecycle.runtime.ktx)
            implementation(activity.compose)
            implementation(platform(compose.bom))
            implementation(ui)
            implementation(ui.graphics)
            implementation(ui.tooling.preview)
            implementation(material3)
            androidTestImplementation(junit)
            androidTestImplementation(espresso.core)
            androidTestImplementation(platform(compose.bom))
            androidTestImplementation(ui.test.junit4)
            debugImplementation(ui.tooling)
            debugImplementation(ui.test.manifest)
        }
        testImplementation(junit)

        implementation(retrofit)
        implementation(retrofit.kotlin.serialization)
        implementation(logging.interceptor)
        implementation(kotlinx.serialization.json)

        implementation("com.google.dagger:hilt-android:2.51.1")
        kapt("com.google.dagger:hilt-compiler:2.51.1")


        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
        implementation("androidx.navigation:navigation-compose:2.7.7")

        // For instrumentation tests
        androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
        kaptAndroidTest("com.google.dagger:hilt-compiler:2.51.1")

        // For local unit tests
        testImplementation("com.google.dagger:hilt-android-testing:2.51.1")
        kaptTest("com.google.dagger:hilt-compiler:2.51.1")
    }

}