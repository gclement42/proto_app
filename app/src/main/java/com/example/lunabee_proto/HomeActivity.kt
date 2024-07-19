package com.example.lunabee_proto

import AlbumData
import AlbumTileAdapter
import ListAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.carousel.CarouselSnapHelper

class HomeActivity : BaseActivity() {

    private lateinit var carouselRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupAvatarClick()
        setupCarousel()
        val listRecyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = ListAdapter(getAlbums())
    }

    private  fun setupCarousel() {
        carouselRecyclerView = findViewById(R.id.carouselRecyclerView)
        CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
        carouselRecyclerView.adapter = AlbumTileAdapter(getAlbums())
    }

    private fun getAlbums(): List<AlbumData> {
        return listOf(
            AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24),
            AlbumData("E-trap", "TH", 2024, R.drawable.th_etrap),
            AlbumData("Malcolm", "Zed", 2024, R.drawable.zed_malcolm),
            AlbumData("Recherche & Destruction", "Jolagreen23", 2023, R.drawable.jolagreen23_rd),
            AlbumData("Bitume Caviar (Vol 1)", "Isha & Limsa d'Aulnay", 2023, R.drawable.isha_limsa_bitume_caviar),
            AlbumData("Saudade", "Green Montana", 2024, R.drawable.green_montana_saudade),
            AlbumData("Saudade", "Green Montana", 2024, R.drawable.green_montana_saudade)
        )
    }
}