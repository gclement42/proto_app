import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.lunabee_proto.ArtistActivity
import com.example.lunabee_proto.R

class ArtistTile @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val nameTextView: TextView
    private val followersTextView: TextView

    init {
        radius = 150f

        LayoutInflater.from(context).inflate(R.layout.artist_tile, this, true)
        imageView = findViewById(R.id.artist_image) ?: throw IllegalArgumentException("ImageView for artist image not found")
        nameTextView = findViewById(R.id.artist_name) ?: throw IllegalArgumentException("TextView for artist name not found")
        followersTextView = findViewById(R.id.artist_followers) ?: throw IllegalArgumentException("TextView for artist followers not found")
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    fun setData(artist: ArtistData) {
        try {
            val resId = context.resources.getIdentifier(artist.image, "drawable", context.packageName)
            if (resId != 0) {
                imageView.setImageResource(resId)
            } else {
                Log.e("ArtistTile", "Drawable resource not found for image: ${artist.image}")
                imageView.setImageResource(R.drawable.la_feve) // Fallback image
            }
        } catch (e: Exception) {
            Log.e("ArtistTile", "Error setting data", e)
        }
        imageView.setOnClickListener {
            Log.d("ArtistTile", "Artist image clicked ${ArrayList(artist.topSongs)}")
            val intent = Intent(context, ArtistActivity::class.java).apply {
                putExtra("ARTIST_NAME", artist.name)
                putExtra("ARTIST_IMAGE", artist.image)
                putExtra("ARTIST_FOLLOWERS", artist.followers)
                putStringArrayListExtra("ARTIST_TOP_SONGS", ArrayList(artist.topSongs))
                putStringArrayListExtra("ARTIST_ALBUMS", ArrayList(artist.albums))
            }
            context.startActivity(intent)
        }
    }
}