package com.simple.simpletestapp.utils

import android.content.Context
import javax.inject.Inject

class JsonReader @Inject constructor(
    private val context: Context
) {

    fun readJson(resourceName: Int): String {
        return context.resources.openRawResource(resourceName)
            .bufferedReader().use { it.readText() }
    }
}
