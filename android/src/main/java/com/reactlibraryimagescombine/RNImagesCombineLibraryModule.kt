package com.reactlibraryimagescombine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper
import com.facebook.react.bridge.*
import com.facebook.react.uimanager.IllegalViewOperationException
import image_cinema.ImageCinema
import java.io.IOException
import java.net.URL


class RNImagesCombineLibraryModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext)  {
    override fun getName(): String {
        return "RNImagesCombineLibrary"
    }

    @ReactMethod
    fun combineImages(
            inputImages: ReadableArray,
            promise: Promise) {
        try {
            val imageString = ImageCinema.concatAndConvertToBase64(getBitmapsFromDrawables(inputImages))
            promise.resolve(imageString)
        } catch (e: IllegalViewOperationException) {
            promise.reject(null, e)
        }
    }

    private fun getBitmapsFromDrawables(inputImages: ReadableArray): List<Bitmap> {
        val result = arrayListOf<Bitmap>()
        for (i in 0 until inputImages.size()) {
            val inputImage = inputImages.getMap(i);
            val inputImageUri = inputImage?.getString("uri")!!;
            result.add(getBitmap(inputImageUri)!!)
        }

        return result
    }

    private fun getBitmap(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        try { // If we are running the app in debug mode, the "local" image will be served from htt://localhost:8080
            if (!url.startsWith("http")) { // Gets the drawable from the RN's helper for local resources
                val helper: ResourceDrawableIdHelper = ResourceDrawableIdHelper.getInstance()
                val image: Drawable = helper.getResourceDrawable(reactApplicationContext, url)!!
                bitmap = if (image is BitmapDrawable) {
                    image.bitmap
                } else {
                    BitmapFactory.decodeFile(url)
                }
            } else { // Open connection to the URL and decodes the image
                val con = URL(url).openConnection()
                con.connect()
                val input = con.getInputStream()
                bitmap = BitmapFactory.decodeStream(input)
                input.close()
            }
        } catch (ex: IOException) {
            Log.w("ImageCombineLibrary", "Could not load the image", ex)
        }
        return bitmap
    }

}