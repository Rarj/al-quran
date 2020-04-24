package rio.arj

interface ItemClickListener<T> {
  fun onItemClicked(value: T)
}