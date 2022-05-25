package com.simple.simpletestapp.framework.localdatasourceimpl

import com.google.gson.Gson
import com.simple.simpletestapp.R
import com.simple.simpletestapp.data.localdatasource.PicLocalDataSource
import com.simple.simpletestapp.domain.dbmodels.PicDbModel
import com.simple.simpletestapp.domain.dbmodels.PicDetailDbModel
import com.simple.simpletestapp.utils.JsonReader
import javax.inject.Inject

class PicLocalDataSourceImpl @Inject constructor(
    private val jsonReader: JsonReader,
    private val gson: Gson
) : PicLocalDataSource {

    /**
     * Function to fetch data from the DB or Android local storage
     * or SharedPreferences.
     *
     * @return [PicDbModel] Keeping it hardcoded for now.
     */
    override suspend fun getPic(): PicDbModel {
        // Read JSON data from the json file using [JsonReader]
        val json = jsonReader.readJson(R.raw.data)
        // Convert the json string to model using Gson
        val list = gson.fromJson(json, Array<PicDetailDbModel>::class.java).asList()
        return PicDbModel(list)
    }
}