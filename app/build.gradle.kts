plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.kotlinAndroidKsp)
    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "com.example.starterproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.starterproject"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger
    implementation(libs.google.dagger.hilt)
    ksp(libs.google.dagger.hilt.compiler)
    implementation(libs.timber)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)?.because("Kotlin Extensions and Coroutines support for Room")

    // Compose navigation
    implementation(libs.androidx.hilt.navigation.compose)

    // Compose
    implementation (libs.androidx.foundation)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Accompanist
    implementation(libs.accompanist.systemuicontroller)?.because("To toggle the color of status bar when showing the splash screen")

    //Retrofit and Gson
    implementation (libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.gson.v290)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")


    //Splash Api
    implementation (libs.androidx.core.splashscreen)

    //Datastore
    implementation (libs.androidx.datastore.preferences)

    // News API
    implementation (libs.news.api.java)

    //Coil
    implementation(libs.coil.compose)

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")?.because("Required for Navigation")

}