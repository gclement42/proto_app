import android.view.View
import androidx.viewpager.widget.ViewPager

class MarginPageTransformer(private val marginPixels: Int) : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val offset = position * marginPixels
        view.translationX = -offset
    }
}