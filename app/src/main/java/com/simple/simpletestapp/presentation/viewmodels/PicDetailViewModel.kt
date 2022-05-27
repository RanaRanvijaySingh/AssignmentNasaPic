package com.simple.simpletestapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.usecases.GetPicUseCase
import com.simple.simpletestapp.utils.CommonUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import javax.inject.Inject

class PicDetailViewModel @Inject constructor(
    private val useCase: GetPicUseCase,
    private val commonUtil: CommonUtil
) : ViewModel() {

    val picsLiveData = MutableLiveData<List<PicUiModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val showErrorLiveData = MutableLiveData<Boolean>()

    /**
     * Function to get the pic from api and sort it based on received image url.
     * The list should contain the first item as the model of the selected image.
     */
    fun getPic(imageUrl: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                isLoadingLiveData.value = true
            }
            try {
                val listPics = useCase.getPic()
                if (listPics.isNullOrEmpty()) {
                    showErrorLiveData.postValue(true)
                } else {
                    val sortedPicList = commonUtil.getSortedPicList(listPics, imageUrl)
                    picsLiveData.postValue(sortedPicList)
                }
            } catch (e: RuntimeException) {
                // Catching a general exception just in case. Don't want the app to crash.
                showErrorLiveData.postValue(true)
            }
            withContext(Dispatchers.Main) { isLoadingLiveData.postValue(false) }
        }
    }
}
