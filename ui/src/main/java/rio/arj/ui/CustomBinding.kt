package rio.arj.ui

import android.os.Build
import android.text.Html
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter

object CustomBinding {

  @BindingAdapter("textHtml")
  @JvmStatic
  fun AppCompatTextView.setTextHtml(text: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
    } else {
      this.text = Html.fromHtml(text);
    }
  }

}