package image_cinema

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Log
import androidx.annotation.DrawableRes
import java.net.URL

object CombineImages {

    fun combineImageFromUrls(list: List<String>): String? {
        if (list.isNullOrEmpty()) return null

        return try {
            val result = getBitmapFromUrl(list.first())?.copy(Bitmap.Config.ARGB_8888, true)
            val canvas = result?.let { Canvas(it) }
            list.forEachIndexed { index, url ->
                if (index != 0) {
                    var bitmap = getBitmapFromUrl(url)
                    bitmap.let {
                        if (it != null) {
                            canvas?.drawBitmap(it, 0f, 0f, null)
                        }
                    }
                    bitmap?.recycle()
                    bitmap = null
                }
            }
            Converter.convertBitmapToBase64(result!!)
        } catch (e: Throwable) {
            Log.e("ImageCinema", null, e)
            null
        }
    }

    private fun getBitmapFromUrl(url: String): Bitmap? {
        if (url.startsWith("data")) {
            val decodedString = Base64.decode(url.substring(url.indexOf(',') + 1), Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.count())
        }

        val con = URL(url).openConnection()
        con.connect()
        val input = con.getInputStream()
        val bitmap = BitmapFactory.decodeStream(input)
        input.close()
        return bitmap
    }

    fun combineImageFromBitmapDrawables(list: List<BitmapDrawable>): String? {
        if (list.isNullOrEmpty()) return null

        return try {
            val result =
                    getBitmapFromBitmapDrawable(list.first()).copy(Bitmap.Config.ARGB_8888, true)
            val canvas = Canvas(result)
            list.forEachIndexed { index, url ->
                if (index != 0) {
                    var bitmap = getBitmapFromBitmapDrawable(url)
                    bitmap.let { canvas.drawBitmap(it, 0f, 0f, null) }
                    bitmap.recycle()
                    bitmap = null
                }
            }
            Converter.convertBitmapToBase64(result)
        } catch (e: Throwable) {
            Log.e("ImageCinema", null, e)
            null
        }
    }

    private fun getBitmapFromBitmapDrawable(drawable: BitmapDrawable) = drawable.bitmap

    fun combineImageFromResources(resources: Resources, list: List<Int>): String? {
        if (list.isNullOrEmpty()) return null

        return try {
            val result = getBitmapFromResourceAsMutable(resources, list.first())
            val canvas = Canvas(result)
            list.forEachIndexed { index, url ->
                if (index != 0) {
                    var bitmap = getBitmapFromResource(resources, url)
                    bitmap.let { canvas.drawBitmap(it, 0f, 0f, null) }
                    bitmap.recycle()
                    bitmap = null
                }
            }
            Converter.convertBitmapToBase64(result)
        } catch (e: Throwable) {
            Log.e("ImageCinema", null, e)
            null
        }
    }

    private val bitmapOptions = BitmapFactory.Options().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            outConfig = Bitmap.Config.HARDWARE
        }
        inSampleSize = 1
        inScaled = false
        inMutable = false
    }

    private val bitmapOptionsAsMutable = BitmapFactory.Options().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            outConfig = Bitmap.Config.HARDWARE
        }
        inSampleSize = 1
        inScaled = false
        inMutable = true
    }

    private fun getBitmapFromResource(resources: Resources, @DrawableRes id: Int) =
            BitmapFactory.decodeResource(resources, id, bitmapOptions)

    private fun getBitmapFromResourceAsMutable(resources: Resources, @DrawableRes id: Int) =
            BitmapFactory.decodeResource(resources, id, bitmapOptionsAsMutable)
}