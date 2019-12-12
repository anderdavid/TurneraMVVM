package com.smartappsolutions.turnera.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.smartappsolutions.turnera.model.classes.LoginUser
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application){

    var A_TAG ="Login"
    /*val TAG =A_TAG*/
    val TAG ="LoginViewModel"
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
        repository.insert(Global(mLoginStatus, mBackend))
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



        val loginUser:LoginUser = LoginUser(email.toString(),password.toString())

        if(email.isNullOrEmpty()){
            validation.value="El campo email esta vacio."
        }else if(password.isNullOrEmpty()){
            validation.value="El campo password está vacío."
        }else if(!loginUser.isEmailValid()){
            validation.value="Email invalido."
        }else{

            Log.d(TAG,"isValid()")
            ///test room///
            /* saveGlobal(Global(true,"192.168.1.141"))*/
        }


    }
}