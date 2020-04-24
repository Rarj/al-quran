package rio.arj.detail

import io.reactivex.Observable
import rio.arj.data.repository.detail.DetailAyahModel
import rio.arj.data.repository.detail.DetailRepository
import javax.inject.Inject

class DetailAyahUseCase @Inject constructor(val repository: DetailRepository) {
  fun getListAyah(ayah: String): Observable<DetailAyahModel> {
    return repository.getListAyah(ayah)
  }
}