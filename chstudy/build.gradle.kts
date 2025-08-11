plugins {
    id("java")
}

group = "dev.ryotaro"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    // https://mvnrepository.com/artifact/org.infinispan/infinispan-client-hotrod
    implementation("org.infinispan:infinispan-client-hotrod:16.0.0-SNAPSHOT")
    // https://mvnrepository.com/artifact/org.infinispan/infinispan-api
    implementation("org.infinispan:infinispan-api:16.0.0-SNAPSHOT")
    // https://mvnrepository.com/artifact/org.infinispan/infinispan-query-dsl
    implementation("org.infinispan:infinispan-query-dsl:16.0.0-SNAPSHOT")
    // https://mvnrepository.com/artifact/org.infinispan/infinispan-remote-query-client
    implementation("org.infinispan:infinispan-remote-query-client:16.0.0-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}