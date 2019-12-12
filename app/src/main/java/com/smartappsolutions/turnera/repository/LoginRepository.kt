package com.smartappsolutions.turnera.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    val loginResponse:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
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

        MyApi().userLogin(email,password)
            .enqueue(object:Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value=t.message
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){

                        mResponse =response.body()?.string()
                        loginResponse.value=mResponse
                        Log.d(TAG,"success: "+mResponse)
                    }else{
                       mResponse=response.errorBody()?.string()
                        loginResponse.value=mResponse
                        Log.d(TAG,"fail: "+mResponse)
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