plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    jacoco
    kotlin("jvm")
}

group = "ru.ssau.tk.nikitals.oop"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

jacoco {
    toolVersion = "0.8.12"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-compress:1.27.1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.thoughtworks.xstream:xstream:1.4.20")

    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    testImplementation("org.springframework.security:spring-security-test")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:postgresql")
    implementation("com.h2database:h2")
    testImplementation("com.h2database:h2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // https://mvnrepository.com/artifact/org.reflections/reflections
    implementation("org.reflections:reflections:0.10.2")

    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok-mapstruct-binding
    implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")


    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {

    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    classDirectories.setFrom(files(classDirectories.files.map {
        fileTree(it) {
            exclude(
                "ru/ssau/tk/nikitals/oop/**/*Demo.*",
                "ru/ssau/tk/nikitals/oop/core/ports/io/demo/*",
            )
        }
    }))
    reports {
        xml.required = true
        csv.required = true
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}
allprojects{
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}