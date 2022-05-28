package com.simple.simpletestapp.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.simple.simpletestapp.MainCoroutineRule
import com.simple.simpletestapp.TestHelper
import com.simple.simpletestapp.data.repositories.PicRepository
import com.simple.simpletestapp.domain.mappers.PicModelMapper
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.framework.repositoriesimpl.PicsRepositoryImpl
import com.simple.simpletestapp.presentation.viewmodels.MainViewModel
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
class GetPicUseCaseTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: PicRepository

    private lateinit var useCase: GetPicUseCase
    private val responseModel = TestHelper.picDbModel
    private val mapper = PicModelMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetPicUseCase(repository, mapper)
    }

    @Test
    fun `test getPic for null`() = runBlocking {
        whenever(repository.getPic()).thenReturn(null)
        val actual = useCase.getPic()
        Assert.assertEquals(null, actual)
    }

    @Test
    fun `test getPic for success`() = runBlocking {
        whenever(repository.getPic()).thenReturn(responseModel)
        val actual = useCase.getPic()
        Assert.assertEquals(TestHelper.picUiModelList.size, actual?.size)
    }
}
