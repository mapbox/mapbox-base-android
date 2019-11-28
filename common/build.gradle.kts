plugins {
  kotlin("jvm")
  id("com.jaredsburrows.license")
  id("org.jetbrains.dokka-android")
}

dependencies {
  implementation(Dependencies.kotlin)
}

project.apply {
  from("$rootDir/gradle/ktlint.gradle")
  from("$rootDir/gradle/java-artifacts.gradle")
  from("$rootDir/gradle/bintray-publish.gradle")
}