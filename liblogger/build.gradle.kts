plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("com.jaredsburrows.license")
  id("org.jetbrains.dokka")
}

android {
  compileSdkVersion(baseLibs.versions.compileSdkVersion.get().toInt())

  defaultConfig {
    minSdkVersion(baseLibs.versions.minSdkVersion.get().toInt())
    targetSdkVersion(baseLibs.versions.targetSdkVersion.get().toInt())
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

dependencies {
  compileOnly(project(":annotations"))
  kapt(project(":annotations-processor"))
  implementation(project(":common"))
  implementation(baseLibs.kotlin)

  /**
   * Required for @Keep annotation by the annotation-processor and the resulting generated code
   */
  implementation(baseLibs.annotations)

  testImplementation(baseLibs.junit)
  testImplementation(baseLibs.mockk)
}

project.apply {
  from("$projectDir/../gradle/ktlint.gradle")
  from("$projectDir/../gradle/lint.gradle")
  from("$projectDir/../gradle/android-artifacts.gradle")
  from("$projectDir/../gradle/sdk-registry-publish.gradle")
}