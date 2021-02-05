package com.example.dogsbreedtechnicaltest.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogsbreedtechnicaltest.R
import com.example.dogsbreedtechnicaltest.adapters.BreedsAdapter
import com.example.dogsbreedtechnicaltest.databinding.ActivityMainBinding
import com.example.dogsbreedtechnicaltest.listeners.BreedListener
import com.example.dogsbreedtechnicaltest.viewmodels.BreedsViewModel
import java.util.*

class MainActivity : AppCompatActivity(), BreedListener {
    private var activityMainBinding: ActivityMainBinding? = null
    private var viewModel: BreedsViewModel? = null
    private val breeds: MutableList<String> = ArrayList()
    private var breedsAdapter: BreedsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        doInitialization()
    }

    override fun onResume() {
        super.onResume()
        getBreeds
    }

    private fun doInitialization() {
        activityMainBinding!!.breedsRecyclerview.setHasFixedSize(true)
        viewModel = ViewModelProvider(this).get(BreedsViewModel::class.java)
        breedsAdapter = BreedsAdapter(breeds, this)
        activityMainBinding!!.breedsRecyclerview.adapter = breedsAdapter
    }

    private val getBreeds: Unit
        get() {
            activityMainBinding!!.isLoading = true
            viewModel!!.breeds.observe(this, { breedsResponse: List<String>? ->
                activityMainBinding!!.isLoading = false
                if (breedsResponse != null) {
                    breeds.clear()
                    breeds.addAll(breedsResponse)
                    breedsAdapter!!.notifyDataSetChanged()
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
            activityMainBinding!!.breedsRecyclerview.layoutManager = GridLayoutManager(this, 2)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            activityMainBinding!!.breedsRecyclerview.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onBreedClick(breed: String?) {
        val intent = Intent(applicationContext, BreedPicturesActivity::class.java)
        intent.putExtra("name", breed)
        startActivity(intent)
    }
}