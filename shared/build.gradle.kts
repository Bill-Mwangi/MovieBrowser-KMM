plugins {
  kotlin("multiplatform")
  id("com.android.library")
  id("com.squareup.sqldelight")
  kotlin("plugin.serialization") version ("1.8.0")
}

kotlin {
  android()

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "shared"
    }
  }

  sourceSets {
    val sqlDelightVersion = "1.5.3"
    val ktorVersion = "2.2.2"

    val commonMain by getting {
      dependencies {
        implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }
    val androidMain by getting {
      dependencies {
        implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
        implementation("io.ktor:ktor-client-android:$ktorVersion")
      }
    }
    val androidTest by getting
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependencies {
        implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
        implementation("io.ktor:ktor-client-darwin:$ktorVersion")
      }
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
    }
    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}
buildscript {
  dependencies {
    classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
  }
}
sqldelight {
  database("MovieDatabase") {
    packageName = "com.bill.multiplatformmoviebrowser.database"
    sourceFolders = listOf("sqldelight")
  }
}

android {
  namespace = "com.bill.multiplatformmoviebrowser"
  compileSdk = 33
  defaultConfig {
    minSdk = 24
    targetSdk = 33
  }
}