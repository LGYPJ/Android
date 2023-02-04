package com.example.template.garamgaebi.src.main.networking_game

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NetworkingGameSelectVerticalItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = 50
        outRect.bottom = offset

    }

}