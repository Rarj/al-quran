package rio.arj.data.repository.detail

class DetailAyahModel : ArrayList<DetailAyahModelItem>()

data class DetailAyahModelItem(
      var ar: String?,
      var id: String?,
      var nomor: String?,
      var tr: String?
)