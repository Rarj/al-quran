package rio.arj.dashboard.di

import dagger.Module
import dagger.Provides
import rio.arj.dashboard.DashboardUseCase
import rio.arj.data.NetworkService
import rio.arj.data.di.DataModule
import rio.arj.data.di.DataScope
import rio.arj.data.repository.list.ListRepository
import rio.arj.data.repository.list.ListRepositoryImpl

@Module(includes = [DataModule::class])
class DashboardModule {
  @DashboardScope
  @Provides
  fun provideRepository(@DataScope service: NetworkService): ListRepository {
    return ListRepositoryImpl(service)
  }

  @DashboardScope
  @Provides
  fun provideUseCase(repository: ListRepository): DashboardUseCase {
    return DashboardUseCase(repository)
  }
}