package com.smartappsolutions.turnera.model.database.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsuntosDao {

    @Insert
    fun insert(asunto:Asuntos)

    @Update
    fun update(asunto:Asuntos)

    @Delete
    fun delete(asunto: Asuntos)

    @Query("SELECT *FROM "+ Asuntos.TABLE_NAME+" ORDER BY name_asunto")
    suspend fun getAsuntos():List<Asuntos>
}