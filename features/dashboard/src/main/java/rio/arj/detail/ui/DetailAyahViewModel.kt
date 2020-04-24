package rio.arj.detail.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rio.arj.data.repository.detail.DetailAyahModel
import rio.arj.detail.DetailAyahUseCase
import javax.inject.Inject

private interface DetailAyahContract {
  fun loadDetailAyah(numberOfAyah: String)
}

class DetailAyahViewModel @Inject constructor(val useCase: DetailAyahUseCase) : ViewModel(), DetailAyahContract {

  var compositeDisposable: CompositeDisposable = CompositeDisposable()
  var detailAyahModel = MutableLiveData<DetailAyahModel>()

  override fun loadDetailAyah(numberOfAyah: String) {
    compositeDisposable.add(
          useCase.getListAyah(numberOfAyah)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                  detailAyahModel.value = response
                }, {})
    )
  }

  override fun onCleared() {
    super.onCleared()

    compositeDisposable.clear()
  }
}