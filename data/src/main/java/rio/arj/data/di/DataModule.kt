package rio.arj.data.di

import dagger.Module
import dagger.Provides
import rio.arj.data.NetworkService
import rio.arj.data.di.Network.provideRetrofit

@Module
class DataModule {

  @Provides
  @DataScope
  fun provideMovieService(): NetworkService {
    return provideRetrofit().create(NetworkService::class.java)
  }

}