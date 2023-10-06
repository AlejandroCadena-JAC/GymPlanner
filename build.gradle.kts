// Top-level build file where you can add configuration options common to all sub-projects/modules.
    import org/jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    application
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

application {
    mainClass.set("org.jlleitschuh.gradle.ktlint.sample.kotlin.MainKt")
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    reporters {
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.JSON)
        reporter(ReporterType.HTML)
    }
    filter {
        exclude("**/style-violations.kt")
    }
}
