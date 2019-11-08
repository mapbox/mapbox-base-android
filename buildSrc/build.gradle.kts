import org.gradle.kotlin.dsl.`kotlin-dsl`

  repositories {
    jcenter()
    google()
  }

plugins {
  `kotlin-dsl`
}

dependencies {
  implementation("com.android.tools.build:gradle:3.5.1")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.41")
  implementation(gradleApi())
  implementation(localGroovy())
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}
