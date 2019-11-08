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
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}