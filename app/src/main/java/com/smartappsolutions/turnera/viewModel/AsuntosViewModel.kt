package com.smartappsolutions.turnera.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartappsolutions.turnera.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AsuntosViewModel(application: Application) : AndroidViewModel(application) {

    var A_TAG ="Asuntos"
    val TAG =A_TAG

    val loginRepository = LoginRepository(application)

    val startLoginAcitivity: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    init {
        Log.d(TAG,"hello world AsuntosViewModel")
        startLoginAcitivity.value=false
    }

    fun logOut(){
        Log.d(TAG,"logOut()")
        viewModelScope.launch {
            var mGlobal=loginRepository.getFirstGlobal()
            if(mGlobal !=null){
                mGlobal.isLogin=false
                loginRepository.update(mGlobal)
                startLoginAcitivity.value =true
            }

        }
    }
}