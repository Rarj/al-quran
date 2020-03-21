package rio.arj.dashboard.di

import dagger.Component
import rio.arj.dashboard.ui.DashboardActivity

@DashboardScope
@Component(modules = [
  DashboardModule::class,
  DashboardViewModelModule::class])
interface DashboardComponent {
  fun inject(dashboardActivity: DashboardActivity)
}

