object AndroidVersions {
  const val minSdkVersion = 19
  const val targetSdkVersion = 28
  const val compileSdkVersion = 28
}

object Plugins {
  const val android = "com.android.tools.build:gradle:${Versions.tools}"
  const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
  const val license = "com.jaredsburrows:gradle-license-plugin:${Versions.license}"
  const val dokka = "org.jetbrains.dokka:dokka-android-gradle-plugin:${Versions.dokka}"
}

object Dependencies {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
  const val annotations = "androidx.annotation:annotation:${Versions.androidX}"
  const val kotlinPoet = "com.squareup:kotlinpoet:${Versions.kotlinPoet}"
  const val serviceProvider = "com.google.auto.service:auto-service:${Versions.serviceProvider}"
  const val soloader = "com.facebook.soloader:soloader:${Versions.soloader}"
}

private object Versions {
  const val tools = "3.5.0"
  const val kotlin = "1.3.41"
  const val androidX = "1.1.0"
  const val license = "0.8.5"
  const val kotlinPoet = "1.3.0"
  const val serviceProvider = "1.0-rc6"
  const val dokka =  "0.9.18"
  const val soloader = "0.8.0"
}