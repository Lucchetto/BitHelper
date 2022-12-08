plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zhenxiang.bithelper.ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.android.accompanistPager)
    implementation(libs.android.accompanistPlaceholder)
    implementation(libs.android.androidXCore)
    implementation(libs.android.composeMaterial3)
    implementation(libs.android.composeUiToolingPreview)
}