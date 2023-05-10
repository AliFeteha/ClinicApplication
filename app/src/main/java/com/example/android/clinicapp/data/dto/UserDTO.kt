package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.clinicapp.data.consts.Type


@Entity(tableName = "user")
data class UserDTO(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "type") var type: Type?
)
