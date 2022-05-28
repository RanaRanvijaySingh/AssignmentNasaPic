package com.simple.simpletestapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.usecases.GetPicUseCase
import com.simple.simpletestapp.utils.AppDispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: GetPicUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    val picsLiveData = MutableLiveData<List<PicUiModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val showErrorLiveData = MutableLiveData<Boolean>()

    fun getPic() {
        viewModelScope.launch {
            isLoadingLiveData.value = true
            try {
                val listPics = withContext(appDispatchers.IO) { useCase.getPic() }
                if (listPics.isNullOrEmpty()) {
                    showErrorLiveData.postValue(true)
                } else {
                    listPics.let { picsLiveData.postValue(it) }
                }
            } catch (e: RuntimeException) {
                // Catching a general exception just in case. Don't want the app to crash.
                showErrorLiveData.postValue(true)
            }
            isLoadingLiveData.postValue(false)
        }
    }
}