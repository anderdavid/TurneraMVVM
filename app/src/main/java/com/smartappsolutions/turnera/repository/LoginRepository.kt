package com.smartappsolutions.turnera.repository

import android.app.Application
import com.smartappsolutions.turnera.model.classes.LoginResponseTest
import com.smartappsolutions.turnera.model.database.DatabaseHelper
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.model.database.entities.GlobalDao
import com.smartappsolutions.turnera.network.ApiRest
import com.smartappsolutions.turnera.network.MyApi
import okhttp3.ResponseBody
import  retrofit2.Response


class LoginRepository (application: Application) {
    var A_TAG ="Login"
    val TAG =A_TAG

    private val globalDao: GlobalDao? = DatabaseHelper.getInstance(application)?.globalDao()


    suspend fun validateExistFirstGlobal():Boolean?{
        return  globalDao?.validateExistFirstGlobal()
    }

    suspend fun insert(global: Global){
        if(global !=null)globalDao?.insert(global)
    }

    suspend fun update(global: Global){
        if(global !=null)globalDao?.update(global)
    }

    suspend fun getFirstGlobal(): Global? {
        return globalDao?.getFirstGlobal()
    }

    suspend fun userLogin(email:String,password:String):Response<ResponseBody>{
        //return MyApi().userLogin(email,password)
        val backend =getFirstGlobal()?.backend
        return ApiRest(backend.toString()).userLogin(email,password)

    }


}