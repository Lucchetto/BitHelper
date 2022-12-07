plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
}

android {
    namespace = "com.zhenxiang.bithelper"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.zhenxiang.bithelper"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.0")
    implementation("androidx.compose.ui:ui-tooling:1.3.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.0")
    implementation("androidx.compose.foundation:foundation:1.3.0")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation(libs.android.accompanistNavAnimation)
    implementation(libs.android.accompanistMaterialNav)
    implementation(libs.android.accompanistPager)
    implementation(libs.android.accompanistPlaceholder)
    implementation(libs.android.androidXLifecycle)
    implementation(libs.android.composeMaterial3)
    implementation(libs.android.composeMaterialIcons)
    implementation(libs.android.composeNavigation)
    implementation(libs.android.dokar3Sheets)
    implementation(libs.android.jkuatdscFormBuilder)
    implementation(libs.android.koin)
    implementation(libs.android.koinCompose)
    implementation(libs.android.mokoResources)

    testImplementation(libs.android.junit)
}