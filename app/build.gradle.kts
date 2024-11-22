import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.Platform
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import java.net.URL

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.dokka")
}

android {
    namespace = "com.ruskaroma"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ruskaroma"
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "**/androidTest/**"
            excludes += "**/test/**"
            excludes += "**/debug/**"
            excludes += "**/release/**"

        }
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.dokka.gradle.plugin)
        classpath(libs.dokka.base)
    }
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set(project.name)
    moduleVersion.set(project.version.toString())
    outputDirectory.set(layout.buildDirectory.dir("dokka/$name"))
    failOnWarning.set(false)
    suppressObviousFunctions.set(true)
    suppressInheritedMembers.set(false)
    offlineMode.set(false)

    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "(c) 2024 DaniAndries"
        separateInheritedMembers = false
        mergeImplicitExpectActualDeclarations = false
    }
    /**
     *
     *     dokkaSourceSets {
     *         configureEach {
     *             suppress.set(false)
     *             perPackageOption {
     *                 matchingRegex.set(".*androidTest.*|.*test.*|.*debug.*|.*release.*") // will match all .internal packages and sub-packages
     *                 suppress.set(true)
     *             }
     *             displayName.set(name)
     *             documentedVisibilities.set(setOf(Visibility.PUBLIC))
     *             reportUndocumented.set(false)
     *             skipEmptyPackages.set(true)
     *             skipDeprecated.set(false)
     *             suppressGeneratedFiles.set(true)
     *             jdkVersion.set(8)
     *             languageVersion.set("1.8")
     *             apiVersion.set("1.8")
     *             noStdlibLink.set(false)
     *             noJdkLink.set(false)
     *             noAndroidSdkLink.set(false)
     *             includes.from(project.files(), "MODULE.md")
     *             platform.set(Platform.DEFAULT)
     *             sourceRoots.from(file("src"))
     *             classpath.from(project.files(), file("libs/dependency.jar"))
     *             samples.from(project.files(), "samples/Basic.kt", "samples/Advanced.kt")
     *         }
     *
     *     }
     */
}






dependencies {
    // Is applied universally
    dokkaPlugin(libs.mathjax.plugin)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.material.icons.extended)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
