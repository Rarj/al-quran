package rio.arj.detail.di

import dagger.Component
import rio.arj.detail.ui.DetailAyahActivity

@DetailAyahScope
@Component(modules = [
  DetailAyahModule::class,
  DetailAyahViewModelModule::class
])
interface DetailAyahComponent {
  fun inject(detailAyahActivity: DetailAyahActivity)
}