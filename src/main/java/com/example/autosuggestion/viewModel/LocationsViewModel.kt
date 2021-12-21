package com.example.autosuggestion.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autosuggestion.model.ResponseDTO
import com.example.autosuggestion.repository.addsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel(private val locRepository: addsRepository): ViewModel() {
    fun getLocation(stringQuery: String,city:String){
        viewModelScope.launch (Dispatchers.IO){
            locRepository.getLocation(stringQuery,city)
        }
    }
    val location:LiveData<ResponseDTO>
    get() = locRepository.location
}