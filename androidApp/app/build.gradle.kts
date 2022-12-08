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
        kotlinCompilerExtensionVersion = libs.versions.composeVersion.get()
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
    implementation(project(":androidApp:form"))
    implementation(project(":androidApp:ui"))
    implementation(project(":shared"))
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation(libs.android.accompanistNavAnimation)
    implementation(libs.android.accompanistMaterialNav)
    implementation(libs.android.accompanistPlaceholder)
    implementation(libs.android.androidXLifecycle)
    implementation(libs.android.composeFoundation)
    implementation(libs.android.composeMaterial3)
    implementation(libs.android.composeMaterialIcons)
    implementation(libs.android.composeNavigation)
    implementation(libs.android.composeUi)
    implementation(libs.android.composeUiTooling)
    implementation(libs.android.composeUiToolingPreview)
    implementation(libs.android.koin)
    implementation(libs.android.koinCompose)

    testImplementation(libs.android.junit)
}