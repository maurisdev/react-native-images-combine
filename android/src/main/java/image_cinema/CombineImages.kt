package image_cinema

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.annotation.DrawableRes

object CombineImages {

    fun combineImageFromUrls(list: List<String>): String? {
        if (list.isNullOrEmpty()) return null

        return try {
            val result = getBitmapFromUrl(list.first()).copy(Bitmap.Config.ARGB_8888, true)
            val canvas = Canvas(result)
            list.forEachIndexed { index, url ->
                if (index != 0) {
                    var bitmap = getBitmapFromUrl(url)
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

    private fun getBitmapFromUrl(url: String) = BitmapFactory.decodeFile(url, bitmapOptions)

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

    private val bitmapOptions = BitmapFactory.Options().apply {
        inSampleSize = 1
    }

    fun combineImageFromResources(resources: Resources, list: List<Int>): String? {
        if (list.isNullOrEmpty()) return null

        return try {
            val result =
                getBitmapFromResource(resources, list.first()).copy(Bitmap.Config.ARGB_8888, true)
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

    private fun getBitmapFromResource(resources: Resources, @DrawableRes id: Int) =
        BitmapFactory.decodeResource(resources, id, bitmapOptions)
}