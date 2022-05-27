package com.simple.simpletestapp.domain.mappers

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
    fun getPics(response: ResponseModel?): MutableList<PicUiModel>? {
        when (response) {
            is PicApiModel ->
                return getPicsFromApiModel(response)

            is PicDbModel ->
                return getPicsFromDbModel(response)
        }
        return null
    }

    /**
     * Function to convert the response model to the UI Model.
     *
     * @return [PicApiModel] or null
     */
    private fun getPicsFromApiModel(picApiModel: PicApiModel?): MutableList<PicUiModel>? {
        /**
         * Mapping logic to convert the api model class to ui model class.
         */
        return null
    }

    /**
     * Function to convert the db model to the UI Model.
     *
     * @return [PicDbModel] or null
     */
    private fun getPicsFromDbModel(picDbModel: PicDbModel?): MutableList<PicUiModel> {
        val list = ArrayList<PicUiModel>()
        picDbModel?.let {
            for (i in it.pics) {
                val model = PicUiModel(
                    copyright = i.copyright,
                    date = i.date,
                    explanation = i.explanation,
                    hdurl = i.hdurl,
                    title = i.title,
                    url = i.url
                )
                list.add(model)
            }
        }
        return list
    }
}