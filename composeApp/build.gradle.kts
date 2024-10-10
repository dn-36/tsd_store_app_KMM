import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)

}

kotlin {
    androidTarget {

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
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
            implementation(project(":core:app"))
            implementation(project(":core:local-storage"))
            implementation(project(":features:outhorization-feature:outhorization-screen-api"))
            implementation(project(":features:application-sections:organization-section:organization-api"))
            implementation(project(":features:application-sections:chat-section:chats-api"))
            implementation(project(":features:application-sections:chat-section:chats-impl"))
            implementation(project(":features:application-sections:organization-section:organization-api"))
            implementation(project(":features:application-sections:organization-section:organization-impl"))
            implementation(project(":features:application-sections:crm-section:munu-crm-feature:menu-crm-impl"))
            implementation(project(":features:application-sections:crm-section:munu-crm-feature:menu-crm-api"))
            implementation(project(":features:application-sections:tape-section:tape-impl"))
            implementation(project(":features:application-sections:profile-section:profile-feature:profile-api"))
            implementation(project(":features:application-sections:profile-section:profile-feature:profile-impl"))
            implementation(project(":features:outhorization-feature:outhorization-screen-impl"))
            implementation(project(":features:application-sections:profile-section:printer-feature:printer-impl"))
            implementation(project(":features:application-sections:profile-section:warehouse-feature:warehouse-api"))
            implementation(project(":features:application-sections:profile-section:warehouse-feature:warehouse-impl"))
            implementation(project(":core:network"))
            api(libs.koin.core)
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
            implementation(libs.kotlinx.datetime)
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

