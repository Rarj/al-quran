package rio.arj.dashboard.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rio.arj.dashboard.DashboardUseCase
import rio.arj.data.repository.list.Data
import javax.inject.Inject

private interface DashboardContract {
  fun loadListSurah()
}

class DashboardViewModel @Inject constructor(
      val useCase: DashboardUseCase
) : ViewModel(), DashboardContract {

  var compositeDisposable: CompositeDisposable = CompositeDisposable()
  var isSuccess = MutableLiveData<Boolean>()
  var listSurah = MutableLiveData<List<Data>>()

  init {
    loadListSurah()
  }

  override fun loadListSurah() {
    compositeDisposable.add(
          useCase.getListQuran()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ surah ->
                  listSurah.value = surah
                  isSuccess.value = true
                }, {
                  isSuccess.value = false
                })
    )
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}