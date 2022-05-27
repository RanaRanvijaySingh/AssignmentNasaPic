package com.simple.simpletestapp.utils

import com.simple.simpletestapp.domain.uimodels.PicUiModel
import javax.inject.Inject

class CommonUtil @Inject constructor() {

    /**
     * Function to sort the list of [PicUiModel] in such a way
     * that model containing imageUrl should be places at the first
     * position.
     */
    fun getSortedPicList(
        list: MutableList<PicUiModel>,
        imageUrl: String?
    ): MutableList<PicUiModel> {
        if (imageUrl.isNullOrBlank()) return list
        // Find the model position with the image url
        for (item in list) {
            if (item.url.equals(imageUrl)) {
                val temp = item
                list.remove(item)
                list.add(0, temp)
                // Return the new list
                return list
            }
        }
        return list
    }
}
