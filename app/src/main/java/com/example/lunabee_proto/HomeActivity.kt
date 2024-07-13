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

class HomeActivity : AppCompatActivity() {

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
        setupCarousel()
        val listRecyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        val items = listOf(
            AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24),
            AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24),
            AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24),
            AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24),
            AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24),
            AlbumData("24", "La Feve", 2023, R.drawable.lafeve_24),
        )

        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = ListAdapter(items)

        val carouselRecyclerView = findViewById<RecyclerView>(R.id.carouselRecyclerView)
    }

    private  fun setupCarousel() {
        carouselRecyclerView = findViewById(R.id.carouselRecyclerView)
        CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
        carouselRecyclerView.adapter = AlbumTileAdapter(getImages())
    }

    private fun getImages(): List<Int> {
        return listOf(
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
            R.drawable.lafeve_24,
        )
    }
}