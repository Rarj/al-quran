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
import rio.arj.data.repository.list.Data
import rio.arj.detail.di.DaggerDetailAyahComponent
import rio.arj.detail.di.DetailAyahModule
import javax.inject.Inject

class DetailAyahActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  lateinit var viewModel: DetailAyahViewModel
  lateinit var binding: ActivityDetailAyahBinding

  lateinit var detailAyahAdapter: DetailAyahAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DaggerDetailAyahComponent.builder()
          .detailAyahModule(DetailAyahModule())
          .build().inject(this)

    viewModel = ViewModelProvider(this, viewModelFactory).get(DetailAyahViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_ayah)
    binding.detailAyahViewModel = viewModel
    binding.lifecycleOwner = this

    val data = intent.getParcelableExtra(INTENT_KEY) as Data
    viewModel.loadDetailAyah(data.nomor.toString())

    viewModel.detailAyahModel.observe(this, Observer { listAyah ->
      if (listAyah.isNotEmpty()) {
        detailAyahAdapter = DetailAyahAdapter(listAyah)

        binding.recyclerAyah.apply {
          layoutManager = LinearLayoutManager(this@DetailAyahActivity)
          this.adapter = detailAyahAdapter
          detailAyahAdapter.notifyDataSetChanged()
        }
      }
    })

  }
}
