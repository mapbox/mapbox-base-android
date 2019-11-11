plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka-android")
}

dependencies {
  implementation(Dependencies.kotlin)

  /**
   * Required for @Keep annotation by the annotation-processor and the resulting generated code
   */
  api(Dependencies.annotations)
}

project.apply {
  from("$rootDir/gradle/ktlint.gradle")
  from("$rootDir/gradle/gradle-bintray.gradle")
}
