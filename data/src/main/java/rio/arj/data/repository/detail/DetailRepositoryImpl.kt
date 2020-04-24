package rio.arj.data.repository.detail

import io.reactivex.Observable
import rio.arj.data.NetworkService
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(val networkService: NetworkService) : DetailRepository {
  override fun getListAyah(ayah: String): Observable<DetailAyahModel> {
    return networkService.getListAyah(ayah)
  }
}