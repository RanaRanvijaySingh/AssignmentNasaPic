package com.simple.simpletestapp.domain.mappers

import com.simple.simpletestapp.domain.apimodels.ErrorApiModel
import com.simple.simpletestapp.domain.dbmodels.PicDbModel
import com.simple.simpletestapp.domain.apimodels.PicApiModel
import com.simple.simpletestapp.domain.base.ResponseModel
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import javax.inject.Inject

class PicModelMapper @Inject constructor() {

    /**
     * Function to get convert the [PicDbModel] or [PicApiModel]
     * to [PicUiModel]
     *
     * @return [PicUiModel] or null
     */
    fun getPic(response: ResponseModel?): PicUiModel? {
        when (response) {
            is PicApiModel ->
                return getPicFromApiModel(response)

            is PicDbModel ->
                return getPicFromDbModel(response)

            is ErrorApiModel ->
                return PicUiModel()
        }
        return null
    }

    /**
     * Function to convert the response model to the UI Model.
     *
     * @return [PicApiModel] or null
     */
    private fun getPicFromApiModel(picApiModel: PicApiModel?): PicUiModel? {

        return null
    }

    /**
     * Function to convert the db model to the UI Model.
     *
     * @return [PicDbModel] or null
     */
    private fun getPicFromDbModel(picDbModel: PicDbModel?): PicUiModel? {

        return null
    }
}