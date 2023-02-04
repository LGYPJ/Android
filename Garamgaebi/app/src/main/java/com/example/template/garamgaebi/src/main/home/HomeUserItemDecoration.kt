package com.example.template.garamgaebi.src.main.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HomeUserItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = 20
        val position = parent.getChildAdapterPosition(view)

        if(position == 0) {
            outRect.left = 0
        } else {
            outRect.left = offset
        }
        outRect.right = offset

    }
}