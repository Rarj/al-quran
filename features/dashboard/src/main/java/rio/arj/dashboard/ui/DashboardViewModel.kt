package rio.arj.dashboard.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rio.arj.dashboard.DashboardUseCase
import javax.inject.Inject

interface DashboardContract {
  fun loadListQuran()
}

class DashboardViewModel @Inject constructor(
      val useCase: DashboardUseCase
) : ViewModel(), DashboardContract {

  var compositeDisposable: CompositeDisposable = CompositeDisposable()
  var isSuccess = MutableLiveData<Boolean>()

  init {
    loadListQuran()
  }

  override fun loadListQuran() {
    compositeDisposable.add(
          useCase.getListQuran()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                  isSuccess.value = true

                }, {
                  isSuccess.value = true
                })
    )
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}