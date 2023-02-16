package com.garamgaebi.garamgaebi.src.main.gathering

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GatheringItemDecoration : RecyclerView.ItemDecoration() {
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
            outRect.top = 0
        } else {
            outRect.top = offset
        }
        outRect.bottom = offset
    }
}