package image_cinema

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream


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

    public fun concatAndConvertToBase64(list: List<Bitmap>): String? {
        val bitmap = concat(list) ?: return null
        return try {
            convertBitmapToBase64(bitmap)
        } catch (e: Throwable) {
            Log.e("ImageCinema", null, e)
            null
        }
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun convertBase64ToBitmap(b64: String): Bitmap? {
        val imageAsBytes =
                Base64.decode(b64.toByteArray(), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
    }
}