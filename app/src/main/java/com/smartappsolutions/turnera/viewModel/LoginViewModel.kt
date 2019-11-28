package com.smartappsolutions.turnera.viewModel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    val TAG ="LoginViewModel"
    var email:String?=null
    var password:String?=null

    val validation: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        Log.d(TAG,"hello world viewmodel")
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
        }
    }
}