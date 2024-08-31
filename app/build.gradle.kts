plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.google.service)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.protobuf)
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.sep.byteClub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sep.byteClub"
        minSdk = 26
        targetSdk = 34
        versionCode = 201
        versionName = "2.0.1"

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

    "baselineProfile"(project(":baselineprofile"))
    with(libs) {
        with(androidx) {
            implementation(compose.ui.constraintlayout)
            implementation(customactivityoncrash)
            implementation(core.ktx)
            implementation(lifecycle.runtime.ktx)
            implementation(lifecycle.runtime.compose.android)
            implementation(activity.compose)
            implementation(profileinstaller)
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
            implementation(hilt.navigation)
            implementation(navigation.compose)
            implementation(dataStore.core)
            implementation(dataStore.prefreneces)

            implementation(room.runtime)
            annotationProcessor(room.compiler)
            kapt(room.compiler)
            implementation(room.ktx)
            implementation(room.guava)
            testImplementation(room.testing)
            implementation(room.paging)
        }
        testImplementation(junit)

        implementation(retrofit)
        implementation(retrofit.kotlin.serialization)
        implementation(logging.interceptor)
        implementation(kotlinx.serialization.json)

        api(google.protobuf)
        api(protobuf.kotlin.lite)

        implementation(dagger.hilt)
        kapt(dagger.hilt.compiler)


        // For instrumentation tests
        androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
        kaptAndroidTest("com.google.dagger:hilt-compiler:2.51.1")

        // For local unit tests
        testImplementation("com.google.dagger:hilt-android-testing:2.51.1")
        kaptTest("com.google.dagger:hilt-compiler:2.51.1")

        implementation("com.patrykandpatrick.vico:compose:2.0.0-alpha.19")
        implementation("com.patrykandpatrick.vico:compose-m3:2.0.0-alpha.19")
        implementation("com.patrykandpatrick.vico:core:2.0.0-alpha.19")
        implementation("com.patrykandpatrick.vico:views:2.0.0-alpha.19")


        implementation(platform(firebase))
        implementation(firebase.analytic)
        implementation(firebase.auth)
        implementation(firebase.firestore)
        implementation(firebase.crashlytics)

//        implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    }

}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}