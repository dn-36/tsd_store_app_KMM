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
            baseName = "menu"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:app"))
            implementation(project(":core:recources"))
            implementation(project(":core:network"))
            implementation(project(":core:local-storage"))
            implementation(project(":common:video-player"))
            implementation(project( ":features:application-sections:profile-section:goods-and-services-feature:goods-and-services-api"))
            implementation(project( ":features:application-sections:profile-section:products-menu-feature:products-menu-api"))
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

            implementation(dependencyNotation = libs.peekaboo.ui)
            implementation(dependencyNotation = libs.peekaboo.imagepicker)

        }
    }
}

android {
    namespace = "com.project.menu"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}