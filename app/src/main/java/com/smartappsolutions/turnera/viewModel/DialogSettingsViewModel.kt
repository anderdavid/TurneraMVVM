package com.smartappsolutions.turnera.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.repository.LoginRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DialogSettingsViewModel(application: Application) : AndroidViewModel(application){

    private val repository = LoginRepository(application)

    val firstGlobal:MutableLiveData<Global> by lazy{
        MutableLiveData<Global>()
    }

    init {
        viewModelScope.launch {
            firstGlobal.value =repository.getFirstGlobal()
        }
    }

    suspend fun updateFirstGlobalRoom(global: Global) = withContext(IO){
        repository.update(global)
    }
    fun updateFirstGlobal(global: Global){
        viewModelScope.launch {
            updateFirstGlobalRoom(global)
        }
    }

}