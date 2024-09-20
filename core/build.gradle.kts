plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

compose.resources {
    generateResClass = never
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-okhttp:2.3.12")
        }
        commonMain.dependencies {
            implementation("com.russhwolf:multiplatform-settings:1.0.0")
            implementation("com.russhwolf:multiplatform-settings-no-arg:1.0.0")

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.cafe.adriel.voyager.voyager.navigator)
            implementation(libs.cafe.adriel.voyager.voyager.transitions)
            implementation(libs.bundles.ktor)
            implementation(libs.koin.core)
            implementation(libs.multiplatform.settings)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.ktor.client.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.kotlinx.serialization.json)
        }
        iosMain.dependencies{
            implementation("io.ktor:ktor-client-ios:2.3.12")
        }

    }
}

android {
    namespace = "com.module.core"
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig { minSdk = libs.versions.android.minSdk.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

