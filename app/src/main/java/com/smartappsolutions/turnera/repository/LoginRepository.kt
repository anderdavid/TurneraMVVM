package com.smartappsolutions.turnera.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartappsolutions.turnera.model.classes.LoginResponse
import com.smartappsolutions.turnera.model.database.DatabaseHelper
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.model.database.entities.GlobalDao
import com.smartappsolutions.turnera.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import  retrofit2.Response


class LoginRepository (application: Application) {
    var A_TAG ="Login"
    val TAG =A_TAG


    val responseLogin:MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }

    val showProgressBarFlag:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }


    private val globalDao: GlobalDao? = DatabaseHelper.getInstance(application)?.globalDao()


    fun insert(global: Global){
        if(globalDao !=null) {
            InsertAsyncTask(globalDao).execute(global)
        }

    }

    fun userLogin(email:String, password:String){
        Log.d(TAG,"userLogin()")


        var mResponse:String?=""
        showProgressBarFlag.value=true
        MyApi().userLogin(email,password)
            .enqueue(object:Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                   Log.d(TAG,"onFailure()")
                    showProgressBarFlag.value=false
                    responseLogin.value = LoginResponse(false,"Error en la conexion,Revice su conexion a internet",null)
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){

                        mResponse =response.body()?.string()
                        Log.d(TAG,"success: "+mResponse)

                        showProgressBarFlag.value=false
                        responseLogin.value =LoginResponse(response.isSuccessful,response.message(),mResponse)
                    }else{
                       mResponse=response.errorBody()?.string()
                        Log.d(TAG,"fail: "+mResponse)
                        showProgressBarFlag.value=false
                        responseLogin.value =LoginResponse(response.isSuccessful,response.message(),mResponse)
                    }
                }
            })



    }

    fun update(global: Global){
        Log.d(TAG,"repository.update")
        if(globalDao !=null){
            UpDateAsyncTask(globalDao).execute(global)
        }
    }

    fun getGlobal():LiveData<List<Global>>{
        return globalDao?.getGlobal() ?: MutableLiveData<List<Global>>()

    }

    fun getFirstGlobal():LiveData<Global>{
        return globalDao?.getFirstGlobal() ?:MutableLiveData<Global>()
    }

    fun validateExistFirstGlobal():LiveData<Boolean>{
        return  globalDao?.validateExistFirstGlobal() ?:MutableLiveData<Boolean>()
    }

    private class InsertAsyncTask(private val globalDao: GlobalDao): AsyncTask<Global, Void, Void>(){
        override fun doInBackground(vararg globals: Global?): Void? {

            for(global in globals){
                if(global !=null)globalDao.insert(global)
            }

            return null

        }

    }

    private class UpDateAsyncTask(private val globalDao: GlobalDao): AsyncTask<Global, Void, Void>(){
        var A_TAG ="Login"
        override fun doInBackground(vararg globals: Global?): Void? {

            Log.d(A_TAG, "length: "+globals.size)
            for(global in globals){
                Log.d(A_TAG,"backend: "+global?.backend.toString())
                if(global !=null)globalDao.update(global)
            }
            return null
        }
    }



}