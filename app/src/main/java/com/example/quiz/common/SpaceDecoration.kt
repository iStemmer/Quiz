package com.example.quiz.common

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class SpaceDecoration(private var space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space
    }
}