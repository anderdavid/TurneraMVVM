package com.smartappsolutions.turnera.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartappsolutions.turnera.database.DatabaseHelper
import com.smartappsolutions.turnera.database.entities.Global
import com.smartappsolutions.turnera.database.entities.GlobalDao

class LoginRepository (application: Application) {
    private val contactDao: GlobalDao? = DatabaseHelper.getInstance(application)?.globalDao()

    fun insert(global: Global){
        if(contactDao !=null) {
            InsertAsyncTask(contactDao).execute(global)
        }

    }

    fun getGlobal():LiveData<List<Global>>{
        return contactDao?.getGlobal() ?: MutableLiveData<List<Global>>()

    }

    private class InsertAsyncTask(private val globalDao: GlobalDao): AsyncTask<Global, Void, Void>(){
        override fun doInBackground(vararg globals: Global?): Void? {

            for(global in globals){
                if(global !=null)globalDao.insert(global)
            }

            return null

        }

    }
}