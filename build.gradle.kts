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
    maven {
      authentication {
        create<BasicAuthentication>("basic")
      }
      url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
      credentials {
        username = "mapbox"
        password = if (project.hasProperty("SDK_REGISTRY_TOKEN")) {
          project.property("SDK_REGISTRY_TOKEN") as String
        } else {
          System.getenv("SDK_REGISTRY_TOKEN")
        } ?: throw IllegalArgumentException("SDK Registry key is not specified")
      }
    }
  }
}