import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "lihan.chen.moneymanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "lihan.chen.moneymanager"
        minSdk = 28
        targetSdk = 34
        versionCode = 7
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
        debug {

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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.6")
    //Room
    val room_version = "2.6.1"
    implementation ("androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")



    //Dagger Hilt
    val hilt_plugin_version = "2.48"
    implementation ("com.google.dagger:hilt-android:$hilt_plugin_version")
    implementation ("com.google.dagger:hilt-android-compiler:$hilt_plugin_version")
    kapt ("com.google.dagger:hilt-compiler:$hilt_plugin_version")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    annotationProcessor ("com.google.dagger:hilt-android-compiler:$hilt_plugin_version")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.android.support:multidex:1.0.2")

    //Data Store
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")

    //Chart
    // For Jetpack Compose.
    implementation("com.patrykandpatrick.vico:compose:1.13.1")
    // For `compose`. Creates a `ChartStyle` based on an M3 Material Theme.
    implementation("com.patrykandpatrick.vico:compose-m3:1.13.1")
    // Houses the core logic for charts and other elements. Included in all other modules.
    implementation("com.patrykandpatrick.vico:core:1.13.1")
    // For the view system.
    implementation("com.patrykandpatrick.vico:views:1.13.1")

    implementation ("com.opencsv:opencsv:5.2")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

}
