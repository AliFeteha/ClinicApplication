package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "records")
data class RecordsDTO(
    @ColumnInfo(name = "done") var title: Boolean?,
    @ColumnInfo(name = "patient") var pName: String?,
    @ColumnInfo(name = "patient_id") var pId: Int?,
    @ColumnInfo(name = "doctor") var dName: String?,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "doctor_id") var dId: Int?,
    @PrimaryKey @ColumnInfo(name = "id") val id:Int?
)

