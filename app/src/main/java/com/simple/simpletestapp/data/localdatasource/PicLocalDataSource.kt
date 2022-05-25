package com.simple.simpletestapp.data.localdatasource

import com.simple.simpletestapp.domain.dbmodels.PicDbModel

interface PicLocalDataSource {
    suspend fun getPic(): PicDbModel
}