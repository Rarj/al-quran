package rio.arj.data.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rio.arj.data.BuildConfig.BASE_URL
import rio.arj.data.NetworkService
import rio.arj.data.network.NetworkInterceptor
import rio.arj.data.network.RequestInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class DataModule {

  @Provides
  @Singleton
  @DataScope
  internal fun provideGson(): Gson {
    val gsonBuilder = GsonBuilder()
    return gsonBuilder.create()
  }

  @Provides
  @Singleton
  @DataScope
  internal fun provideCache(application: Application): Cache {
    val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
    val httpCacheDirectory = File(application.cacheDir, "http-cache")
    return Cache(httpCacheDirectory, cacheSize)
  }


  @Provides
  @Singleton
  @DataScope
  internal fun provideNetworkInterceptor(application: Application): NetworkInterceptor {
    return NetworkInterceptor(application.applicationContext)
  }

  @Provides
  @Singleton
  @DataScope
  internal fun provideOkhttpClient(
        cache: Cache,
        networkInterceptor: NetworkInterceptor
  ): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient.Builder()
    httpClient.cache(cache)
    httpClient.addInterceptor(networkInterceptor)
    httpClient.addInterceptor(logging)
    httpClient.addNetworkInterceptor(RequestInterceptor())
    httpClient.connectTimeout(30, TimeUnit.SECONDS)
    httpClient.readTimeout(30, TimeUnit.SECONDS)
    return httpClient.build()
  }

  @Provides
  @Singleton
  @DataScope
  internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .baseUrl(BASE_URL)
          .client(okHttpClient)
          .build()
  }

  @Provides
  @Singleton
  @DataScope
  internal fun provideMovieService(retrofit: Retrofit): NetworkService {
    return retrofit.create(NetworkService::class.java)
  }

}