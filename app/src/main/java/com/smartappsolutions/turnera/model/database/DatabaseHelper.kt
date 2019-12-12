package com.smartappsolutions.turnera.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.model.database.entities.GlobalDao

@Database(entities = [Global::class], version = 1)
abstract class DatabaseHelper:RoomDatabase() {

    abstract fun globalDao(): GlobalDao

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

