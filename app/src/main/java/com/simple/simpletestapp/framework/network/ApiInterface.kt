package com.simple.simpletestapp.framework.network

import com.simple.simpletestapp.utils.Constants
import com.simple.simpletestapp.domain.apimodels.PicApiModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(Constants.APIS.PICS)
    suspend fun getRandomPics(): Response<List<PicApiModel>>
}