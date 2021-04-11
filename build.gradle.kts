import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.10"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id ("org.springframework.boot") version "2.3.7.RELEASE"
    id ("io.spring.dependency-management") version "1.0.8.RELEASE"
    id ("io.github.http-builder-ng.http-plugin") version "0.1.1"
    id("io.gitlab.arturbosch.detekt") version "1.14.1"
    id("jacoco")
    id ("application")
}

application {
    mainClassName = "poker.model.PokerModelKt"
}

group = "me.jwh13"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    //KOTLIN
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    //JACKSON
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
    //MATHS FUNCTIONS
    implementation ("com.marcinmoskala:DiscreteMathToolkit:1.0.3")
    //SPRING
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-mustache")
    runtimeOnly ("com.h2database:h2")
    runtimeOnly ("org.springframework.boot:spring-boot-devtools")
    //SWAGGER
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    //TESTING
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
    testImplementation("io.mockk:mockk:1.10.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage")
    }
}

detekt {
    toolVersion = "1.14.1"
    input = files("src/main")
    buildUponDefaultConfig = true
    config = files("${project.rootDir.path}/config/detekt-overrides.yml")
}

tasks {
    withType<Test> {
        useJUnitPlatform()

        testLogging {
            events = mutableSetOf(
                org.gradle.api.tasks.testing.logging.TestLogEvent.STARTED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
            )
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }

        finalizedBy("jacocoTestReport")
    }

    withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xuse-experimental=kotlin.contracts.ExperimentalContracts")
        }
    }
    distTar {
        enabled = false
    }

    distZip {
        enabled = false
    }

    bootDistZip {
        enabled = false
    }
}
