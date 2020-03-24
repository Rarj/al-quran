object Version {
  const val buildGradle = "3.5.3"

  const val kotlinVersion = "1.3.61"
  const val kotlinExtension = "1.2.0"

  const val appCompat = "1.1.0"
  const val constraintLayout = "1.1.3"
  const val material = "1.1.0-alpha05"
  const val recyclerView = "1.0.0"

  const val dataBindingCompiler = "3.1.4"

  const val archLifecycle = "2.0.0"

  const val dagger = "2.21"

  const val retrofit = "2.6.0"
  const val gson = "2.8.5"
  const val rxRetrofit = "2.4.0"
  const val okHttpLogging = "3.11.0"

  const val rxAndroid = "2.1.0"
  const val rxJava = "2.1.0"
  const val rxBinding = "3.0.0"

  const val glide = "4.8.0"

  const val jUnit = "4.12"
  const val jUnitExtension = "1.1.1"
  const val espresso = "3.2.0"
}

object GradleProjectLevel {
  val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
  val buildGradle = "com.android.tools.build:gradle:${Version.buildGradle}"
}

object Android {
  val compileAndTargetSdk = 29
  val buildTools = "29.0.2"
  val applicationId = "rio.arj.alquran"
  val minimumSdk = 19
  val versionCode = 1
  val versionName = "1.0"
  val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Dependencies {
  val kotlinJdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlinVersion}"
  val kotlinExtension = "androidx.core:core-ktx:${Version.kotlinExtension}"

  val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
  val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
  val material = "com.google.android.material:material:${Version.material}"
  val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"

  val dataBindingCompiler = "com.android.databinding:compiler:${Version.dataBindingCompiler}"
}

object Jetpack {
  val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Version.archLifecycle}"
  val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.archLifecycle}"
}

object Dagger {
  val dagger = "com.google.dagger:dagger:${Version.dagger}"
  val android = "com.google.dagger:dagger-android:${Version.dagger}"
  val androidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
  val compiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
  val processor = "com.google.dagger:dagger-android-processor:${Version.dagger}"
}

object Retrofit {
  val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
  val gson = "com.google.code.gson:gson:${Version.gson}"
  val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
  val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Version.okHttpLogging}"
}

object Rx {
  val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
  val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
  val rxBinding = "com.jakewharton.rxbinding3:rxbinding:${Version.rxBinding}"
  val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.rxRetrofit}"
}

object Glide {
  val glide = "com.github.bumptech.glide:glide:${Version.glide}"
  val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
}

object Testing {
  val jUnit = "junit:junit:${Version.jUnit}"
  val jUnitExtension = "androidx.test.ext:junit:${Version.jUnitExtension}"
  val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
}
