package rio.arj.detail.di

import dagger.Module
import dagger.Provides
import rio.arj.data.NetworkService
import rio.arj.data.di.DataModule
import rio.arj.data.di.DataScope
import rio.arj.data.repository.detail.DetailRepository
import rio.arj.data.repository.detail.DetailRepositoryImpl
import rio.arj.detail.DetailAyahUseCase

@Module(includes = [DataModule::class])
class DetailAyahModule {
  @DetailAyahScope
  @Provides
  fun provideRepository(@DataScope service: NetworkService): DetailRepository {
    return DetailRepositoryImpl(service)
  }

  @DetailAyahScope
  @Provides
  fun provideUseCase(repository: DetailRepository): DetailAyahUseCase {
    return DetailAyahUseCase(repository)
  }
}