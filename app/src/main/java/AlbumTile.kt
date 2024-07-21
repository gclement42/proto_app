package com.example.lunabee_proto

import AlbumData
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.lunabee_proto.AlbumActivity
import com.example.lunabee_proto.R

class AlbumTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView = ImageView(context)
    private lateinit var data: AlbumData

    init {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.lafeve_24)
        addView(imageView)
    }

    private fun setImageResource(filename: String) {
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
                putStringArrayListExtra("ALBUM_TRACKLIST", ArrayList(data.tracklist))
            }
            context.startActivity(intent)
        }
    }
}