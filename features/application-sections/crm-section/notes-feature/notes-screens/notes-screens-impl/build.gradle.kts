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
            baseName = "notes_screens_impl"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":features:application-sections:crm-section:notes-feature:notes-screens:notes-screens-api"))
            implementation(project( ":features:application-sections:crm-section:munu-crm-feature:menu-crm-api"))

            implementation(project(":core:app"))
            implementation(project(":core:recources"))
            implementation(project(":core:network"))

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
            implementation(libs.bundles.ktor)

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
        }

    }
}

android {
    namespace = "com.example.notes_screens_impl"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}