package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.clinicapp.data.consts.Days
import com.example.android.clinicapp.data.consts.Doctor

@Entity(tableName = "days")
data class DaysDTO(
    @PrimaryKey @ColumnInfo(name = "day") val day:Days,
    @ColumnInfo(name = "Doctors") val doctors:List<Doctor>?
)
