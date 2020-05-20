package rio.arj.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_FORCED
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT

fun View.hideKeyboard() {
  val imm = this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
  this.isFocusable = true

  val imm = this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
  imm.toggleSoftInput(SHOW_FORCED, SHOW_IMPLICIT)
}