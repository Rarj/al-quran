package rio.arj.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import rio.arj.data.repository.detail.DetailAyahModel
import rio.arj.data.repository.list.Data

interface NetworkService {

  @GET("data.json")
  fun getListQuran(): Observable<List<Data>>

  @GET("surat/{ayah}.json")
  fun getListAyah(@Path("ayah") ayah: String): Observable<DetailAyahModel>

}