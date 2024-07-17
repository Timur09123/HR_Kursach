buildscript {
    val kotlin_version by extra("1.8.10")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
