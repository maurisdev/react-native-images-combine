package com.reactlibraryimagescombine

import android.R.attr.src
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.facebook.react.bridge.*
import com.facebook.react.uimanager.IllegalViewOperationException
import image_cinema.ImageCinema
import java.net.HttpURLConnection
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

    private fun getBitmapFactoryOptions(): BitmapFactory.Options {
        val options = BitmapFactory.Options()
        options.inSampleSize = 3
        options.inJustDecodeBounds = true
        return options
    }

    private fun getBitmapsFromDrawables(inputImages: ReadableArray): List<Bitmap> {
        val options = getBitmapFactoryOptions()
        val result = arrayListOf<Bitmap>()
        for (i in 0 until inputImages.size()) {
            val inputImage = inputImages.getMap(i);
            val inputImageUri = inputImage?.getString("uri")!!;
            val url = URL(inputImageUri)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            val myBitmap = BitmapFactory.decodeStream(input)
            result.add(myBitmap)
        }
        return result
    }

}