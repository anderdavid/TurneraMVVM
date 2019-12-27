package com.smartappsolutions.turnera.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Asuntos.TABLE_NAME )
data class Asuntos (
    @ColumnInfo(name="name_asunto")@NotNull var asunto:String
){
    companion object{
        const val TABLE_NAME ="asuntos"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="asuntos_id")
    var asuntosId:Int =0
}