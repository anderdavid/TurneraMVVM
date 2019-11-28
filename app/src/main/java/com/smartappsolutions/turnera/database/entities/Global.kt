package com.smartappsolutions.turnera.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Global.TABLE_NAME)
data class Global(
    @ColumnInfo(name = "is_login")@NotNull val isLogin :Boolean,
    @ColumnInfo(name = "backend")@NotNull val backend :String
){
    companion object{
        const val TABLE_NAME ="global"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="global_id")
    var globalId:Int =0
}
