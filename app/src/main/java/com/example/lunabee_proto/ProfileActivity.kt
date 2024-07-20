package com.example.lunabee_proto

import ArtistData
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp.AlbumTile
import com.example.myapp.ArtistTile

val favorite_albums = listOf(
    AlbumData("24", "La Feve", 2023, "lafeve_24", listOf("Track 1", "Track 2", "Track 3")),
    AlbumData("Malcolm", "Zed", 2024, "zed_malcolm", listOf("Track 1", "Track 2", "Track 3")),
    AlbumData("E-trap", "TH", 2024, "th_etrap", listOf("Track 1", "Track 2", "Track 3"))
)

val favorite_artists = listOf(
    ArtistData("La Feve", R.drawable.la_feve, 200),
    ArtistData("Mairo", R.drawable.mairo, 100),
    ArtistData("Kekra", R.drawable.kekra, 50)
)

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setFavoriteAlbums(favorite_albums)
        setFavoriteArtists(favorite_artists)
    }


    private fun setFavoriteAlbums(albums: List<AlbumData>) {
        val albumTileIds = arrayOf(
            R.id.album_tile_1,
            R.id.album_tile_2,
            R.id.album_tile_3
        )
        for ((i, id) in albumTileIds.withIndex()) {
            val view = findViewById<AlbumTile>(id)
            view.setData(albums[i])
        }
    }

    private fun setFavoriteArtists(artists: List<ArtistData>) {
        val artistTileIds = arrayOf(
            R.id.artist_tile_1,
            R.id.artist_tile_2,
            R.id.artist_tile_3
        )
        for ((i, id) in artistTileIds.withIndex()) {
            val view = findViewById<ArtistTile>(id)
            view.setData(artists[i])
        }
    }
}