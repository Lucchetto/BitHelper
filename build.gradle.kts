buildscript {
    dependencies {
        classpath(libs.buildscript.sqlDelight)
        classpath(libs.buildscript.mokoResources)
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.2.2").apply(false)
    id("com.android.library").version("7.2.2").apply(false)
    kotlin("android").version("1.7.20").apply(false)
    kotlin("multiplatform").version("1.7.20").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
