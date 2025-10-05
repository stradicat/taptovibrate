/*
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import java.io.FileInputStream
import java.util.Properties
*/

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "dev.dmayr.taptovibrate"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.dmayr.taptovibrate"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        signingConfigs {
/*
            create("release") {
                val propsFile = rootProject.file("keystore.properties")
                if (propsFile.exists()) {
                    val props = Properties()
                    props.load(FileInputStream(propsFile))

                    storeFile = file(
                        props["STORE_FILE"] as? String
                            ?: error("Missing 'storeFile' in keystore.properties")
                    )
                    storePassword = props["STORE_PASSWORD"] as? String
                        ?: error("Missing 'storePassword' in keystore.properties")
                    keyAlias = props["KEY_ALIAS"] as? String
                        ?: error("Missing 'keyAlias' in keystore.properties")
                    keyPassword = props["KEY_PASSWORD"] as? String
                        ?: error("Missing 'keyPassword' in keystore.properties")
                } else {
                    throw GradleException("keystore.properties not found! Cannot sign release build.")
                }
            }
*/
        }
        signingConfig = signingConfigs.getByName("debug")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.timber)
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.material3)

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
