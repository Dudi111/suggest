package com.example.autosuggestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.example.autosuggestion.adapterViewHolder.addAdatper
import com.example.autosuggestion.api.RetrofitBuilder
import com.example.autosuggestion.api.RetrofitInterface
import com.example.autosuggestion.model.Address
import com.example.autosuggestion.model.ResponseDTO
import com.example.autosuggestion.model.Suggestions
import com.example.autosuggestion.repository.addsRepository
import com.example.autosuggestion.viewModel.LocationsViewModel
import com.example.autosuggestion.viewModel.LocationsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: LocationsViewModel
    lateinit var repository: addsRepository
    val addressList = ArrayList<Address>()
    lateinit var locationsAdapters: addAdatper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locationApi=RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface::class.java)

        repository= addsRepository(locationApi)

        val locationFactory=LocationsViewModelFactory(repository)
        viewModel=ViewModelProviders.of(this,locationFactory).get(LocationsViewModel::class.java)

        floating_search_view.setOnQueryChangeListener(FloatingSearchView.OnQueryChangeListener { oldQuery, newQuery ->
            viewModel.getLocation(oldQuery, "")
            viewModel.location.observe(this, Observer {
                Log.d("DataIsComing", viewModel.location.toString())

                floating_search_view.swapSuggestions(getSuggestions(it))
                addressList.clear()
                addressList.addAll(it.data?.addressList as List<Address>)
                setRecyclerView()
                Log.d("DataIsComing", addressList.toString())
            })

        })

    }

    private fun getSuggestions(it: ResponseDTO?): MutableList<out SearchSuggestion>? {
        val suggestionsList = ArrayList<Suggestions>()
        val list = it?.data?.addressList

        list?.forEach {
            val suggestion = Suggestions(it?.addressString)
            suggestionsList.add(suggestion)
        }

        return suggestionsList
    }

    private fun setRecyclerView() {
        locationsAdapters = addAdatper(this, addressList)
        recyclerView.adapter=locationsAdapters
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}