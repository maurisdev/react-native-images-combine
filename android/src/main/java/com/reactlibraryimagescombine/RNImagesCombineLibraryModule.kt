package com.reactlibraryimagescombine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import image_cinema.ImageCinema

class RNImagesCombineLibraryModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext)  {
    override fun getName(): String {
        return "RNImagesCombineLibrary"
    }

    @ReactMethod
    fun concat() {
        ImageCinema.concat(getInputBitmaps())
    }

    private fun getInputBitmaps(drawables: List<Int>): List<Bitmap> {
        return getBitmapsFromDrawables(
                arrayListOf(

                )
        )
    }

    private fun getBitmapFactoryOptions(): BitmapFactory.Options {
        val options = BitmapFactory.Options()
        options.inSampleSize = 3
        return options
    }


    private fun getBitmapsFromDrawables(drawables: List<Int>): List<Bitmap> {
        val options = getBitmapFactoryOptions()
        val result = arrayListOf<Bitmap>()
        //drawables.forEach { result.add(BitmapFactory.decodeResource(getResources(), it, options)) }
        return result
    }

}