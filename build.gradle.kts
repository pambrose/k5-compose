import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev716"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.jetbrains.dokka") version "1.6.21"
    id("com.github.ben-manes.versions") version "0.42.0"
}

group = "me.nikhilchaudhari"
version = "1.0.0"

repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(project("k5-compose"))
    // implementation("me.nikhilchaudhari:k5-compose:1.0.0-alpha01")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.6.21")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs =
        listOf(
            "-opt-in=kotlin.RequiresOptIn",
        )
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "k5-compose-examples"
            packageVersion = "1.0.0"
        }
    }
}
