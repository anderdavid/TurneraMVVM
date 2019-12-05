package com.smartappsolutions.turnera.database.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GlobalDao {

    @Insert
    fun insert(global: Global)

    @Update
    fun update(global: Global)

    @Delete
    fun delete(global: Global)

    /*@Query("SELECT *FROM  "+Global.TABLE_NAME +" WHERE global_id = 1")*/
    @Query("SELECT *FROM  "+Global.TABLE_NAME +" ORDER BY backend")
    fun getGlobal(): LiveData<List<Global>>

    @Query("SELECT *FROM  "+Global.TABLE_NAME +" WHERE global_id = 1 LIMIT 1")
    fun getFirstGlobal():LiveData<Global>

    @Query("SELECT EXISTS (SELECT *FROM global where global_id =1)")
    fun validateExistFirstGlobal():LiveData<Boolean>
}