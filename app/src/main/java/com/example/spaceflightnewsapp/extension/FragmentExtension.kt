package com.example.spaceflightnewsapp.extension

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.spaceflightnewsapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.dialog(
    block: MaterialAlertDialogBuilder.() -> Unit,
) {
    val builder = MaterialAlertDialogBuilder(requireContext())
    block.invoke(builder)
    builder.show()
}

fun Fragment.errorDialog(
    block: MaterialAlertDialogBuilder.() -> Unit,
) {
    this.dialog {
        setTitle(this.context.getString(R.string.error))
        setCancelable(true)
        setPositiveButton(this.context.getString(R.string.okay)) { _, _ -> }
        block.invoke(this)
    }
}

@SuppressLint("ClickableViewAccessibility")
fun Fragment.easyCloseableKeyboard(view: View) {
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            requireActivity().hideSoftKeyboard()
            false
        }
    }

    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val innerView: View = view.getChildAt(i)
            easyCloseableKeyboard(innerView)
        }
    }
}
