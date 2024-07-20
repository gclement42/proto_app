package com.example.myapp

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import androidx.cardview.widget.CardView
import android.widget.ImageView
import com.example.lunabee_proto.AlbumActivity
import com.example.lunabee_proto.AlbumData
import com.example.lunabee_proto.R

class AlbumTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val imageView: ImageView = ImageView(context)
    private lateinit var data: AlbumData

    init {
        radius = 16f
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.lafeve_24)
        addView(imageView)
    }

    fun setImageResource(filename: String) {
        imageView.setImageResource(context.resources.getIdentifier(filename, "drawable", context.packageName))
    }

    fun setData(data: AlbumData) {
        this.data = data
        setImageResource(this.data.cover)
        imageView.setOnClickListener {
            val intent = Intent(context, AlbumActivity::class.java).apply {
                putExtra("ALBUM_TITLE", data.title)
                putExtra("ALBUM_ARTIST", data.artist)
                putExtra("ALBUM_YEAR", data.year)
                putExtra("ALBUM_COVER", data.cover)
            }
            context.startActivity(intent)
        }
    }
}