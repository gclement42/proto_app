package com.example.lunabee_proto

import ArtistData
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class AlbumActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_album)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupAvatarClick()
        setAlbumInfo()
    }

    private fun setTracklist(tracklist: List<String>) {
        val tracklistView = findViewById<ListView>(R.id.album_tracklist)
        tracklistView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tracklist)
    }

    private fun getAllArtistsData(): List<ArtistData> {
        val inputStream = resources.openRawResource(R.raw.artists)
        val reader = InputStreamReader(inputStream)
        val artistType = object : TypeToken<List<ArtistData>>() {}.type
        return Gson().fromJson(reader, artistType)
    }

    private fun getArtistData(artistName: String): ArtistData? {
        val artistsData = getAllArtistsData()
        return artistsData.find { it.name == artistName }
    }

    private fun setArtistTile(artistName: String) {
        val artistTile = findViewById<ArtistTile>(R.id.album_artist_avatar)
        val artistData = getArtistData(artistName)

        if (artistData != null) {
            artistTile.setData(artistData)
        } else {
            Log.e("AlbumActivity", "Artist not found: $artistName")
            artistTile.setData(ArtistData(name = "Unknown Artist", image = "la_feve", followers = 0, topSongs = listOf(), albums = listOf()))
        }
    }

    private fun setBackground(coverId: Int) {
        val headerView = findViewById<View>(R.id.album_top)

        Glide.with(this)
            .asBitmap()
            .load(coverId)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                    Palette.from(resource).generate { palette ->
                        val dominantColor = palette?.getDominantColor(ContextCompat.getColor(this@AlbumActivity, R.color.backgroundAlbum))
                        val layerDrawable = ContextCompat.getDrawable(this@AlbumActivity, R.drawable.default_background) as LayerDrawable
                        val gradientDrawable = layerDrawable.getDrawable(0) as GradientDrawable
                        val middleColor = blendColors(dominantColor ?: ContextCompat.getColor(this@AlbumActivity, R.color.backgroundAlbum),
                            ContextCompat.getColor(this@AlbumActivity, android.R.color.white),
                            0.7F)
                        gradientDrawable.colors = intArrayOf(
                            dominantColor ?: ContextCompat.getColor(this@AlbumActivity, R.color.backgroundAlbum),
                            middleColor,
                            ContextCompat.getColor(this@AlbumActivity, android.R.color.white)
                        )

                        headerView.background = layerDrawable
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Optionnel : gérer le cas où l'image est effacée
                }
            })
    }

    private fun blendColors(color1: Int, color2: Int, ratio: Float): Int {
        val inverseRatio = 1 - ratio
        val red = (Color.red(color1) * ratio + Color.red(color2) * inverseRatio).toInt()
        val green = (Color.green(color1) * ratio + Color.green(color2) * inverseRatio).toInt()
        val blue = (Color.blue(color1) * ratio + Color.blue(color2) * inverseRatio).toInt()
        return Color.rgb(red, green, blue)
    }


    private fun setAlbumInfo() {
        val albumTitle = intent.getStringExtra("ALBUM_TITLE")
        val albumArtist= intent.getStringExtra("ALBUM_ARTIST")
        val albumYear = intent.getIntExtra("ALBUM_YEAR", -1)
        val albumCover = intent.getStringExtra("ALBUM_COVER")
        val albumTracklist = intent.getStringArrayListExtra("ALBUM_TRACKLIST")

        if (albumArtist != null)
            setArtistTile(albumArtist)
        findViewById<TextView>(R.id.album_title).text = albumTitle
        findViewById<TextView>(R.id.album_artist_name).text = albumArtist
        findViewById<TextView>(R.id.album_year).text = albumYear.toString()
        val cover = findViewById<ImageView>(R.id.album_cover)
        val imgId = resources.getIdentifier(albumCover, "drawable", packageName)
        cover.setImageResource(imgId)
        albumTracklist?.let { setTracklist(it.toList()) }
        if (albumCover != null)
            setBackground(imgId)
    }
}