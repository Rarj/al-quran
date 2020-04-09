package rio.arj.dashboard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rio.arj.dashboard.R
import rio.arj.dashboard.databinding.ActivityDashboardBinding
import rio.arj.dashboard.di.DaggerDashboardComponent
import rio.arj.dashboard.di.DashboardModule
import rio.arj.ui.ItemDecoration
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModelDashboard: DashboardViewModel
  private lateinit var bindingDashboard: ActivityDashboardBinding

  private lateinit var dashboardAdapter: DashboardAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerDashboardComponent.builder()
          .dashboardModule(DashboardModule())
          .build()
          .inject(this)

    viewModelDashboard = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
    bindingDashboard = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
    bindingDashboard.viewModelDashboard = viewModelDashboard
    bindingDashboard.lifecycleOwner = this

    observer()
  }

  private fun initAdapter() {
    dashboardAdapter = DashboardAdapter(viewModelDashboard.listSurah.value)

    bindingDashboard.recyclerAlquran.apply {
      layoutManager = LinearLayoutManager(this@DashboardActivity)
      addItemDecoration(ItemDecoration(16))
      this.adapter = dashboardAdapter
      dashboardAdapter.notifyDataSetChanged()
    }
  }

  private fun observer() {
    viewModelDashboard.isSuccess.observe(this, Observer { isSucces ->
      if (isSucces) {
        initAdapter()
      } else {
        Toast.makeText(this, "Load Failed. Try Again", Toast.LENGTH_LONG).show()
      }
    })
  }
}
