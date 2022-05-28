package com.simple.simpletestapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.simple.simpletestapp.MainCoroutineRule
import com.simple.simpletestapp.TestHelper
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.usecases.GetPicUseCase
import com.simple.simpletestapp.utils.AppDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var useCase: GetPicUseCase

    lateinit var viewModel: MainViewModel
    lateinit var listPicUiModel: MutableList<PicUiModel>

    private val testDispatcher = AppDispatchers(
        IO = TestCoroutineDispatcher()
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(useCase, testDispatcher)
        listPicUiModel = TestHelper.picUiModelList
    }

    @Test
    fun `test status of showErrorMessage false case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(null)
        viewModel.getPic()
        Assert.assertEquals(true, viewModel.showErrorLiveData.value)
    }

    @Test
    fun `test status of showErrorMessage true case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(listPicUiModel)
        viewModel.getPic()
        Assert.assertEquals(null, viewModel.showErrorLiveData.value)
    }

    @Test
    fun `test status of isProgress false case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(null)
        viewModel.getPic()
        Assert.assertEquals(false, viewModel.isLoadingLiveData.value)
    }

    @Test
    fun `test getPic for null case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(null)
        viewModel.getPic()
        Assert.assertEquals(null, viewModel.picsLiveData.value)
    }

    @Test
    fun `test getPic for success case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(listPicUiModel)
        viewModel.getPic()
        Assert.assertEquals(listPicUiModel, viewModel.picsLiveData.value)
    }

    @Test
    fun `test getPic for failure case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(mutableListOf())
        viewModel.getPic()
        Assert.assertEquals(null, viewModel.picsLiveData.value?.size)
    }
}
