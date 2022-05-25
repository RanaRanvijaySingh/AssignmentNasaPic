package com.simple.simpletestapp.presentation.views

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.simple.simpletestapp.MyApplication
import com.simple.simpletestapp.R
import com.simple.simpletestapp.presentation.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).getAppComponent().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        initObservers()
    }

    /**
     * Function to get the [LinearLayout.LayoutParams] to set the height of the [GLSurfaceView]
     *
     * @return [LinearLayout.LayoutParams]
     */
    private fun getParamsOfHeight(height: Int): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            height
        )
    }

    /**
     * Function to initialize all the observers on [LiveData]
     */
    private fun initObservers() {
        viewModel.isLoading.observe(this, { updateProgress(it) })
        viewModel.showError.observe(this, { showErrorResponse() })
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
