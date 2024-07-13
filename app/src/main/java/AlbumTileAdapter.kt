import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.lunabee_proto.R
import com.example.myapp.AlbumTile

class AlbumTileAdapter(private val images: List<Int>):
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

    override fun onBindViewHolder(holder: AlbumTileAdapter.CarouselViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
