package com.smartappsolutions.turnera.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartappsolutions.turnera.database.entities.Global
import com.smartappsolutions.turnera.repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application){

    val TAG ="LoginViewModel"
    var email:String?=null
    var password:String?=null

    private val repository = LoginRepository(application)

    val validation: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        Log.d(TAG,"hello world viewmodel")
    }

    val globals =repository.getGlobal()

    fun saveGlobal(global: Global){
        repository.insert(global)
    }

    fun onButtonClick(view: View){
        Log.d(TAG,"onclick")

        if(email.isNullOrEmpty()||password.isNullOrEmpty()){
            Log.d(TAG,"campos estan vacios")
            validation.value="campos estna vacios"
            return
        }else{
            Log.d(TAG,"email: $email \n  password: $password")
            validation.value="email: $email \n  password: $password"

            ///test room///
            saveGlobal(Global(true,"192.168.1.141"))

        }
    }
}