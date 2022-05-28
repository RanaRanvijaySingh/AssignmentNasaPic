package com.simple.simpletestapp.presentation.views.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.idling.CountingIdlingResource
import com.simple.simpletestapp.MyApplication
import com.simple.simpletestapp.R
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import com.simple.simpletestapp.presentation.viewmodels.MainViewModel
import com.simple.simpletestapp.presentation.views.picdetail.PicDetailActivity
import com.simple.simpletestapp.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnPicClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var picsAdapter: PicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        initViews()
        initObservers()
        viewModel.getPic()
    }

    private fun initViews() {
        picsAdapter = PicsAdapter(this)
        rvPics.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = picsAdapter
        }
    }

    /**
     * Function to initialize all the observers on [LiveData]
     */
    private fun initObservers() {
        viewModel.picsLiveData.observe(this, { updateList(it) })
        viewModel.isLoadingLiveData.observe(this, { updateProgress(it) })
        viewModel.showErrorLiveData.observe(this, { showErrorResponse() })
    }

    private fun updateList(list: List<PicUiModel>?) {
        // add item in adapter
        picsAdapter.refreshList(list)
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

    override fun onPicClick(picModel: PicUiModel) {
        val intent = Intent(this, PicDetailActivity::class.java)
        intent.putExtra(Constants.Intent.IMAGE_URL, picModel.url)
        startActivity(intent)
    }

    fun onclickbutton(view: View){
        Toast.makeText(this, "Button click", Toast.LENGTH_LONG).show()
    }
}
