package rio.arj.dashboard.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rio.arj.ItemClickListener
import rio.arj.core.Constants.INTENT_KEY
import rio.arj.dashboard.R
import rio.arj.dashboard.databinding.ActivityDashboardBinding
import rio.arj.dashboard.di.DaggerDashboardComponent
import rio.arj.dashboard.di.DashboardModule
import rio.arj.data.repository.list.Data
import rio.arj.detail.ui.DetailAyahActivity
import rio.arj.ui.ItemDecoration
import rio.arj.ui.hideKeyboard
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

    listener()
    observer()
  }

  private fun listener() {
    bindingDashboard.toolbarDashboard.setOnMenuItemClickListener {
      when (it.itemId) {
        R.id.item_dashboard_search -> {
          bindingDashboard.isSearchMode = true
        }
      }
      true
    }

    bindingDashboard.buttonCancelSearch.setOnClickListener {
      bindingDashboard.isSearchMode = false
      viewModelDashboard.query.value = ""
    }

    bindingDashboard.inputQuerySearch.setOnEditorActionListener { view, actionId, event ->
      if (actionId == EditorInfo.IME_ACTION_SEARCH) {
        bindingDashboard.root.hideKeyboard()
      }
      true
    }
  }

  private fun initAdapter() {
    dashboardAdapter = DashboardAdapter(this, viewModelDashboard.listSurah.value,
          object : ItemClickListener<Data> {
            override fun onItemClicked(value: Data) {
              val intent = Intent(this@DashboardActivity, DetailAyahActivity::class.java)
              intent.putExtra(INTENT_KEY, value)
              startActivity(intent)
            }
          })

    bindingDashboard.recyclerAlquran.apply {
      layoutManager = LinearLayoutManager(this@DashboardActivity)
      addItemDecoration(ItemDecoration(16))
      this.adapter = dashboardAdapter
      dashboardAdapter.notifyDataSetChanged()
    }
  }

  private fun observer() {
    viewModelDashboard.isSuccess.observe(this, Observer { isSuccess ->
      if (isSuccess) {
        initAdapter()
      } else {
        Toast.makeText(this, "Load Failed. Try Again", Toast.LENGTH_LONG).show()
      }
    })

    viewModelDashboard.query.observe(this, Observer { query ->
      if (query.isNotBlank()) {

      }
    })
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    if (currentFocus != null) {
      currentFocus!!.hideKeyboard()
    }
    return super.dispatchTouchEvent(ev)
  }
}
