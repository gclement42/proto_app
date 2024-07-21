package com.example.lunabee_proto

import AlbumData
import ArtistData
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

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
        val favoriteArtists = getFavoriteArtists()
        setFavoriteAlbums(getFavoriteAlbums())
        setFavoriteArtists(favoriteArtists)
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
        try {
            val artistTileIds = arrayOf(
                R.id.artist_tile_1,
                R.id.artist_tile_2,
                R.id.artist_tile_3
            )
            for ((i, id) in artistTileIds.withIndex()) {
                val artist = artists.getOrNull(i)
                if (artist != null) {
                    val view = findViewById<ArtistTile>(id)
                    view.setData(artist)
                } else {
                    Log.e("ProfileActivity", "No artist data available for index $i")
                }
            }
        } catch (e: Exception) {
            Log.e("ProfileActivity", "Error setting favorite artists", e)
        }
    }

    private fun getFavoriteArtists(): List<ArtistData> {
        val inputStream = resources.openRawResource(R.raw.artists)
        val reader = InputStreamReader(inputStream)
        val artistType = object : TypeToken<List<ArtistData>>() {}.type
        return Gson().fromJson(reader, artistType)
    }

    private fun getFavoriteAlbums(): List<AlbumData> {
        val inputStream = resources.openRawResource(R.raw.albums)
        val reader = InputStreamReader(inputStream)
        val albumType = object : com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken<List<AlbumData>>() {}.type
        return Gson().fromJson(reader, albumType)
    }
}
