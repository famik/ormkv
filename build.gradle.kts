// Top-level build file where you can add configuration options common to all sub-projects/modules.


plugins {
    kotlin("jvm")
}


buildscript {
//    ext.kotlin_version = '1.6.10'
//ext {
//    libVersion = "0.3.0"
//    groupId = "com.lwjlol.ccsp"
//    artifactId = "ccsp"
//    pkg_name = "ccsp"
//    libraryDescription = 'ccsp'
//    siteUrl = 'https://github.com/lwj1994/ccsp.git'
//    gitUrl = 'https://github.com/lwj1994/ccsp.git'
//    developerId = 'wenchieh'
//    developerName = 'wenchieh'
//    developerEmail = 'alwjlola@gmail.com'
//    licenseName = 'The Apache Software License, Version 2.0'
//    licenseUrl = 'http://www.apache.org/licenses/LICENSE-2.0.txt'
//    allLicenses = ["Apache-2.0"]
//}
    repositories {
        google()
        maven("https://jitpack.io")
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.6.10"))
        classpath("com.android.tools.build:gradle:4.1.3")
//        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.18")
        classpath("com.google.devtools.ksp:symbol-processing-gradle-plugin:1.6.10-1.0.2")
        // https://mvnrepository.com/artifact/org.jetbrains.kotlin.jvm/org.jetbrains.kotlin.jvm.gradle.plugin
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}

task("cleanApp", Delete::class) {
    delete(rootProject.buildDir)
}
