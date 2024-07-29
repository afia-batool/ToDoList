plugins {
    id("com.android.application")
    kotlin("android") // Apply the Kotlin Android plugin
}

android {
    namespace = "com.example.todolist" // Update this if necessary
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todolist" // Update this if necessary
        minSdk = 21
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material.v190)
    implementation(libs.activity.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview.v130) // Add this line for RecyclerView
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)
    implementation(libs.core.ktx.v1120)
}