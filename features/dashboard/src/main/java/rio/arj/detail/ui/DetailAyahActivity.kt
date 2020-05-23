package rio.arj.detail.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

  private lateinit var viewModel: DetailAyahViewModel
  private lateinit var binding: ActivityDetailAyahBinding

  private val detailAyahAdapter: DetailAyahAdapter by lazy {
    DetailAyahAdapter(viewModel.detailAyahModel.value!!)
  }
  private val SURAH_ALFATIHAH = "1"
  private lateinit var data: Data

  private var mediaPlayer: MediaPlayer? = null

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

    mediaPlayer = MediaPlayer()
    mediaPlayer?.setDataSource(this, Uri.parse(data.audio))
    mediaPlayer?.prepareAsync()

    viewModel.loadDetailAyah(data.nomor.toString())

    listener()
    observer()
  }

  private fun listener() {
    binding.toolbarAyah.setNavigationOnClickListener { finish() }

    var isPlayedMurottal = false
    binding.toolbarAyah.setOnMenuItemClickListener { menuItem ->
      when (menuItem.itemId) {
        R.id.item_play_murottal -> {
          mediaPlayer?.setOnCompletionListener { _ ->
            isPlayedMurottal = false
            setTitleMenuItem(menuItem, getString(R.string.detail_play_murattal), R.drawable.ic_play)
          }

          isPlayedMurottal = !isPlayedMurottal
          if (isPlayedMurottal) {
            mediaPlayer?.start()
            setTitleMenuItem(menuItem, getString(R.string.detail_stop_murattal), R.drawable.ic_stop)
          } else {
            if (mediaPlayer?.isPlaying!!) {
              mediaPlayer?.pause()
              mediaPlayer?.seekTo(0)
            }
            setTitleMenuItem(menuItem, getString(R.string.detail_play_murattal), R.drawable.ic_play)
          }
        }
        R.id.item_share_surah -> {

        }
      }
      true
    }
  }

  private fun setTitleMenuItem(menuItem: MenuItem, title: String, @DrawableRes drawable: Int) {
    menuItem.title = title
    menuItem.icon = ContextCompat.getDrawable(this, drawable)
  }

  private fun observer() {
    viewModel.detailAyahModel.observe(this, Observer { listAyah ->
      binding.isSuccess = listAyah.isNotEmpty()
      if (listAyah.isNotEmpty()) {
        removeBismillah(listAyah)
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

    viewModel.detailAyahModel.value?.add(0, DetailAyahModelItem(
          getString(R.string.detail_bismillah_ar),
          getString(R.string.detail_bismillah_mean),
          "0",
          getString(R.string.detail_bismillah_id)
    ))
  }

  override fun onDestroy() {
    super.onDestroy()

    if (mediaPlayer != null) {
      mediaPlayer?.stop()
      mediaPlayer?.release()
    }
  }

  override fun onPause() {
    super.onPause()

    if (mediaPlayer != null) {
      if (mediaPlayer!!.isPlaying) {
        mediaPlayer!!.pause()
      }
    }
  }

  override fun onResume() {
    super.onResume()

    if (mediaPlayer != null) {
      if (!mediaPlayer!!.isPlaying && mediaPlayer!!.currentPosition > 1) {
        mediaPlayer?.start()
      }
    }
  }
}
