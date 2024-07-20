import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.lunabee_proto.AlbumActivity
import com.example.lunabee_proto.AlbumData
import com.example.lunabee_proto.R
import com.example.myapp.AlbumTile

class AlbumTileAdapter(private val albums: List<AlbumData>):
    RecyclerView.Adapter<AlbumTileAdapter.CarouselViewHolder>() {


    inner class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val carouselImageView: AppCompatImageView =
            view.findViewById(R.id.carouselImageView)

        fun bind(imageResource: Int) {
            carouselImageView.setImageResource(imageResource)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            AlbumTileAdapter.CarouselViewHolder {
        return CarouselViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.album_tile, parent, false
            )
        )
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: AlbumTileAdapter.CarouselViewHolder, position: Int) {
        val albumCover = holder.itemView.findViewById<AppCompatImageView>(R.id.carouselImageView)
        Glide.with(holder.itemView.context)
            .load(holder.itemView.context.resources.getIdentifier(albums[position].cover, "drawable", holder.itemView.context.packageName))
            .into(albumCover)
        Log.d("AlbumTileAdapter", "albums[position].getName(): ${albums[position]}")
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AlbumActivity::class.java).apply {
                putExtra("ALBUM_TITLE", albums[position].title)
                putExtra("ALBUM_ARTIST", albums[position].artist)
                putExtra("ALBUM_YEAR", albums[position].year)
                putExtra("ALBUM_COVER", albums[position].cover)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}
