buildscript {
  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath(Plugins.android)
    classpath(Plugins.kotlin)
    classpath(Plugins.license)
    classpath(Plugins.dokka)
    classpath(Plugins.androidPublish)
    classpath(Plugins.bintray)
    classpath(Plugins.artifactory)
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}