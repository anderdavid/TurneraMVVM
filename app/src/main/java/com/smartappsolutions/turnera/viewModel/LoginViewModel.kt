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

    var A_TAG ="Login"
    val TAG =A_TAG
    /*val TAG ="LoginViewModel"*/
    var email:String?=null
    var password:String?=null

    var loginStatus:Boolean?=null
    var backend:String?=null

    private val repository = LoginRepository(application)

    val globals =repository.getGlobal()
    var firstGlobal =repository.getFirstGlobal()

    var existFirstGlobal = repository.validateExistFirstGlobal()


    val validation: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        Log.d(TAG,"hello world viewmodel")

    }

    fun initGlobal(mLoginStatus:Boolean, mBackend:String):Boolean{
        Log.d(TAG,"initGlobal")
        repository.insert(Global(mLoginStatus,mBackend))
        return true
    }


    fun updateGlobal(global: Global){
        repository.update(global)
    }

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
           /* saveGlobal(Global(true,"192.168.1.141"))*/

        }
    }
}