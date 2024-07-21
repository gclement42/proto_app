package com.example.lunabee_proto

import AlbumTileAdapter
import ListAdapter
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.InputStreamReader

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
        listRecyclerView.isNestedScrollingEnabled = false
        setRecyclerViewHeightBasedOnChildren(listRecyclerView)
    }

    private  fun setupCarousel() {
        carouselRecyclerView = findViewById(R.id.carouselRecyclerView)
        CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
        carouselRecyclerView.adapter = AlbumTileAdapter(getAlbums())
    }

    private fun getAlbums(): List<AlbumData> {
        val inputStream = resources.openRawResource(R.raw.albums)
        val reader = InputStreamReader(inputStream)
        val albumType = object : TypeToken<List<AlbumData>>() {}.type
        return Gson().fromJson(reader, albumType)
    }

    private fun setRecyclerViewHeightBasedOnChildren(recyclerView: RecyclerView) {
        val adapter = recyclerView.adapter ?: return
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(recyclerView.width, View.MeasureSpec.UNSPECIFIED)

        for (i in 0 until adapter.itemCount) {
            val listItem = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i)).itemView
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += listItem.measuredHeight
        }

        val params = recyclerView.layoutParams
        params.height = totalHeight
        recyclerView.layoutParams = params
    }
}