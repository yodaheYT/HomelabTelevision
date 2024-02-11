plugins {
    id("java")
}

group = "yodahes.shit"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("uk.co.caprica:vlcj:4.4.4")
    implementation("uk.co.caprica:vlcj-natives:4.1.0")
    implementation("net.java.dev.jna:jna:5.2.0")
    implementation("net.java.dev.jna:jna-platform:5.2.0")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jar {
    manifest.attributes["Main-Class"] = "main.Main"
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}