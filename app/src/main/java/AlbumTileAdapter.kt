import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.lunabee_proto.R
import com.example.myapp.AlbumTile

class AlbumTileAdapter(private val context: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val albumTile = AlbumTile(context)
        albumTile.setImageResource(R.drawable.lafeve_24)
        container.addView(albumTile)
        return albumTile
    }

    override fun getCount(): Int {
        return 5
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
