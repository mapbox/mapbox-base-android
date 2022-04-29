plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
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

  kotlinOptions {
    freeCompilerArgs = listOf(
      "-Xno-optimized-callable-references"
    )
  }
}

dependencies {
  compileOnly(project(":annotations"))
  kapt(project(":annotations-processor"))
  implementation(project(":common"))
  implementation(Dependencies.kotlin)

  /**
   * Required for @Keep annotation by the annotation-processor and the resulting generated code
   */
  implementation(Dependencies.annotations)

  testImplementation(Dependencies.junit)
  testImplementation(Dependencies.mockk)
}

project.apply {
  from("$rootDir/gradle/ktlint.gradle")
  from("$rootDir/gradle/lint.gradle")
  from("$rootDir/gradle/android-artifacts.gradle")
  from("$rootDir/gradle/sdk-registry-publish.gradle")
}