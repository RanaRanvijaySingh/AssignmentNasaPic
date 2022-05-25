package com.simple.simpletestapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.usecases.GetPicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: GetPicUseCase
) : ViewModel() {

    val picUiModel = MutableLiveData<PicUiModel>()
    val isLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()

    fun getPic() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
            }
            try {
                val uiModel = useCase.getPic()
                uiModel?.let { picUiModel.postValue(it) }
            } catch (e: Exception) {
                showError.postValue(true)
            }
            withContext(Dispatchers.Main) { isLoading.postValue(false) }
        }
    }
}