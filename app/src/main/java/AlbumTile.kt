package com.example.myapp

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import android.widget.ImageView
import com.example.lunabee_proto.R

class AlbumTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val imageView: ImageView = ImageView(context)

    init {
        layoutParams = LayoutParams(200, 200)
        radius = 16f  // Rayon des coins du CardView
        imageView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.lafeve_24)
        addView(imageView)
    }

    fun setImageResource(resId: Int) {
        imageView.setImageResource(resId)
    }
}