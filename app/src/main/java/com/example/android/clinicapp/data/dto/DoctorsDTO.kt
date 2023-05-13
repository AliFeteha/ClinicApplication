package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.clinicapp.data.consts.Days
import com.example.android.clinicapp.data.consts.Doctor

@Entity(tableName = "doctors")

data class DoctorsDTO(

    @PrimaryKey @ColumnInfo(name = "id") val id:String?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "gender") var gender:Doctor?,
    @ColumnInfo(name = "working_days") var workDays: List<Days>?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "img_url") var img_url: String?,
    @ColumnInfo(name = "city") var city: String?,
    @ColumnInfo(name = "telephone") var telephone: String?,
    @ColumnInfo(name = "address") var address:String
)
