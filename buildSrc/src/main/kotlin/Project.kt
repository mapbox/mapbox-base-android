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
  const val bintray = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}"
  const val androidPublish = "digital.wup:android-maven-publish:${Versions.androidPublish}"
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
  const val dokka = "0.9.18"
  const val soloader = "0.8.0"
  const val bintray = "1.8.4"
  const val androidPublish = "3.6.2"
}

object ArtifactSettings {
  const val mapboxArtifactGroupId = 'com.mapbox.base'
  const val mapboxArtifactId = if (project.hasProperty('POM_ARTIFACT_ID')) project.property('POM_ARTIFACT_ID') else System.getenv('POM_ARTIFACT_ID')
  const val mapboxArtifactTitle = if (project.hasProperty('POM_TITLE')) project.property('POM_TITLE') else System.getenv('POM_TITLE')
  const val mapboxArtifactDescription = if (project.hasProperty('POM_DESCRIPTION')) project.property('POM_DESCRIPTION') else System.getenv('POM_DESCRIPTION')
  const val mapboxDeveloperName = 'Mapbox'
  const val mapboxDeveloperId = 'mapbox'
  const val mapboxArtifactUrl = 'https://github.com/mapbox/mapbox-base-android'
  const val mapboxArtifactVcsUrl = 'https://github.com/mapbox/mapbox-base-android.git'
  const val mapboxArtifactScmUrl = 'scm:git@github.com:mapbox/mapbox-base-android.git'
  const val mapboxArtifactLicenseName = 'BSD'
  const val mapboxArtifactLicenseUrl = 'https://opensource.org/licenses/BSD-2-Clause'
  const val versionName = if (project.hasProperty('VERSION_NAME')) project.property('VERSION_NAME') else System.getenv('VERSION_NAME')

  const val mapboxBintrayUserOrg = 'mapbox'
  const val mapboxBintrayRepoName = 'mapbox'
  const val mapboxBintrayUser = if (project.hasProperty('BINTRAY_USER')) project.property('BINTRAY_USER') else System.getenv('BINTRAY_USER')
  const val mapboxBintrayApiKey = if (project.hasProperty('BINTRAY_API_KEY')) project.property('BINTRAY_API_KEY') else System.getenv('BINTRAY_API_KEY')
  const val mapboxGpgPassphrase = if (project.hasProperty('GPG_PASSPHRASE')) project.property('GPG_PASSPHRASE') else System.getenv('GPG_PASSPHRASE')
}