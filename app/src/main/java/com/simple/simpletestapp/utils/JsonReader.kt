package com.simple.simpletestapp.utils

import android.content.Context
import javax.inject.Inject

class JsonReader @Inject constructor(
    private val context: Context
) {

    /**
     * Function to read the json data saved at the raw location.
     *
     * @return [String]
     */
    fun readJson(resourceName: Int): String {
        return context.resources.openRawResource(resourceName)
            .bufferedReader().use { it.readText() }
    }
}
