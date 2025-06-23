plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
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

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}

dependencies {
  implementation(baseLibs.kotlin)

  compileOnly(project(":annotations"))
  kapt(project(":annotations-processor"))
  implementation(project(":common"))
  implementation(baseLibs.navigationBase)

  testImplementation(baseLibs.mockk)
  testImplementation(baseLibs.junit)
  testImplementation(project(":annotations"))
}

project.apply {
  from("$projectDir/../gradle/ktlint.gradle")
  from("$projectDir/../gradle/lint.gradle")
}