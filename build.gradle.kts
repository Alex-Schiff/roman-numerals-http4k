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
  application
}

val gitVersion: Closure<String> by extra

version = gitVersion().replace("^v".toRegex(), "")

println("Build Version = $version")

kotlin { jvmToolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

kover { reports { filters { excludes { classes("dev.alexschiff.enums.*") } } } }

ktfmt { googleStyle() }

repositories { mavenCentral() }

application { mainClass = "dev.alexschiff.RomanNumeralsKt" }

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
  implementation(platform(libs.http4k))
  implementation("org.http4k:http4k-api-openapi")
  implementation("org.http4k:http4k-api-ui-swagger")
  implementation("org.http4k:http4k-client-okhttp")
  implementation("org.http4k:http4k-core")
  implementation("org.http4k:http4k-format-jackson")
  implementation("org.http4k:http4k-platform-docker")
  testImplementation("org.http4k:http4k-testing-chaos")
  testImplementation("org.http4k:http4k-testing-kotest")
  testImplementation(libs.junit.jupiter.api)
  testImplementation(libs.junit.jupiter.engine)
  testImplementation(libs.junit.platform.launcher)
}
