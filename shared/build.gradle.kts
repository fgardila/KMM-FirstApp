import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    //kotlin("multiplatform") version "1.5.31"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val ktorVersion = "1.6.3"
        val sql_delight_version = "1.5.0"
        val commonMain by getting {
            dependencies {
                //Logger
                //implementation("com.github.aakira:napier:2.1.0")
                implementation("io.github.aakira:napier:2.1.0")

                // Date Time
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.0")

                // Ktor
                implementation("io.ktor:ktor-client-core:1.6.3")
                implementation("io.ktor:ktor-client-logging:1.6.3")
                implementation("io.ktor:ktor-client-serialization:1.6.3")

                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")
                implementation("com.squareup.sqldelight:runtime:$sql_delight_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.6.3")
                implementation("com.squareup.sqldelight:android-driver:$sql_delight_version")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.3")
                implementation("com.squareup.sqldelight:native-driver:$sql_delight_version")
            }
        }
        val iosTest by getting
    }
}

android {
    //compileSdkVersion(31)
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        //minSdkVersion(26)
        minSdk = 26
        //targetSdkVersion(31)
        targetSdk = 31
    }
    ndkVersion = "22.1.7171670"
}

sqldelight {
    database("MyDatabase") {
        packageName = "com.example.db"
        sourceFolders = listOf("db")
        schemaOutputDirectory = file("build/dbs")
        dependency(project(":OtherProject"))
        dialect = "sqlite:3.24"
        verifyMigrations = true
    }
    linkSqlite = false
}