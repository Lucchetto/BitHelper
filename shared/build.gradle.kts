plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("dev.icerock.mobile.multiplatform-resources")
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
    namespace = "com.zhenxiang.bithelper"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

sqldelight {
    database("StorageDb") {
        packageName = "com.zhenxiang.bithelper.db"
        sourceFolders = listOf("sqldelight")
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.zhenxiang.bithelper.res"
    multiplatformResourcesClassName = "SharedRes"
}
