object AndroidVersions {
  const val minSdkVersion = 19
  const val targetSdkVersion = 29
  const val compileSdkVersion = 29
}

object Plugins {
  const val android = "com.android.tools.build:gradle:${Versions.tools}"
  const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
  const val license = "com.jaredsburrows:gradle-license-plugin:${Versions.license}"
  const val dokka = "org.jetbrains.dokka:dokka-android-gradle-plugin:${Versions.dokka}"
  const val bintray = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}"
  const val androidPublish = "digital.wup:android-maven-publish:${Versions.androidPublish}"
  const val artifactory = "org.jfrog.buildinfo:build-info-extractor-gradle:${Versions.artifactory}"
}

object Dependencies {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
  const val annotations = "androidx.annotation:annotation:${Versions.androidX}"
  const val kotlinPoet = "com.squareup:kotlinpoet:${Versions.kotlinPoet}"
  const val serviceProvider = "com.google.auto.service:auto-service:${Versions.serviceProvider}"
  const val mockk = "io.mockk:mockk:${Versions.mockk}"
  const val junit = "androidx.test.ext:junit:${Versions.junit}"
  const val navigationBase = "com.mapbox.navigation:base:${Versions.navigationBase}"
}

private object Versions {
  const val tools = "4.0.1"
  const val kotlin = "1.4.10"
  const val androidX = "1.1.0"
  const val license = "0.8.5"
  const val kotlinPoet = "1.6.0"
  const val serviceProvider = "1.0-rc7"
  const val dokka = "0.9.18"
  const val bintray = "1.8.4"
  const val androidPublish = "3.6.2"
  const val artifactory = "4.17.2"
  const val mockk = "1.10.0"
  const val junit = "1.1.2"
  const val navigationBase = "1.0.0-rc.6"
}

object ArtifactSettings {
  const val artifactGroupId = "com.mapbox.base"
  const val developerName = "Mapbox"
  const val developerId = "mapbox"
  const val artifactUrl = "https://github.com/mapbox/mapbox-base-android"
  const val artifactVcsUrl = "https://github.com/mapbox/mapbox-base-android.git"
  const val artifactScmUrl = "scm:git@github.com:mapbox/mapbox-base-android.git"
  const val artifactLicenseName = "BSD"
  const val artifactLicenseUrl = "https://opensource.org/licenses/BSD-2-Clause"

  const val bintrayUserOrg = "mapbox"
  const val bintrayRepoName = "mapbox"
}
