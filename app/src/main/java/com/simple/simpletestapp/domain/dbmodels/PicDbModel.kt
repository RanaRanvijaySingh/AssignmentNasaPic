package com.simple.simpletestapp.domain.dbmodels

import com.simple.simpletestapp.domain.base.ResponseModel

data class PicDbModel(
    val pics: List<PicDetailDbModel>
) : ResponseModel()

data class PicDetailDbModel(
    val copyright: String? = null,
    val date: String? = null,
    val explanation: String? = null,
    val hdurl: String? = null,
    val media_type: String? = null,
    val service_version: String? = null,
    val title: String? = null,
    val url: String? = null,
) : ResponseModel()