plugins {
  id("com.android.library")
  kotlin("android")
  id("com.jaredsburrows.license")
  id("org.jetbrains.dokka-android")
}

android {
  compileSdkVersion(AndroidVersions.compileSdkVersion)

  defaultConfig {
    minSdkVersion(AndroidVersions.minSdkVersion)
    targetSdkVersion(AndroidVersions.targetSdkVersion)
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

dependencies {
  implementation(Dependencies.kotlin)

  /**
   * Required for @Keep annotation by the annotation-processor and the resulting generated code
   */
  implementation(Dependencies.annotations)
}

project.apply {
  from("$rootDir/gradle/ktlint.gradle")
  from("$rootDir/gradle/lint.gradle")
  from("$rootDir/gradle/android-artifacts.gradle")
  from("$rootDir/gradle/bintray-publish.gradle")
}