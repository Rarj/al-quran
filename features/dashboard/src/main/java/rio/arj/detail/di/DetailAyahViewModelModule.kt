package rio.arj.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import rio.arj.core.viewmodel.ViewModelFactory
import rio.arj.core.viewmodel.ViewModelKey
import rio.arj.detail.ui.DetailAyahViewModel

@Module
abstract class DetailAyahViewModelModule {

  @DetailAyahScope
  @Binds
  internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(DetailAyahViewModel::class)
  internal abstract fun bindDetailAyahViewModel(detailAyahViewModel: DetailAyahViewModel): ViewModel

}