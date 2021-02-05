package com.example.dogsbreedtechnicaltest.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogsbreedtechnicaltest.R
import com.example.dogsbreedtechnicaltest.adapters.BreedPicturesAdapter
import com.example.dogsbreedtechnicaltest.databinding.ActivityBreedPicturesBinding
import com.example.dogsbreedtechnicaltest.viewmodels.BreedPicturesViewModel
import java.util.ArrayList

class BreedPicturesActivity : AppCompatActivity() {
    private var activityBreedPicturesBinding: ActivityBreedPicturesBinding? = null
    private var viewModel: BreedPicturesViewModel? = null
    private val breedPictures: MutableList<String> = ArrayList()
    private var breedPicturesAdapter: BreedPicturesAdapter? = null
    private var breedName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBreedPicturesBinding = DataBindingUtil.setContentView(this, R.layout.activity_breed_pictures)
        doInitialization()
    }

    override fun onResume() {
        super.onResume()
        getBreedPictures
    }

    private fun doInitialization() {
        viewModel = ViewModelProvider(this).get(BreedPicturesViewModel::class.java)
        activityBreedPicturesBinding!!.btnBack.setOnClickListener { _: View? -> onBackPressed() }
        bindBreedName()
        activityBreedPicturesBinding!!.breedsRecyclerview!!.setHasFixedSize(true)
        breedPicturesAdapter = BreedPicturesAdapter(breedPictures)
        activityBreedPicturesBinding!!.breedsRecyclerview!!.adapter = breedPicturesAdapter
    }

    private fun bindBreedName() {
        breedName = intent.getStringExtra("name")!!
        activityBreedPicturesBinding!!.name = breedName
    }

    private val getBreedPictures: Unit
        get() {
            activityBreedPicturesBinding!!.isLoading = true
            viewModel!!.getBreedPictures(breedName).observe(this, { breedsResponse: List<String>? ->
                activityBreedPicturesBinding!!.isLoading = false
                if (breedsResponse != null) {
                    breedPictures.clear()
                    breedPictures.addAll(breedsResponse)
                    breedPicturesAdapter!!.notifyDataSetChanged()
                }
            })
        }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        changesRecyclerLayoutManager(newConfig)
    }

    private fun changesRecyclerLayoutManager(newConfig: Configuration) {
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            activityBreedPicturesBinding!!.breedsRecyclerview!!.layoutManager = GridLayoutManager(this, 4)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            activityBreedPicturesBinding!!.breedsRecyclerview!!.layoutManager = GridLayoutManager(this,3)
        }
    }
}