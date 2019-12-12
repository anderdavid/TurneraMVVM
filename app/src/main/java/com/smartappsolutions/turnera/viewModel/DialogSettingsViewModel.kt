package com.smartappsolutions.turnera.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.repository.LoginRepository

class DialogSettingsViewModel(application: Application) : AndroidViewModel(application){

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