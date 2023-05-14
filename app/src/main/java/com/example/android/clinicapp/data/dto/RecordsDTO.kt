package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "records")
data class RecordsDTO(
    @ColumnInfo(name = "done") var title: String?,
    @ColumnInfo(name = "patient") var pName: String?,
    @ColumnInfo(name = "patient_id") var pId: String?,
    @ColumnInfo(name = "doctor") var dName: String?,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "doctor_id") var dId: String?,
    @PrimaryKey @ColumnInfo(name = "id") val id:String
)

