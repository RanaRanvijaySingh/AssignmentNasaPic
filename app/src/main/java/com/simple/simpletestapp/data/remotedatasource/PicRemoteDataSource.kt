package com.simple.simpletestapp.data.remotedatasource

import com.simple.simpletestapp.domain.base.ResponseModel

interface PicRemoteDataSource {
    suspend fun getPic(): ResponseModel?
}