plugins {
  kotlin("jvm")
  kotlin("kapt")
}

dependencies {
  implementation(project(":annotations"))
  implementation(Dependencies.kotlin)
  implementation(Dependencies.kotlinPoet)
  implementation(Dependencies.serviceProvider)
  kapt(Dependencies.serviceProvider)
}

project.apply {
  from("$rootDir/gradle/ktlint.gradle")
  from("$rootDir/gradle/java-artifacts.gradle")
  from("$rootDir/gradle/bintray-publish.gradle")
}
