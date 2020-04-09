package rio.arj.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(private var margin: Int) : RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
  ) {
    with(outRect) {
      if (parent.getChildAdapterPosition(view) == 0) {
        top = margin * 2
      }
      left = margin * 2
      right = margin * 2
      bottom = margin * 2
    }
  }
}