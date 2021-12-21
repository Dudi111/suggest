package com.example.autosuggestion.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.autosuggestion.api.RetrofitInterface
import com.example.autosuggestion.model.ResponseDTO

class addsRepository(private val locationApi:RetrofitInterface) {

    private val locationLiveData=MutableLiveData<ResponseDTO>()
    val location:LiveData<ResponseDTO>
    get() = locationLiveData

    suspend fun getLocation(queryString: String,city:String){
        val result=locationApi.getPlace(queryString,city)
        if (result.body()!=null){
            locationLiveData.postValue(result.body())
        }
    }
}