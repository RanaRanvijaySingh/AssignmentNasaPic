package com.simple.simpletestapp.framework.repositoriesimpl

import com.simple.simpletestapp.data.localdatasource.PicLocalDataSource
import com.simple.simpletestapp.data.remotedatasource.PicRemoteDataSource
import com.simple.simpletestapp.data.repositories.PicRepository
import com.simple.simpletestapp.domain.base.ResponseModel
import com.simple.simpletestapp.framework.network.NetworkCheck
import javax.inject.Inject

class PicsRepositoryImpl @Inject constructor(
    private val remoteDataSource: PicRemoteDataSource,
    private val localDataSource: PicLocalDataSource,
    private val networkCheck: NetworkCheck
) : PicRepository {

    /**
     * Function to get the data from API or local storage.
     *
     * @return [ResponseModel]
     */
    override suspend fun getPic(): ResponseModel? {
        return try {
            /**
             *  Check network and make the api call.
             *  If the network is not available then check if the data
             *  is not present then try local data source.
             *
             *  DISABLING THE API CALL FOR NOW
             */
            /*if (networkCheck.isNetworkAvailable()) {
                remoteDataSource.getPic()
            } else {
                localDataSource.getPic()
            }*/
            localDataSource.getPic()
        } catch (e: Exception) {
            localDataSource.getPic()
        }
    }
}