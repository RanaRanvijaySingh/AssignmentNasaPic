package com.simple.simpletestapp

import com.google.gson.GsonBuilder
import com.simple.simpletestapp.domain.base.ResponseModel
import com.simple.simpletestapp.domain.dbmodels.PicDbModel
import com.simple.simpletestapp.domain.dbmodels.PicDetailDbModel
import com.simple.simpletestapp.domain.mappers.PicModelMapper
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.framework.remotedatasourceimpl.PicRemoteDataSourceImpl
import com.simple.simpletestapp.framework.repositoriesimpl.PicsRepositoryImpl
import com.simple.simpletestapp.presentation.viewmodels.MainViewModel
import com.simple.simpletestapp.usecases.GetPicUseCase

object TestHelper {

    private const val JSON_FILE_NAME = "data.json"
    lateinit var picDbModel: PicDbModel
    lateinit var picUiModelList: MutableList<PicUiModel>
    lateinit var responseJson: String
    lateinit var picUiModel: PicUiModel

    init {
        getListOfPicUiModel()
        picUiModel = picUiModelList[0]
    }

    /**
     * Function to get the list of [PicUiModel] from json saved in the resources.
     *
     * @return [MutableList<PicUiModel>]
     */
    private fun getListOfPicUiModel(): MutableList<PicUiModel> {
        val json = getJsonStringFromResource()
        val list = getPicDetailDbModelList(json)
        val responseModel = getPicDbModel(list)
        val listUiModel = getPicUiModelList(responseModel)
        listUiModel?.let {
            picUiModelList = it
            return picUiModelList
        }
        return mutableListOf()
    }

    /**
     * Function to get the list of [PicUiModel] from [PicDbModel]
     *
     * @return [MutableList<PicUiModel>]
     */
    private fun getPicUiModelList(responseModel: PicDbModel): MutableList<PicUiModel>? {
        return PicModelMapper().getPics(responseModel)
    }

    /**
     * Function to get the [PicDbModel] from [List<PicDetailDbModel>]
     *
     * @return [PicDbModel]
     */
    private fun getPicDbModel(list: List<PicDetailDbModel>): PicDbModel {
        picDbModel = PicDbModel(list)
        return picDbModel
    }

    /**
     * Function to get the list of [List<PicDetailDbModel>] from Json value
     *
     * @return [List<PicDetailDbModel>]
     */
    private fun getPicDetailDbModelList(json: String): List<PicDetailDbModel> {
        val gson = GsonBuilder().create()
        return gson.fromJson(json, Array<PicDetailDbModel>::class.java).asList()
    }

    /**
     * Function to get the list of [String] from resources.
     *
     * @return [String]
     */
    private fun getJsonStringFromResource(): String {
        responseJson = javaClass.classLoader.getResource(JSON_FILE_NAME).readText()
        return responseJson
    }
}
