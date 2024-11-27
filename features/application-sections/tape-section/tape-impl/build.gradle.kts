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

        androidMain.dependencies {

            //implementation(compose.preview)

            implementation(libs.androidx.activity.compose)


        }

        commonMain.dependencies {
            implementation(project(":core:app"))
            implementation(project(":common:video-player"))
            implementation(project(":common:file-provider"))
            implementation(project(":core:recources"))
            implementation(project(":core:network"))
            implementation(project(":core:local-storage"))
            implementation(project(":features:application-sections:tape-section:tape-api"))

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

            //implementation(libs.image.loader)

            //implementation(libs.media.player)

            //implementation( "org.robolectric:robolectric:4.0.2")
        }

        nativeMain.dependencies {

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
