package com.simple.simpletestapp.di

import com.google.gson.Gson
import com.simple.simpletestapp.MyApplication
import com.simple.simpletestapp.data.localdatasource.PicLocalDataSource
import com.simple.simpletestapp.data.remotedatasource.PicRemoteDataSource
import com.simple.simpletestapp.data.repositories.PicRepository
import com.simple.simpletestapp.framework.localdatasourceimpl.PicLocalDataSourceImpl
import com.simple.simpletestapp.framework.network.ApiInterface
import com.simple.simpletestapp.framework.network.NetworkCheck
import com.simple.simpletestapp.framework.network.NetworkCheckImpl
import com.simple.simpletestapp.framework.remotedatasourceimpl.PicRemoteDataSourceImpl
import com.simple.simpletestapp.framework.repositoriesimpl.PicsRepositoryImpl
import com.simple.simpletestapp.utils.JsonReader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    @Singleton
    fun providesPicRemoteDataSource(apiInterface: ApiInterface): PicRemoteDataSource {
        return PicRemoteDataSourceImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun providesNetworkCheck(application: MyApplication): NetworkCheck {
        return NetworkCheckImpl(application)
    }

    @Provides
    @Singleton
    fun providesPicLocalDataSource(jsonReader: JsonReader, gson: Gson): PicLocalDataSource {
        return PicLocalDataSourceImpl(jsonReader, gson)
    }

    @Provides
    @Singleton
    fun providesPicRepository(
        remoteDataSource: PicRemoteDataSource,
        localDataSource: PicLocalDataSource,
        networkCheck: NetworkCheck
    ): PicRepository {
        return PicsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            networkCheck = networkCheck
        )
    }
}