package com.smartappsolutions.turnera.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartappsolutions.turnera.model.database.DatabaseHelper
import com.smartappsolutions.turnera.model.database.entities.Asuntos
import com.smartappsolutions.turnera.model.database.entities.AsuntosDao
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.model.database.entities.GlobalDao


class AsuntosRepository(application: Application) {

    var A_TAG ="Asuntos"
    var TAG =A_TAG

    private  val asuntosDao:AsuntosDao? =DatabaseHelper.getInstance(application)?.asuntosDao()

    suspend fun getAsuntos():List<Asuntos>?{
        return asuntosDao?.getAsuntos()
    }

    fun insert(asunto: Asuntos){
        Log.d(TAG,"insert()")
        if(asuntosDao !=null) {
            MInsertAsyncTask(asuntosDao).execute(asunto)
        }

    }

    /*private class InsertAsyncTask(private val asuntosDao: AsuntosDao): AsyncTask<Asuntos, Void, Void>(){
        override fun doInBackground(vararg asuntos:Asuntos?): Void? {

            for(asunto in asuntos){
                if(asunto !=null)asuntosDao.insert(asunto)
            }

            return null

        }

    }*/

    private class MInsertAsyncTask(private val asuntosDao: AsuntosDao): AsyncTask<Asuntos, Void, Void>(){
        override fun doInBackground(vararg asuntos: Asuntos?): Void? {
            Log.d("Asuntos","doinbackground()")
            for(asunto in asuntos){
                if(asunto !=null)asuntosDao.insert(asunto)
            }

            return null

        }

    }

}