package com.simple.simpletestapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.usecases.GetPicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: GetPicUseCase
) : ViewModel() {

    val picsLiveData = MutableLiveData<List<PicUiModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val showErrorLiveData = MutableLiveData<Boolean>()

    fun getPic() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                isLoadingLiveData.value = true
            }
            try {
                val listPics = useCase.getPic()
                if (listPics.isNullOrEmpty()) {
                    showErrorLiveData.postValue(true)
                } else {
                    listPics.let { picsLiveData.postValue(it) }
                }
            } catch (e: RuntimeException) {
                // Catching a general exception just in case. Don't want the app to crash.
                showErrorLiveData.postValue(true)
            }
            withContext(Dispatchers.Main) { isLoadingLiveData.postValue(false) }
        }
    }
}