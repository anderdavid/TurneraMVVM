package com.smartappsolutions.turnera.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smartappsolutions.turnera.model.database.entities.Asuntos
import com.smartappsolutions.turnera.model.database.entities.AsuntosDao
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.model.database.entities.GlobalDao

@Database(entities = [Global::class,Asuntos::class], version = 2)
abstract class DatabaseHelper:RoomDatabase() {

    abstract fun globalDao(): GlobalDao
    abstract fun asuntosDao(): AsuntosDao

    companion object{
        private const val DATABASE_NAME="sistema_turnos"


        @Volatile
        private var INSTANCE: DatabaseHelper?=null

        fun getInstance(context: Context): DatabaseHelper? {

            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }


}

