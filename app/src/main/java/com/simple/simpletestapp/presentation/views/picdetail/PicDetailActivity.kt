package com.simple.simpletestapp.presentation.views.picdetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.simple.simpletestapp.MyApplication
import com.simple.simpletestapp.R
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.presentation.viewmodels.PicDetailViewModel
import com.simple.simpletestapp.utils.Constants
import kotlinx.android.synthetic.main.activity_pic_detail.*
import kotlinx.android.synthetic.main.item_pic_detail.*
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.*
import javax.inject.Inject

class PicDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PicDetailViewModel
    private lateinit var picViewPagerAdapter: PicViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic_detail)
        (application as MyApplication).getAppComponent().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[PicDetailViewModel::class.java]
        initViews()
        initObservers()
        val imageUrl = intent.getStringExtra(Constants.Intent.IMAGE_URL)
        viewModel.getPic(imageUrl)
    }

    private fun initViews() {
        ivBack.setOnClickListener { onBackPressed() }
        picViewPagerAdapter = PicViewPagerAdapter()
        viewpager.adapter = picViewPagerAdapter
        viewpager.setPageTransformer(ZoomInTransformation())
        viewpager.registerOnPageChangeCallback(onPageChangeCallBack)
    }

    private val onPageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
        }
    }

    /**
     * Function to initialize all the observers on [LiveData]
     */
    private fun initObservers() {
        viewModel.picsLiveData.observe(this, { updatePicList(it) })
        viewModel.isLoadingLiveData.observe(this, { updateProgress(it) })
        viewModel.showErrorLiveData.observe(this, { showErrorResponse() })
    }

    /**
     * Function to update the pic adapter with the new list.
     */
    private fun updatePicList(list: List<PicUiModel>?) {
        picViewPagerAdapter.refreshList(list)
    }

    /**
     * Function to update the visibility fo the progress bar.
     */
    private fun updateProgress(showProgress: Boolean) {
        pbGetPic.visibility = if (showProgress) View.VISIBLE else View.INVISIBLE
    }

    /**
     * Function to show error message in case the data is not received from server.
     */
    private fun showErrorResponse() {
        Toast.makeText(this, getString(R.string.unable_to_fetch_data), Toast.LENGTH_LONG).show()
    }
}
