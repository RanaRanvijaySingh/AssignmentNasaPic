package com.simple.simpletestapp.data.repositories

import com.simple.simpletestapp.domain.base.ResponseModel

interface PicRepository {
    suspend fun getPic(): ResponseModel?
}