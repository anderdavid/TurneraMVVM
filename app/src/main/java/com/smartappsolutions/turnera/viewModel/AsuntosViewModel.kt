package com.smartappsolutions.turnera.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.smartappsolutions.turnera.model.database.entities.Asuntos
import com.smartappsolutions.turnera.repository.AsuntosRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AsuntosViewModel(application: Application) : AndroidViewModel(application){
    var A_TAG ="Asuntos"
    var TAG =A_TAG

    val repository =AsuntosRepository(application)

  /*  val asunto =repository.getAsuntos()*/


    fun getAsuntos(){
        viewModelScope.launch {
          loadAsuntos()
        }
    }

    private  suspend fun loadAsuntos() = withContext(IO){
        val asuntos =repository.getAsuntos()
        if (asuntos != null) {
            for(asunto in asuntos){
                Log.d(TAG, "asunto"+asunto.asuntosId+" "+asunto.asunto)
            }
        }
    }

    fun initAsuntos():Boolean{
        Log.d(TAG,"initAsuntos")
        repository.insert(Asuntos("first asunto"))
        return true
    }

}