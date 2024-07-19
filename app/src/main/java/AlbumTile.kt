package com.example.myapp

import AlbumData
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import android.widget.ImageView
import com.example.lunabee_proto.AlbumActivity
import com.example.lunabee_proto.R

class AlbumTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val imageView: ImageView = ImageView(context)
    private var data: AlbumData = AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24)

    init {
        radius = 16f
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.lafeve_24)
        addView(imageView)
    }

    fun setImageResource(resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setData(data: AlbumData) {
        this.data = data
        imageView.setImageResource(data.getImage())
        imageView.setOnClickListener {
            val intent = Intent(context, AlbumActivity::class.java).apply {
                putExtra("ALBUM_TITLE", data.getName())
                putExtra("ALBUM_ARTIST", data.getArtist())
                putExtra("ALBUM_YEAR", data.getYear())
                putExtra("ALBUM_COVER", data.getImage())
            }
            context.startActivity(intent)
        }
    }
}