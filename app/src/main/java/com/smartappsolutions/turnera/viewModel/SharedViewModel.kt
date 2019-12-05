package com.smartappsolutions.turnera.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartappsolutions.turnera.database.entities.Global
import com.smartappsolutions.turnera.repository.LoginRepository

class SharedViewModel(application: Application) : AndroidViewModel(application){

    private val repository = LoginRepository(application)
    val data = MutableLiveData<String>()

    fun setData(item:String){
        data.value=item
    }

    val globals =repository.getGlobal()
    var firstGlobal =repository.getFirstGlobal()

    fun saveGlobal(global: Global){
        repository.insert(global)
    }
    fun updateFirstGlobal(global: Global){
        repository.update(global)
    }
}