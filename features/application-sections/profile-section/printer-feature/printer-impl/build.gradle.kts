plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "printerimpl"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:app"))
            implementation(project(":core:recources"))
            implementation(project(":core:local-storage"))
            implementation(project(":core:network"))
            implementation("com.journeyapps:zxing-android-embedded:4.3.0")
            implementation(project(":features:application-sections:profile-section:printer-feature:printer-api"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
             implementation(libs.koin.core)
            implementation(libs.cafe.adriel.voyager.voyager.navigator)
            implementation(libs.cafe.adriel.voyager.voyager.transitions)
        }
        androidMain.dependencies {
            implementation(project(":common:printer-barcode-tsc"))
            implementation(project(":common:printer-barcode-vkp"))
          //  implementation(project(":common:phone"))

        }
        nativeMain.dependencies{
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.project.printerimpl"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
