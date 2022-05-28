package com.simple.simpletestapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.simple.simpletestapp.MainCoroutineRule
import com.simple.simpletestapp.TestHelper
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.usecases.GetPicUseCase
import com.simple.simpletestapp.utils.AppDispatchers
import com.simple.simpletestapp.utils.CommonUtil
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
class PicDetailViewModelTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var useCase: GetPicUseCase

    lateinit var viewModel: PicDetailViewModel
    lateinit var listPicUiModel: MutableList<PicUiModel>
    val commonUtil = CommonUtil()

    private val testDispatcher = AppDispatchers(
        IO = TestCoroutineDispatcher()
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = PicDetailViewModel(useCase, commonUtil, testDispatcher)
        listPicUiModel = TestHelper.picUiModelList
    }

    @Test
    fun `test status of showErrorMessage false case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(null)
        viewModel.getPic(null)
        Assert.assertEquals(true, viewModel.showErrorLiveData.value)
    }

    @Test
    fun `test status of showErrorMessage true case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(listPicUiModel)
        viewModel.getPic(null)
        Assert.assertEquals(null, viewModel.showErrorLiveData.value)
    }

    @Test
    fun `test status of isProgress false case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(null)
        viewModel.getPic(null)
        Assert.assertEquals(false, viewModel.isLoadingLiveData.value)
    }

    @Test
    fun `test getPic for null case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(null)
        viewModel.getPic(null)
        Assert.assertEquals(null, viewModel.picsLiveData.value)
    }

    @Test
    fun `test getPic for success case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(listPicUiModel)
        viewModel.getPic(null)
        Assert.assertEquals(listPicUiModel, viewModel.picsLiveData.value)
    }

    @Test
    fun `test getPic for failure case`() = runBlocking {
        whenever(useCase.getPic()).thenReturn(mutableListOf())
        viewModel.getPic(null)
        Assert.assertEquals(null, viewModel.picsLiveData.value?.size)
    }

    @Test
    fun `test getPic for second item selection`() = runBlocking {
        val secondItemUrl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
        whenever(useCase.getPic()).thenReturn(commonUtil.getSortedPicList(listPicUiModel, secondItemUrl))
        viewModel.getPic(secondItemUrl)
        val actual = viewModel.picsLiveData.value?.get(0)?.url
        Assert.assertEquals(secondItemUrl, actual)
    }

    @Test
    fun `test getPic for last item selection`() = runBlocking {
        val secondItemUrl = "https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao1024.jpg"
        whenever(useCase.getPic()).thenReturn(commonUtil.getSortedPicList(listPicUiModel, secondItemUrl))
        viewModel.getPic(secondItemUrl)
        val actual = viewModel.picsLiveData.value?.get(0)?.url
        Assert.assertEquals(secondItemUrl, actual)
    }
}