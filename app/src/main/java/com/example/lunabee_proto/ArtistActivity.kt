package com.example.lunabee_proto

import AlbumTileAdapter
import ListAdapter
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.InputStreamReader

class ArtistActivity : BaseActivity() {
    private lateinit var carouselRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artist)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val artistName = intent.getStringExtra("ARTIST_NAME")
        setupAvatarClick()
        setupCarousel()
    }

    private  fun setupCarousel() {
        carouselRecyclerView = findViewById(R.id.artist_releases)
        CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
        carouselRecyclerView.adapter = AlbumTileAdapter(getAlbums())
    }

    private fun getAlbums(): List<AlbumData> {
        val inputStream = resources.openRawResource(R.raw.albums)
        val reader = InputStreamReader(inputStream)
        val albumType = object : TypeToken<List<AlbumData>>() {}.type
        return Gson().fromJson(reader, albumType)
    }
}