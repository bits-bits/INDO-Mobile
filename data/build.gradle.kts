import org.gradle.kotlin.dsl.projects
import java.io.FileInputStream
import java.util.Properties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    kotlin("plugin.serialization") version "1.9.20"
    id("com.codingfeline.buildkonfig")
}

val secretsFile = rootProject.file("secrets.properties")
val secrets = Properties().apply {
    if (secretsFile.exists()) {
        load(FileInputStream(secretsFile))
    }
}

println("=== BuildKonfig Debug ===")
println("Secrets file exists: ${secretsFile.exists()}")
println("Secrets file path: ${secretsFile.absolutePath}")
println("USERNAME: ${secrets.getProperty("USERNAME", "NOT_FOUND")}")
println("PASSWORD: ${secrets.getProperty("PASSWORD", "NOT_FOUND")}")
println("HOST: ${secrets.getProperty("HOST", "NOT_FOUND")}")
println("PORT: ${secrets.getProperty("PORT", "NOT_FOUND")}")
println("========================")

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.bitsandbits.data"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "dataKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
                implementation(projects.domain)

                // Ktor
                api(libs.ktor.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)

                implementation(libs.ktor.client.auth)

                implementation(libs.kotlinx.coroutines.core)

            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                // Ktor
                api(libs.ktor.client.okhttp)
                implementation(libs.play.services.location)
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.testExt.junit)
            }
        }

        iosMain {
            dependencies {
                // ktor
                api(libs.ktor.client.darwin)
            }
        }
    }

}


buildkonfig {
    packageName = "com.bitsandbits.config"

    defaultConfigs {
        buildConfigField(Type.STRING, "USERNAME", secrets.getProperty("USERNAME", ""))
        buildConfigField(Type.STRING, "PASSWORD", secrets.getProperty("PASSWORD", ""))
        buildConfigField(Type.STRING, "HOST", secrets.getProperty("HOST", "localhost"))
        buildConfigField(Type.INT, "PORT", secrets.getProperty("PORT", "8080"))
    }
}