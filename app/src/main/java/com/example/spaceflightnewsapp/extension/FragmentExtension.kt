package com.example.spaceflightnewsapp.extension

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.dialog(
    block: MaterialAlertDialogBuilder.() -> Unit,
) {
    val builder = MaterialAlertDialogBuilder(requireContext())
    block.invoke(builder)
    builder.show()
}
