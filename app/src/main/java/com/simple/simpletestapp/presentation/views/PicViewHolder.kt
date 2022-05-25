package com.simple.simpletestapp.presentation.views

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.simple.simpletestapp.R
import com.simple.simpletestapp.domain.uimodels.PicUiModel

class PicViewHolder(itemView: View, private val listener: OnPicClickListener) :
    RecyclerView.ViewHolder(itemView) {

    var ivPic: ImageView = itemView.findViewById(R.id.ivPic)
    lateinit var model: PicUiModel

    init {
        ivPic.setOnClickListener {
            listener.onPicClick(model)
        }
    }


}

interface OnPicClickListener {
    fun onPicClick(picModel: PicUiModel)
}