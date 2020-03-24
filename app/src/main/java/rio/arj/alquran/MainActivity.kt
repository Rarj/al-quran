package rio.arj.alquran

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import rio.arj.dashboard.ui.DashboardActivity

class MainActivity : AppCompatActivity() {

  private val handler = Handler()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val runnable = Runnable {
      startActivity(Intent(this, DashboardActivity::class.java))
      finish()
    }

    handler.postDelayed(runnable, 2000)
  }

  override fun onDestroy() {
    super.onDestroy()
    handler.removeCallbacksAndMessages(null)
  }
}
