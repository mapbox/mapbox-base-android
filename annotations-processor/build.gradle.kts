plugins {
  kotlin("jvm")
  kotlin("kapt")
  id("org.jetbrains.dokka")
}

dependencies {
  implementation(project(":annotations"))
  implementation(baseLibs.kotlin)
  implementation(baseLibs.kotlinPoet)
  implementation(baseLibs.serviceProvider)
  kapt(baseLibs.serviceProvider)
  compileOnly(baseLibs.incapRuntime)
  kapt(baseLibs.incapProcessor)
}

project.apply {
  from("$projectDir/../gradle/ktlint.gradle")
  from("$projectDir/../gradle/java-artifacts.gradle")
  from("$projectDir/../gradle/sdk-registry-publish.gradle")
}
