plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("io.freefair.lombok") version "8.13"
	checkstyle
}

group = "ru.maksimlitvinov"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	implementation("org.postgresql:postgresql:42.7.5")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.instancio:instancio-junit:3.3.0")
	implementation("net.datafaker:datafaker:2.4.2")
	implementation("org.flywaydb:flyway-core:11.4.0")
	implementation("org.flywaydb:flyway-database-postgresql:11.4.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.h2database:h2:2.3.232")

	testImplementation("org.testcontainers:junit-jupiter:1.17.3")
	testImplementation("org.testcontainers:postgresql:1.17.3")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
