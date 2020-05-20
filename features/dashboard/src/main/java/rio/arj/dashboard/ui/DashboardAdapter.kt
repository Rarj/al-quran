package rio.arj.dashboard.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import rio.arj.ItemClickListener
import rio.arj.dashboard.BR
import rio.arj.dashboard.R
import rio.arj.dashboard.databinding.ItemSurahBinding
import rio.arj.data.repository.list.Data
import java.util.concurrent.TimeUnit

class DashboardAdapter(
      private val context: Context,
      private val listSurah: List<Data>?,
      private val listener: ItemClickListener<Data>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

  fun clear() {
    listSurah?.toMutableList()?.clear()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemSurahBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return listSurah?.size ?: 0
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(context, listSurah?.get(position), listener)
  }

  class ViewHolder(private val viewDataBinding: ItemSurahBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    @SuppressLint("CheckResult")
    fun bind(context: Context, model: Data?, listener: ItemClickListener<Data>) {
      viewDataBinding.setVariable(BR.dataSurah, model)
      viewDataBinding.executePendingBindings()

      var isMaxLine = false
      viewDataBinding.textMore.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
              if (isMaxLine) {
                viewDataBinding.textOverview.maxLines = 3
                viewDataBinding.textMore.text = context.getString(R.string.dashboard_more_caption)
              } else {
                viewDataBinding.textOverview.maxLines = Integer.MAX_VALUE
                viewDataBinding.textMore.text = context.getString(R.string.dashboard_less_caption)
              }
              isMaxLine = !isMaxLine
            }

      viewDataBinding.root.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
              model?.let { value -> listener.onItemClicked(value) }
            }
    }
  }
}