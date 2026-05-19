import groovy.lang.Closure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
  alias(libs.plugins.detekt)
  alias(libs.plugins.git.version)
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.kover)
  alias(libs.plugins.ktfmt)
}

buildscript {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }

  dependencies {}
}

val gitVersion: Closure<String> by extra

version = gitVersion().replace("^v".toRegex(), "")

println("Build Version = $version")

kotlin { jvmToolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

ktfmt { googleStyle() }

repositories { mavenCentral() }

tasks {
  withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
      allWarningsAsErrors.set(false)
      jvmTarget.set(JVM_21)
      freeCompilerArgs.add("-jvm-default=enable")
    }
  }

  withType<Test> { useJUnitPlatform() }

  java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }
}

dependencies {
  implementation(platform("org.http4k:http4k-bom:6.47.1.0"))
  implementation("org.http4k.pro:http4k-tools-hotreload")
  implementation("org.http4k:http4k-api-openapi")
  implementation("org.http4k:http4k-client-okhttp")
  implementation("org.http4k:http4k-core")
  implementation("org.http4k:http4k-format-jackson")
  implementation("org.http4k:http4k-format-kotlinx-serialization")
  implementation("org.http4k:http4k-serverless-core")
  implementation("org.http4k:http4k-serverless-gcf")
  testImplementation("org.http4k:http4k-testing-chaos")
  testImplementation("org.http4k:http4k-testing-kotest")
  testImplementation(libs.junit.jupiter.api)
  testImplementation(libs.junit.jupiter.engine)
  testImplementation(libs.junit.platform.launcher)
}
