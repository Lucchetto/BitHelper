plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.google.devtools.ksp") version "1.7.20-1.0.8"
    id("kotlinx-serialization")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false

            export(libs.common.mokoResources)
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.common.mokoResources)

                implementation(libs.common.coroutines)
                implementation(libs.common.ktorContentNegotiation)
                implementation(libs.common.ktorJsonSerialization)
                implementation(libs.common.ktorfit)
                implementation(libs.common.sqlDelight)
                implementation(libs.common.sqlDelightExt)
                implementation(libs.common.koin)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.common.koinTest)
                implementation(libs.common.mokoResourcesTest)

                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.android.sqlDelight)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(libs.native.sqlDelight)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.zhenxiang.bithelper.shared"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.common.ktorfitKsp)
    add("kspAndroid", libs.common.ktorfitKsp)
}

sqldelight {
    database("StorageDb") {
        packageName = "com.zhenxiang.bithelper.shared.db"
        sourceFolders = listOf("sqldelight")
        schemaOutputDirectory = file("src/commonMain/sqldelight/schemas")
        verifyMigrations = true
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.zhenxiang.bithelper.shared.res"
    multiplatformResourcesClassName = "SharedRes"
}
