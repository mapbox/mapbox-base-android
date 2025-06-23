plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka")
}

dependencies {
  implementation(baseLibs.kotlin)

  /**
   * Required for @Keep annotation by the annotation-processor and the resulting generated code
   */
  api(baseLibs.annotations)
}

project.apply {
  from("$projectDir/../gradle/ktlint.gradle")
  from("$projectDir/../gradle/java-artifacts.gradle")
  from("$projectDir/../gradle/sdk-registry-publish.gradle")
}
