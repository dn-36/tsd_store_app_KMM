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
            baseName = "core"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(project(":features:application-sections:profile-section:ip-camera-feature:ip-camera-api"))
            implementation(project(":features:application-sections:profile-section:printer-feature:printer-api"))
            implementation(project(":features:authorization-feature:authorization-screen-api"))
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            implementation(project(":core:recources"))
            implementation(project(":core:local-storage"))
            implementation(project(":core:network"))
            implementation(dependencyNotation = libs.peekaboo.ui)
            implementation(dependencyNotation = libs.peekaboo.imagepicker)
            implementation("com.squareup.okio:okio:3.9.1")
            implementation(libs.kotlinx.io)
            implementation("io.ktor:ktor-utils:2.0.0")
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.cafe.adriel.voyager.voyager.navigator)
            implementation(libs.cafe.adriel.voyager.voyager.transitions)
            implementation(libs.bundles.ktor)
            implementation(libs.koin.core)
            implementation("network.chaintech:qr-kit:2.0.0")
            implementation(dependencyNotation = libs.peekaboo.ui)
            implementation(dependencyNotation = libs.peekaboo.imagepicker)
        }

    }
}

android {
    namespace = "com.project.core"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    dependencies {
        implementation(libs.koin.android)
    }
}

