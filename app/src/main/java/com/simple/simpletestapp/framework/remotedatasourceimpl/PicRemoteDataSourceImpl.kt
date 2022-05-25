package com.simple.simpletestapp.framework.remotedatasourceimpl

import com.simple.simpletestapp.data.remotedatasource.PicRemoteDataSource
import com.simple.simpletestapp.domain.apimodels.ErrorApiModel
import com.simple.simpletestapp.domain.apimodels.PicApiModel
import com.simple.simpletestapp.domain.base.ResponseModel
import com.simple.simpletestapp.framework.network.ApiInterface
import javax.inject.Inject

class PicRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : PicRemoteDataSource {

    /**
     * Function to get the data from the api.
     *
     * @return [PicApiModel] or null
     */
    override suspend fun getPic(): ResponseModel? {
        val model = apiInterface.getRandomPics()
        return if (model.isSuccessful) {
            /**
             * Handle success response
             * For now since we do not have any api
             * we will return null.
             */
            null
        }else {
            // Check the error and return the error object instead of null
            ErrorApiModel("Something went wrong")
        }
    }
}