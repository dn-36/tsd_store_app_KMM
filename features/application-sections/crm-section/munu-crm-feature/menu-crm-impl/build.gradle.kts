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
            implementation(project( ":features:application-sections:crm-section:munu-crm-feature:menu-crm-api"))
            implementation(project(":features:application-sections:crm-section:notes-feature:notes-screens:notes-screens-api"))
            implementation(project(":features:application-sections:crm-section:contragents-feature:contragents-api"))
            implementation(project(":features:application-sections:crm-section:crm-feature:crm-api"))
            implementation(project(":features:application-sections:crm-section:project-conterol-feature:project-control-api"))
            implementation(project(":features:application-sections:crm-section:specifications-feature:specifications-api"))
            implementation(project(":features:application-sections:crm-section:locations-feature:locations-api"))
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
            implementation(libs.cafe.adriel.voyager.voyager.transitions)        }

    }
}

android {
    namespace = "com.project.menu"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
