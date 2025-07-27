plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.gms.google-services")
    //id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"


}

android {
    namespace = "com.example.greenpulse_04"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.greenpulse_04"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/INDEX.LIST")
    }
    configurations.all {
        resolutionStrategy {
            force("com.some.library:library-name:version")
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
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("androidx.room:room-runtime:2.6.1")
    //implementation("io.ktor:ktor-server-core:2.2.4")
    //implementation("io.ktor:ktor-server-netty:2.2.4")
    implementation("org.jetbrains.exposed:exposed-core:0.38.1")
    implementation("org.postgresql:postgresql:42.3.1")
    //implementation("com.google.apis:google-api-services-calendar:v3-rev20250115-2.0.0")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("io.ktor:ktor-server-core:2.2.4")
    //implementation("io.ktor:ktor-server-netty:2.2.4") // or another engine you're using
    implementation("io.ktor:ktor-server-auth:2.2.4")  // if using authentication
    implementation("io.ktor:ktor-server-content-negotiation:2.2.4") // if using content negotiation
    implementation("io.ktor:ktor-serialization:2.2.4") // for JSON handling, if needed
    implementation("io.ktor:ktor-client-android:2.2.4") // Use the Ktor client for Android
    implementation("io.ktor:ktor-client-core:2.2.4")
    implementation("io.ktor:ktor-client-json:2.2.4")
    implementation("io.ktor:ktor-client-serialization:2.2.4")
    implementation("io.ktor:ktor-client-logging:2.2.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.2.4")
    //implementation("io.ktor:ktor-client-netty:2.2.4")
    implementation("com.google.api-client:google-api-client:1.34.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
    implementation("com.google.apis:google-api-services-calendar:v3-rev20250115-2.0.0")
    implementation("com.google.api-client:google-api-client-android:1.33.0")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.30.0")
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.android.gms:play-services-measurement-api:20.0.2")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.http-client:google-http-client-android:1.43.3") // HTTP transport
    implementation("com.google.http-client:google-http-client-gson:1.43.3")
    implementation("com.google.firebase:firebase-auth-ktx:21.0.1")
    //implementation("androidx.room:room-runtime:2.5.0")
    //implementation("androidx.room:room-runtime:2.5.0")
    //annotationProcessor("androidx.room:room-compiler:2.5.0") // or use kapt if you're using Kotlin
    // If using Kotlin KAPT:
    //kapt("androidx.room:room-compiler:2.5.0")

    // Optional: Room Paging (if you need Paging support)
    implementation("androidx.room:room-paging:2.5.0")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("androidx.compose.ui:ui:1.6.0") // Check for the latest version
    implementation("androidx.compose.material:material:1.6.0") // For material components
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation("androidx.compose.runtime:runtime:1.6.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("com.google.api-client:google-api-client:1.34.0")
    implementation("com.google.http-client:google-http-client-android:1.41.0")
    implementation("com.google.oauth-client:google-oauth-client:1.34.0")
    implementation("com.google.api-client:google-api-client-android:2.2.0")
    //implementation("com.google.api-client:google-api-client-jackson2:2.2.0")
    implementation("com.google.code.gson:gson:2.8.8") // Gson dependency
    implementation("com.google.api-client:google-api-client-android:2.2.0") // Google API client for Android
    implementation("com.google.api-client:google-api-client:2.2.0") // Google API client
    implementation("com.google.firebase:firebase-auth-ktx:21.0.1")

    // Jackson for JSON parsing
    /*implementation("com.fasterxml.jackson.core:jackson-core:2.13.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")*/

    // Required for Kotlin Coroutines
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("org.tensorflow:tensorflow-lite:2.9.0")
    implementation("com.google.api-client:google-api-client-android:2.2.0")





    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

}