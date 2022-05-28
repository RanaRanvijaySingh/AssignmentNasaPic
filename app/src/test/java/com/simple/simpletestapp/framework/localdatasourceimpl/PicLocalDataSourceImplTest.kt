package com.simple.simpletestapp.framework.localdatasourceimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.simple.simpletestapp.MainCoroutineRule
import com.simple.simpletestapp.R
import com.simple.simpletestapp.TestHelper
import com.simple.simpletestapp.data.repositories.PicRepository
import com.simple.simpletestapp.domain.mappers.PicModelMapper
import com.simple.simpletestapp.usecases.GetPicUseCase
import com.simple.simpletestapp.utils.JsonReader
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PicLocalDataSourceImplTest {
    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var jsonReader: JsonReader
    val gson = GsonBuilder().create()

    private lateinit var dataSource: PicLocalDataSourceImpl
    private val picDbModel = TestHelper.picDbModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = PicLocalDataSourceImpl(jsonReader, gson)
    }

    @Test
    fun `test getPic for null`() = runBlocking {
        whenever(jsonReader.readJson(R.raw.data)).thenReturn(TestHelper.responseJson)
        val actual = dataSource.getPic()
        Assert.assertEquals(picDbModel, actual)
    }
}
