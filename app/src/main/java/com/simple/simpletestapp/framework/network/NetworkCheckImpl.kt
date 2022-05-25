package com.simple.simpletestapp.framework.network

import android.content.Context

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.simple.simpletestapp.MyApplication
import javax.inject.Inject

class NetworkCheckImpl @Inject constructor(private val application: MyApplication) : NetworkCheck {

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}