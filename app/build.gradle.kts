plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    kotlin("android")
    alias(libs.plugins.kotlin.serialization)
    jacoco
}

android {
    compileSdk = 36
    defaultConfig {
        applicationId = "dev.kutluca.koinscopeissue"
        minSdk = 26
        targetSdk = 35
        versionCode = System.getenv("CI_PIPELINE_IID")?.toInt() ?: 99999
        versionName = "1.0.$versionCode"
        // Don't attempt to crunch PNGs since there should only be vector assets of webp images
        aaptOptions.cruncherEnabled = false
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packagingOptions.resources {
        excludes += "META-INF/**"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs =
            listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlinx.coroutines.DelicateCoroutinesApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=androidx.compose.ui.text.ExperimentalTextApi",
                "-opt-in=kotlinx.serialization.InternalSerializationApi",
            )
        allWarningsAsErrors = true
    }
    testOptions.unitTests {
        all {
            // Configure JVM memory settings for unit tests to prevent OutOfMemoryError
            it.jvmArgs("-Xmx2g", "-XX:+UseG1GC", "-XX:+HeapDumpOnOutOfMemoryError")
        }
        isIncludeAndroidResources = true
    }
    testOptions.execution = "ANDROIDX_TEST_ORCHESTRATOR"

    namespace = "dev.kutluca.koinscopeissue"
}

dependencies {
    implementation(libs.kotlin.reflect)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.exif)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons)
    debugImplementation(libs.androidx.compose.test.manifest)
    debugImplementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.startup.runtime)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)
    implementation(libs.koin.navigation.extension)

    // Test
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.androidx.test.truth)
    testImplementation(libs.androidx.test.runner)
    testImplementation(libs.androidx.test.architecture)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.compose.ui.test.junit)
    testImplementation(libs.androidx.test.espresso)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestUtil(libs.androidx.test.orchestrator)
    androidTestImplementation(libs.androidx.test.runner)
}
