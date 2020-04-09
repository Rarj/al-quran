package rio.arj.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import rio.arj.dashboard.BR
import rio.arj.dashboard.databinding.ItemSurahBinding
import rio.arj.data.repository.list.Data

class DashboardAdapter(
      private val listSurah: List<Data>?
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemSurahBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return listSurah?.size ?: 0
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(listSurah?.get(position))
  }

  class ViewHolder(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(model: Data?) {
      viewDataBinding.setVariable(BR.dataSurah, model)
      viewDataBinding.executePendingBindings()
    }
  }

}