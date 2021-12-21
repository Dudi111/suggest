package com.example.autosuggestion.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autosuggestion.repository.addsRepository

class LocationsViewModelFactory(private val locRepository: addsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationsViewModel(locRepository)as T
    }

}