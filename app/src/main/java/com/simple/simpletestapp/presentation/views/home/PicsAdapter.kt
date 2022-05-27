package com.simple.simpletestapp.presentation.views.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.simple.simpletestapp.R
import com.simple.simpletestapp.domain.uimodels.PicUiModel

class PicsAdapter(private val listener: OnPicClickListener) : RecyclerView.Adapter<PicViewHolder>(),
    OnPicClickListener {

    private var list: List<PicUiModel> = ArrayList()
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        mContext = parent.context
        return PicViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.item_pic, null), this
        )
    }

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        holder.model = list[position]
        Glide.with(mContext)
            .load(list[position].url)
            .transform(CenterCrop(), RoundedCorners(24))
            .placeholder(R.drawable.progress_drawable)
            .into(holder.ivPic)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onPicClick(picModel: PicUiModel) {
        listener.onPicClick(picModel)
    }

    fun refreshList(picList: List<PicUiModel>?) {
        picList?.let {
            list = it
            notifyDataSetChanged()
        }
    }
}