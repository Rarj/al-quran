package rio.arj.data

import io.reactivex.Observable
import retrofit2.http.GET
import rio.arj.data.repository.list.Data

interface NetworkService {

  @GET("data.json")
  fun getListQuran(): Observable<List<Data>>

}