plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
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

  compileOnly(project(":annotations"))
  kapt(project(":annotations-processor"))
  implementation(project(":common"))
  implementation(Dependencies.navigationBase)

  testImplementation(Dependencies.mockk)
  testImplementation(Dependencies.junit)
  testImplementation(project(":annotations"))
}

project.apply {
  from("$rootDir/gradle/ktlint.gradle")
  from("$rootDir/gradle/lint.gradle")
}