package rio.arj.detail.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rio.arj.core.Constants.INTENT_KEY
import rio.arj.dashboard.R
import rio.arj.dashboard.databinding.ActivityDetailAyahBinding
import rio.arj.data.repository.detail.DetailAyahModel
import rio.arj.data.repository.detail.DetailAyahModelItem
import rio.arj.data.repository.list.Data
import rio.arj.detail.di.DaggerDetailAyahComponent
import rio.arj.detail.di.DetailAyahModule
import rio.arj.ui.ItemDecoration
import javax.inject.Inject

class DetailAyahActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  lateinit var viewModel: DetailAyahViewModel
  lateinit var binding: ActivityDetailAyahBinding

  lateinit var detailAyahAdapter: DetailAyahAdapter
  val SURAH_ALFATIHAH = "1"
  lateinit var data: Data

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DaggerDetailAyahComponent.builder()
          .detailAyahModule(DetailAyahModule())
          .build().inject(this)

    data = intent.getParcelableExtra(INTENT_KEY) as Data

    viewModel = ViewModelProvider(this, viewModelFactory).get(DetailAyahViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_ayah)
    binding.detailAyahViewModel = viewModel
    binding.dataModel = data
    binding.lifecycleOwner = this

    viewModel.loadDetailAyah(data.nomor.toString())

    listener()
    observer()
  }

  private fun listener() {
    binding.toolbarAyah.setNavigationOnClickListener { finish() }
  }

  private fun observer() {
    viewModel.detailAyahModel.observe(this, Observer { listAyah ->
      binding.isSuccess = listAyah.isNotEmpty()
      if (listAyah.isNotEmpty()) {
        removeBismillah(listAyah)
        detailAyahAdapter = DetailAyahAdapter(listAyah)
        binding.recyclerAyah.apply {
          layoutManager = LinearLayoutManager(this@DetailAyahActivity)
          addItemDecoration(ItemDecoration(16))
          this.adapter = detailAyahAdapter
          detailAyahAdapter.notifyDataSetChanged()
        }
      }
    })
  }

  private fun removeBismillah(listAyah: DetailAyahModel) {
    if (data.nomor == SURAH_ALFATIHAH) {
      listAyah.removeAt(0)
    } else {
      if (listAyah[0].ar.toString().contains(getString(R.string.detail_bismillah_ar))) {
        val removed = listAyah[0].ar.toString().replace(getString(R.string.detail_bismillah_ar), " ")
        listAyah[0] = DetailAyahModelItem(
              removed,
              listAyah[0].id,
              listAyah[0].nomor, listAyah[0].tr
        )
      }
    }
  }
}
