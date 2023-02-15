package com.garamgaebi.garamgaebi.garamgaebi.src.main.networking_game

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NetworkingGameProfileHorizontalItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = 31
        outRect.right = offset
        outRect.left = offset

    }
}