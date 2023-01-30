package com.softsquared.template.Garamgaebi.src.main.networking_game

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NetworkingGameSelectHorizontalItemDecoration :RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //val offset = 42
        //outRect.right = offset

    }
}