package ru.lyubeznyh.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class ContactItemDecorator(private val divider: Int):ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val halfDivider = divider/2
        with(outRect){
            top = halfDivider
            bottom = halfDivider
            left = halfDivider
            right = halfDivider
        }
    }
}
