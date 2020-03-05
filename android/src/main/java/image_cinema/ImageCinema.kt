package image_cinema

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log

object ImageCinema {

    public fun concat(list: List<Bitmap>): Bitmap? {
        if (list.isNullOrEmpty()) return null;

        return try {
            val result = list.first().copy(Bitmap.Config.ARGB_8888, true)
            val canvas = Canvas(result)
            list.forEachIndexed { index, bitmap ->
                if (index != 0) canvas.drawBitmap(bitmap, 0f, 0f, null)
            }
            result
        } catch (e: Throwable) {
            Log.e("ImageCinema", null, e)
            null
        }
    }
}