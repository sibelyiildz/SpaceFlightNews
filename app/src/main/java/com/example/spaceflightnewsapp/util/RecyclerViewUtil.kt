package com.example.spaceflightnewsapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnewsapp.extension.dp

class SpaceItemDecorationVertical(
    private val margin: Int = 12.dp,
    private val lastDraw: Boolean = true
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 1
        val pos = parent.getChildLayoutPosition(view)
        val bottom = if (lastDraw) {
            margin
        } else {
            if (pos == itemCount - 1) {
                0
            } else {
                margin
            }
        }
        outRect.set(0, 0, 0, bottom)
    }
}

