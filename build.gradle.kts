buildscript {
  repositories {
    google()
    mavenCentral()

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

  dependencies {
    fun applyClasspath(provider: Provider<PluginDependency>) {
      val dependency = provider.get()
      classpath("${dependency.pluginId}:${dependency.version}")
    }

    applyClasspath(baseLibs.plugins.android)
    applyClasspath(baseLibs.plugins.kotlin)
    applyClasspath(baseLibs.plugins.license)
    applyClasspath(baseLibs.plugins.dokka)
    applyClasspath(baseLibs.plugins.sdkRegistry)
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()

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