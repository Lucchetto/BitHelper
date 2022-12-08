plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zhenxiang.bithelper.form"
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
        kotlinCompilerExtensionVersion = libs.versions.composeVersion.get()
    }
}

dependencies {
    implementation(project(":androidApp:ui"))

    implementation(libs.android.composeMaterial3)
    implementation(libs.android.dokar3Sheets)

    testImplementation(libs.android.junit)
}