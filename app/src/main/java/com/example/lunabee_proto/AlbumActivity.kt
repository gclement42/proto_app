package com.example.lunabee_proto

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val albumTitle = intent.getStringExtra("ALBUM_TITLE")
        val albumArtist = intent.getStringExtra("ALBUM_ARTIST")
        val albumYear = intent.getIntExtra("ALBUM_YEAR", -1)
        val albumCover = intent.getIntExtra("ALBUM_COVER", -1)

        findViewById<TextView>(R.id.album_title).text = albumTitle
        findViewById<TextView>(R.id.album_artist).text = albumArtist
        findViewById<TextView>(R.id.album_year).text = albumYear.toString()
        findViewById<ImageView>(R.id.album_cover).setImageResource(albumCover)
        setTracklist(getTracklist())
    }

    private fun getTracklist(): List<String> {
        return listOf(
            "Track 1","Track 2","Track 3","Track 4","Track 5","Track 6", "Track 7"
        )
    }

    fun setTracklist(tracklist: List<String>) {
        val tracklistView = findViewById<ListView>(R.id.album_tracklist)
        tracklistView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tracklist)

    }
}