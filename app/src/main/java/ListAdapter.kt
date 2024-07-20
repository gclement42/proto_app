import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lunabee_proto.AlbumActivity
import com.example.lunabee_proto.AlbumData
import com.example.lunabee_proto.R

class ListAdapter(private val albums: List<AlbumData>):
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.itemTitle)
        val artist: TextView = itemView.findViewById(R.id.itemArtist)
        val cover: ImageView = itemView.findViewById(R.id.itemCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = albums[position].title
        holder.artist.text = albums[position].artist
        holder.cover.setImageResource(
            holder.itemView.context.resources.getIdentifier(
                albums[position].cover, "drawable", holder.itemView.context.packageName))
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

    override fun getItemCount(): Int = albums.size
}