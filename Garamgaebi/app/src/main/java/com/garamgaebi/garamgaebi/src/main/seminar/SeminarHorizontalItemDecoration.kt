package com.garamgaebi.garamgaebi.src.main.seminar

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SeminarHorizontalItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = 60
        //val position = parent.getChildAdapterPosition(view)
        outRect.right = offset

    }
}