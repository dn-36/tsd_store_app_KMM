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
            implementation("io.ktor:ktor-network:3.0.0")
            implementation("io.ktor:ktor-client-core:3.0.0")
            implementation("io.ktor:ktor-client-cio:3.0.0")
       //     implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            implementation(project(":core:app"))
            implementation(project(":core:recources"))
            implementation(project(":core:local-storage"))
            implementation(project(":core:network"))
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
           implementation("org.videolan.android:libvlc-all:3.1.12")
            implementation ("com.google.code.gson:gson:2.11.0")
            implementation ("com.github.mwiede:jsch:0.2.19")
          //  implementation ("com.arthenica:ffmpeg-kit-full:5.1")
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
