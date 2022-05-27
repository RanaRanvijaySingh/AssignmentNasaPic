package com.simple.simpletestapp.presentation.views.picdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.simple.simpletestapp.R
import com.simple.simpletestapp.domain.uimodels.PicUiModel

class PicViewPagerAdapter : RecyclerView.Adapter<PictureDetailViewHolder>() {

    private var list: List<PicUiModel> = ArrayList()
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureDetailViewHolder {
        mContext = parent.context
        return PictureDetailViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.item_pic_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PictureDetailViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title
        holder.tvSubtitle.text = list[position].explanation
        Glide.with(mContext)
            .load(list[position].hdurl)
            .centerCrop()
            .placeholder(R.drawable.progress_drawable)
            .into(holder.ivPic)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun refreshList(picList: List<PicUiModel>?) {
        picList?.let {
            list = it
            notifyDataSetChanged()
        }
    }
}

class PictureDetailViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    var tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)
    var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    var ivPic: ImageView = itemView.findViewById(R.id.ivPic)
    var ivIndicator: ImageView = itemView.findViewById(R.id.ivIndicator)
    var bottomSheet: ConstraintLayout = itemView.findViewById(R.id.bottomSheet)
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    init {
        initBottomSheet()
    }

    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Handle onSlide callbacks
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED ->
                        ivIndicator.setImageResource(R.drawable.ic_arrow_up)
                    BottomSheetBehavior.STATE_EXPANDED ->
                        ivIndicator.setImageResource(R.drawable.ic_arrow_down)
                }
            }
        })
    }
}