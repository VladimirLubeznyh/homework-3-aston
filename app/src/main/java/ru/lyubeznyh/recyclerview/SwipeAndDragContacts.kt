package ru.lyubeznyh.recyclerview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeAndDragContacts(val onItemMove: (Int, Int) -> Unit, val onItemDelete: (Int) -> Unit) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
        ItemTouchHelper.RIGHT
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        onItemMove(viewHolder.absoluteAdapterPosition, target.absoluteAdapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemDelete(viewHolder.absoluteAdapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = true

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float = 0.4f

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView: View = viewHolder.itemView

        val iconDelete: Bitmap? =
            ContextCompat.getDrawable(recyclerView.context, R.drawable.baseline_delete_24)
                ?.toBitmap()

        val paint = Paint().also {
            it.color = Color.RED
            it.style = Paint.Style.FILL
        }

        c.drawRect(
            itemView.left.toFloat() + dX,
            itemView.top.toFloat(),
            itemView.left.toFloat(),
            itemView.bottom.toFloat(),
            paint
        )

        val iconMarginLeft = (dX * 0.3f).coerceAtMost(150f).coerceAtLeast(0f)
        iconDelete?.let {
            c.drawBitmap(
                iconDelete,
                itemView.left.toFloat() + iconMarginLeft - iconDelete.width,
                itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - iconDelete.height) / 2,
                paint
            )
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}
