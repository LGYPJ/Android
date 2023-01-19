package com.softsquared.template.Garamgaebi.src.main.networking

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NetworkingHorizontalItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = 72
        val offset = 60
        outRect.right = offset

    }
}