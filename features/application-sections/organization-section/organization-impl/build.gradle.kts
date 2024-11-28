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
            baseName = "home"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:app"))
            implementation(project(":core:network"))
            implementation(project(":core:recources"))
            implementation(project(":features:application-sections:organization-section:organization-api"))
            implementation(project(":features:application-sections:chat-section:chats-api"))
            implementation(project(":features:application-sections:crm-section:munu-crm-feature:menu-crm-api"))
            implementation(project(":features:application-sections:tape-section:tape-api"))
            implementation(project(":features:application-sections:profile-section:profile-feature:profile-api"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.koin.core)
            implementation(libs.sdp.ssp)
            implementation(libs.cafe.adriel.voyager.voyager.navigator)
            implementation(libs.cafe.adriel.voyager.voyager.transitions)
            implementation(libs.bundles.ktor)

            implementation(dependencyNotation = libs.peekaboo.ui)
            implementation(dependencyNotation = libs.peekaboo.imagepicker)
            implementation("com.github.skydoves:landscapist-coil3:2.4.1")
        }

    }
}

android {
    namespace = "com.project.home"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
