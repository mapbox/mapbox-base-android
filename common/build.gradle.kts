plugins {
  id("com.android.library")
  kotlin("android")
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

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions{
    jvmTarget = JavaVersion.VERSION_1_8.toString()
    freeCompilerArgs = listOf("-module-name", "common-android")
  }
}

dependencies {
  implementation(baseLibs.kotlin)

  implementation(project(":annotations"))

  /**
   * Required for @Keep annotation by the annotation-processor and the resulting generated code
   */
  implementation(baseLibs.annotations)
}

project.apply {
  from("$projectDir/../gradle/ktlint.gradle")
  from("$projectDir/../gradle/lint.gradle")
  from("$projectDir/../gradle/android-artifacts.gradle")
  from("$projectDir/../gradle/sdk-registry-publish.gradle")
}