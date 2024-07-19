package com.example.myapp

import ArtistData
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.example.lunabee_proto.R

class ArtistTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val imageView: ImageView = ImageView(context)
    private var data: ArtistData? = null

    init {
        radius = 100f
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.la_feve)
        addView(imageView)
    }

    fun setData(data: ArtistData) {
        this.data = data
        imageView.setImageResource(data.getImage())
    }

    fun setImageResource(resId: Int) {
        imageView.setImageResource(resId)
    }
}