package com.smartappsolutions.turnera.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Global.TABLE_NAME)
data class Global(
    @ColumnInfo(name = "is_login")@NotNull var isLogin :Boolean,
    @ColumnInfo(name = "backend")@NotNull var backend :String
){
    companion object{
        const val TABLE_NAME ="global"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="global_id")
    var globalId:Int =0
}
