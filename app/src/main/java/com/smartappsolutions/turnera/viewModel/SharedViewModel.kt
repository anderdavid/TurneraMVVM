package com.smartappsolutions.turnera.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {

    val data = MutableLiveData<String>()

    fun setData(item:String){
        data.value=item
    }
}