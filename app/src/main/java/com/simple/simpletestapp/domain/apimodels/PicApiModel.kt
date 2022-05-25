package com.simple.simpletestapp.domain.apimodels

import com.google.gson.annotations.SerializedName
import com.simple.simpletestapp.domain.base.ResponseModel

data class PicApiModel(
    @SerializedName("copyright") val copyright: String? = null,
    @SerializedName("date") val date: String? = null,
    @SerializedName("explanation") val explanation: String? = null,
    @SerializedName("hdurl") val hdurl: String? = null,
    @SerializedName("media_type") val media_type: String? = null,
    @SerializedName("service_version") val service_version: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("url") val url: String? = null,
) : ResponseModel()
