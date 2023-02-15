package com.garamgaebi.garamgaebi.src.main.home

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HomeUserItemDecoration(val context : Context) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = dpToPx(16)
        val position = parent.getChildAdapterPosition(view)

        if(position == 0) {
            outRect.left = offset
        } else {
            outRect.left = 0
        }
        outRect.right = offset

    }
    private fun dpToPx(dp : Int) : Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }
}