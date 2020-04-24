package rio.arj.detail.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import rio.arj.dashboard.BR
import rio.arj.dashboard.databinding.ItemAyahBinding
import rio.arj.data.repository.detail.DetailAyahModelItem

class DetailAyahAdapter(
      private val listAyah: ArrayList<DetailAyahModelItem>
) : RecyclerView.Adapter<DetailAyahAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemAyahBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return listAyah.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(listAyah[position])
  }

  class ViewHolder(private val viewDataBinding: ItemAyahBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(model: DetailAyahModelItem) {
      viewDataBinding.setVariable(BR.itemModel, model)
      viewDataBinding.executePendingBindings()
    }
  }
}