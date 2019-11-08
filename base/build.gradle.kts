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
  }
}

dependencies {
  implementation(Dependencies.kotlin)
  implementation(Dependencies.annotations)
}

project.apply {
  from("$rootDir/gradle/ktlint.gradle")
  from("$rootDir/gradle/lint.gradle")
}