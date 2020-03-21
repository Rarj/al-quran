package rio.arj.dashboard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import rio.arj.dashboard.R
import rio.arj.dashboard.databinding.ActivityDashboardBinding
import rio.arj.dashboard.di.DaggerDashboardComponent
import rio.arj.dashboard.di.DashboardModule
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModelDashboard: DashboardViewModel
  private lateinit var bindingDashboard: ActivityDashboardBinding

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

    viewModelDashboard.isSuccess.observe(this, Observer {
      if (it) {
        Toast.makeText(this, "data loaded", Toast.LENGTH_LONG).show()
      } else {
        Toast.makeText(this, "load failed", Toast.LENGTH_LONG).show()
      }
    })

  }
}
