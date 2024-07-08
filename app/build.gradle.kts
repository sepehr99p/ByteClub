plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.baselineprofile)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.sep.quiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sep.quiz"
        minSdk = 26
        targetSdk = 34
        versionCode = 103
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            ndk {
//                abiFilters.clear()
//                abiFilters.addAll(mutableSetOf("armeabi", "armeabi-v7a", "arm64-v8a"))
//            }
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

    implementation(libs.androidx.profileinstaller)
    "baselineProfile"(project(":baselineprofile"))
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


        // Import the Firebase BoM
        implementation(platform("com.google.firebase:firebase-bom:33.1.1"))

        // When using the BoM, you don't specify versions in Firebase library dependencies

        // Add the dependency for the Firebase SDK for Google Analytics
        implementation("com.google.firebase:firebase-analytics")

        // TODO: Add the dependencies for any other Firebase products you want to use
        // See https://firebase.google.com/docs/android/setup#available-libraries
        // For example, add the dependencies for Firebase Authentication and Cloud Firestore
        implementation("com.google.firebase:firebase-auth")
        implementation("com.google.firebase:firebase-firestore")

        // Import the BoM for the Firebase platform
        implementation(platform("com.google.firebase:firebase-bom:33.1.1"))

        // Add the dependencies for the Crashlytics and Analytics libraries
        // When using the BoM, you don't specify versions in Firebase library dependencies
        implementation("com.google.firebase:firebase-crashlytics")
        implementation("com.google.firebase:firebase-analytics")

        val room_version = "2.6.1"

        implementation("androidx.room:room-runtime:$room_version")
        annotationProcessor("androidx.room:room-compiler:$room_version")

        kapt("androidx.room:room-compiler:$room_version")

        // optional - Kotlin Extensions and Coroutines support for Room
        implementation("androidx.room:room-ktx:$room_version")

        // optional - Guava support for Room, including Optional and ListenableFuture
        implementation("androidx.room:room-guava:$room_version")

        // optional - Test helpers
        testImplementation("androidx.room:room-testing:$room_version")

        // optional - Paging 3 Integration
        implementation("androidx.room:room-paging:$room_version")
    }

}