package com.simple.simpletestapp.usecases

import com.simple.simpletestapp.data.repositories.PicRepository
import com.simple.simpletestapp.domain.mappers.PicModelMapper
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.utils.CommonUtil
import javax.inject.Inject

class GetPicUseCase @Inject constructor(
    private val repository: PicRepository,
    private val modelMapper: PicModelMapper
) {

    /**
     * Function to get the data from the [PicRepository].
     * It will get the data and convert to [PicUiModel]
     * using the [PicModelMapper]
     *
     * @return [PicUiModel] or null
     */
    suspend fun getPic(): MutableList<PicUiModel>? {
        val response = repository.getPic()
        return modelMapper.getPics(response)
    }
}