package rio.arj.dashboard

import io.reactivex.Observable
import rio.arj.data.repository.list.Data
import rio.arj.data.repository.list.ListRepository
import javax.inject.Inject

class DashboardUseCase @Inject constructor(private val repository: ListRepository) {
  fun getListQuran(): Observable<List<Data>> {
    return repository.getListQuran()
  }
}