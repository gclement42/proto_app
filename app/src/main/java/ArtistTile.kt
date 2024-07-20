package com.example.myapp

import ArtistData
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.lunabee_proto.AlbumActivity
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.example.lunabee_proto.R

class ArtistTile @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val nameTextView: TextView
    private val followersTextView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.artist_tile, this, true)
        imageView = findViewById(R.id.artist_image) ?: throw IllegalArgumentException("ImageView not found")
        nameTextView = findViewById(R.id.artist_name) ?: throw IllegalArgumentException("TextView for artist name not found")
        followersTextView = findViewById(R.id.artist_followers) ?: throw IllegalArgumentException("TextView for artist followers not found")
    }

    fun setData(artist: ArtistData) {
        try {
            nameTextView.text = artist.name
            followersTextView.text = artist.followers.toString()
            Log.d("ArtistTile", "Data set for artist: ${artist.image}")
            val resId = context.resources.getIdentifier(artist.image, "drawable", context.packageName)
            if (resId != 0) {
                imageView.setImageResource(resId)
            } else {
                Log.e("ArtistTile", "Drawable resource not found for image: ${artist.image}")
                imageView.setImageResource(R.drawable.la_feve) // Fallback image
            }
        } catch (e: Exception) {
            Log.e("ArtistTile", "Error setting data", e)
        }
    }
}