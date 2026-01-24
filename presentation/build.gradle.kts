import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.cocoapods)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    cocoapods {
        summary = "Small summary about the project."
        homepage = "https://indo.com"
        version = "1.0"
        ios.deploymentTarget = "15.4"         // set as needed
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "presentation"
            isStatic = true
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "presentation"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(compose.components.resources)
            implementation(projects.designsystem)
            implementation(projects.domain)


            // serialization
            implementation(libs.kotlinx.serialization.json)

            // Navigation
            implementation(libs.androidx.navigation.compose)

            // koin
//            implementation(libs.koin.compose)
            implementation(libs.bundles.koin.compose)


            // coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)

            implementation(libs.peekaboo.ui)
            implementation(libs.peekaboo.image.picker)

            // maps
            implementation(libs.maplibre.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.bitsandbits.indo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}