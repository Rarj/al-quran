package rio.arj.data.repository.list

import io.reactivex.Observable

interface ListRepository {
  fun getListQuran(): Observable<List<Data>>
}