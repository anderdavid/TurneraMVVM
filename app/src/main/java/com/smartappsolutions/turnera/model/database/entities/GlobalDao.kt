package com.smartappsolutions.turnera.model.database.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GlobalDao {

    @Insert
    suspend fun insert(global: Global)

    @Update
    suspend fun update(global: Global)

    @Delete
    suspend fun delete(global: Global)

    @Query("SELECT EXISTS (SELECT *FROM global where global_id =1)")
    suspend fun validateExistFirstGlobal():Boolean

    @Query("SELECT *FROM  "+ Global.TABLE_NAME +" WHERE global_id = 1 LIMIT 1")
    suspend fun getFirstGlobal():Global

    @Query("SELECT *FROM  "+ Global.TABLE_NAME +" ORDER BY backend")
    suspend fun getGlobal(): List<Global>

}