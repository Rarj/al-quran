package rio.arj.dashboard.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import viewmodel.ViewModelFactory
import rio.arj.dashboard.ui.DashboardViewModel
import viewmodel.ViewModelKey

@Module
abstract class DashboardViewModelModule {

  @DashboardScope
  @Binds
  internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(DashboardViewModel::class)
  internal abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

}