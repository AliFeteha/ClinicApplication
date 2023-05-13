package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class DaysDTO(
    @PrimaryKey @ColumnInfo(name = "id") var id :String,
    @ColumnInfo(name = "saturday") val saturday:List<String?>,
    @ColumnInfo(name = "sunday") var sunday:List<String?>,
    @ColumnInfo(name = "monday") var monday:List<String?>,
    @ColumnInfo(name = "tuesday") var tuesday:List<String?>,
    @ColumnInfo(name = "wednesday") var wednesday:List<String?>,
    @ColumnInfo(name = "thursday") var thursday:List<String?>,
    @ColumnInfo(name = "friday") var friday:List<String?>
)
