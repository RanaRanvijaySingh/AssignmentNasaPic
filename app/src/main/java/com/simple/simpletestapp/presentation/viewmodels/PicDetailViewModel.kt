package com.simple.simpletestapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.usecases.GetPicUseCase
import com.simple.simpletestapp.utils.AppDispatchers
import com.simple.simpletestapp.utils.CommonUtil
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PicDetailViewModel @Inject constructor(
    private val useCase: GetPicUseCase,
    private val commonUtil: CommonUtil,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    val picsLiveData = MutableLiveData<List<PicUiModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val showErrorLiveData = MutableLiveData<Boolean>()

    /**
     * Function to get the pic from api and sort it based on received image url.
     * The list should contain the first item as the model of the selected image.
     */
    fun getPic(imageUrl: String?) {
        viewModelScope.launch {
            isLoadingLiveData.value = true
            try {
                val listPics = withContext(appDispatchers.IO) { useCase.getPic() }
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
            isLoadingLiveData.postValue(false)
        }
    }
}
