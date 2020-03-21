package rio.arj.data.repository.list

import io.reactivex.Observable
import rio.arj.data.NetworkService
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(val networkService: NetworkService) : ListRepository {
  override fun getListQuran(): Observable<List<Data>> {
    return networkService.getListQuran()
  }
}