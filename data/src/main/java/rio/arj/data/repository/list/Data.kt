package rio.arj.data.repository.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
      val arti: String? = "",
      val asma: String? = "",
      val audio: String? = "",
      val ayat: Int? = 0,
      val keterangan: String? = "",
      val nama: String? = "",
      val nomor: String? = "",
      val type: String? = ""
) : Parcelable