import org.gradle.kotlin.dsl.`kotlin-dsl`

  repositories {
    jcenter()
    google()
  }

plugins {
  `kotlin-dsl`
}

dependencies {
  implementation("com.android.tools.build:gradle:4.0.1")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
  implementation(gradleApi())
  implementation(localGroovy())
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}
