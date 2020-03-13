package com.reactlibraryimagescombine

import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper
import com.facebook.react.bridge.*
import com.facebook.react.uimanager.IllegalViewOperationException
import image_cinema.CombineImages


class RNImagesCombineLibraryModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext)  {
    override fun getName(): String {
        return "RNImagesCombineLibrary"
    }

    @ReactMethod
    fun combineImages(
            inputImages: ReadableArray,
            promise: Promise) {
        try {
            val imageString = CombineImages.combineImageFromResources(reactApplicationContext.resources, geDrawablesId(inputImages))
            promise.resolve(imageString)
        } catch (e: IllegalViewOperationException) {
            promise.reject(null, e)
        }
    }

    private fun geDrawablesId(inputImages: ReadableArray): List<Int> {
        val result = arrayListOf<Int>()
        for (i in 0 until inputImages.size()) {
            val inputImage = inputImages.getMap(i);
            val inputImageUri = inputImage?.getString("uri")!!;
            val helper: ResourceDrawableIdHelper = ResourceDrawableIdHelper.getInstance()
            val imageId: Int = helper.getResourceDrawableId(reactApplicationContext, inputImageUri)!!
            result.add(imageId)
        }
        return result
    }

}