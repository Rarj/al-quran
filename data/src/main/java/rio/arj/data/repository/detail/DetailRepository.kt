package rio.arj.data.repository.detail

import io.reactivex.Observable

interface DetailRepository {
  fun getListAyah(ayah: String): Observable<DetailAyahModel>
}