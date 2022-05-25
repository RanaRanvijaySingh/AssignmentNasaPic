package com.simple.simpletestapp.framework.network

interface NetworkCheck {
    suspend fun isNetworkAvailable(): Boolean
}