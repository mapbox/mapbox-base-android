import com.jfrog.bintray.gradle.BintrayExtension

apply(plugin = "digital.wup.android-maven-publish")
apply(plugin = "maven-publish")
apply(plugin = "com.jfrog.bintray")
apply(plugin = "com.jfrog.artifactory")

val isAndroid: String by project
val mapboxArtifactId: String by project
val artifactVersion: String by project
val artifactTitle: String by project
val artifactDescription: String by project
val bintrayUser: String by project
val bintrayApiKey: String by project
val gpgPassphrase: String by project

configure<PublishingExtension> {
  publications {
    register("MapboxBasePublication", MavenPublication::class) {
      if (isAndroid.toBoolean()) {
        from(components["android"])
      } else {
        from(components["java"])
      }

      groupId = ArtifactSettings.artifactGroupId
      artifactId = mapboxArtifactId
      version = artifactVersion

      afterEvaluate {
        artifact(sourcesJar.get())
        artifact(javadocsJar.get())
      }

      pom.withXml {
        val mainNode = asNode()
        mainNode.appendNode("name", artifactTitle)
        mainNode.appendNode("description", artifactTitle)
        mainNode.appendNode("url", ArtifactSettings.artifactUrl)

        val licenseNode = mainNode.appendNode("licenses").appendNode("license")
        licenseNode.appendNode("name", ArtifactSettings.artifactLicenseName)
        licenseNode.appendNode("url", ArtifactSettings.artifactLicenseUrl)
        licenseNode.appendNode("distribution", "repo")

        val developerNode = mainNode.appendNode("developers").appendNode("developer")
        developerNode.appendNode("id", ArtifactSettings.developerId)
        developerNode.appendNode("name", ArtifactSettings.developerName)

        val scmNode = mainNode.appendNode("scm")
        scmNode.appendNode("connection", ArtifactSettings.artifactScmUrl)
        scmNode.appendNode("developerConnection", ArtifactSettings.artifactScmUrl)
        scmNode.appendNode("url", ArtifactSettings.artifactUrl)
      }
    }
  }
}

configure<BintrayExtension> {
  user = bintrayUser
  key = bintrayApiKey
  setPublications("MapboxBasePublication")
  publish = false

  val pkgConfig = PackageConfig()
  pkgConfig.repo = ArtifactSettings.bintrayRepoName
  pkgConfig.name = mapboxArtifactId
  pkgConfig.userOrg = ArtifactSettings.bintrayUserOrg
  pkgConfig.setLicenses(ArtifactSettings.artifactLicenseName)
  pkgConfig.vcsUrl = ArtifactSettings.artifactVcsUrl

  val pkgVersion = VersionConfig()
  pkgVersion.name = artifactVersion
  pkgVersion.desc = artifactDescription
  pkgVersion.released = java.util.Date()

  val gpgConfig = GpgConfig()
  gpgConfig.sign = true
  gpgConfig.passphrase = gpgPassphrase
  pkgVersion.gpg = gpgConfig

  val mavenCentralSync = MavenCentralSyncConfig()
  mavenCentralSync.sync = false
  pkgVersion.mavenCentralSync = mavenCentralSync

  pkgConfig.version = pkgVersion
}

val javadocs by tasks.registering(Javadoc::class) {
  source = project.the<SourceSetContainer>()["main"].allSource
  isFailOnError = false
}

val javadocsJar by tasks.registering(Jar::class) {
  dependsOn.add(javadocs)
  archiveClassifier.set("javadoc")
  from(javadocs)
}

val sourcesJar by tasks.registering(Jar::class) {
  archiveClassifier.set("sources")
  from(project.the<SourceSetContainer>()["main"].allSource)
}

tasks.withType(Javadoc::class) {
  options.encoding = "UTF-8"
}

/*
publishing {
    publications {
        MapboxMapsSdkPublication(MavenPublication) {
            from components.android
            groupId this.group
            mapboxArtifactId project.ext.mapboxArtifactId
            artifactVersion this.artifactVersion

            afterEvaluate {
                artifact(androidSourcesJar)
                artifact(androidJavadocsJar)
            }

            pom.withXml {
                final mainNode = asNode()
                mainNode.appendNode("name", project.ext.mapboxArtifactTitle)
                mainNode.appendNode("description", project.ext.mapboxArtifactTitle)
                mainNode.appendNode("url", project.ext.mapboxArtifactUrl)

                final licenseNode = mainNode.appendNode("licenses").appendNode("license")
                licenseNode.appendNode("name", project.ext.mapboxArtifactLicenseName)
                licenseNode.appendNode("url", project.ext.mapboxArtifactLicenseUrl)
                licenseNode.appendNode("distribution", "repo")

                final developerNode = mainNode.appendNode("developers").appendNode("developer")
                developerNode.appendNode("id", project.ext.mapboxDeveloperId)
                developerNode.appendNode("name", project.ext.mapboxDeveloperName)

                final scmNode = mainNode.appendNode("scm")
                scmNode.appendNode("connection", project.ext.mapboxArtifactScmUrl)
                scmNode.appendNode("developerConnection", project.ext.mapboxArtifactScmUrl)
                scmNode.appendNode("url", project.ext.mapboxArtifactUrl)
            }
        }
    }
}

bintray {
    user = mapboxBintrayUser
    key = mapboxBintrayApiKey
    publications("MapboxMapsSdkPublication")
    pkg {
        repo = project.ext.mapboxBintrayRepoName
        name = project.ext.mapboxBintrayPackageName
        userOrg = project.ext.mapboxBintrayUserOrg
        licenses = [project.ext.mapboxArtifactLicenseName]
        vcsUrl = project.ext.mapboxArtifactVcsUrl
        publish = false
        artifactVersion {
            name = project.ext.versionName
            desc = project.ext.mapboxArtifactDescription
            released = new Date()
            gpg {
                sign = true
                passphrase = mapboxGpgPassphrase
            }
            mavenCentralSync {
                sync = false
            }
        }
    }
}

artifactory {
    contextUrl = "http://oss.jfrog.org"
    publish {
        repository {
            repoKey = "oss-snapshot-local"
            username = mapboxBintrayUser
            password = mapboxBintrayApiKey
        }
        defaults {
            publications("MapboxMapsSdkPublication")
        }
    }
}

task androidJavadocs(type: Javadoc) {
    source = android.sourceSets.main.java.sourceFiles
    classpath = files(android.bootClasspath)
    failOnError = false
}

task androidJavadocsJar(type: Jar, dependsOn: androidJavadocs) {
    classifier = "javadoc"
    from androidJavadocs.destinationDir
}

task androidSourcesJar(type: Jar) {
    classifier = "sources"
    from android.sourceSets.main.java.sourceFiles
}

tasks.withType(Javadoc) {
    options.addStringOption("encoding", "UTF-8")
    options.addStringOption("docencoding", "UTF-8")
    options.addStringOption("charset", "UTF-8")
}

afterEvaluate { project ->
    android.libraryVariants.all { variant ->
        tasks.androidJavadocs.doFirst {
            classpath += files(variant.javaCompile.classpath.files)
        }
    }
}
 */