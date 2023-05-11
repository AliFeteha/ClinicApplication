package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.clinicapp.data.consts.Comment
import com.example.android.clinicapp.data.consts.Type


@Entity(tableName = "form")
data class FormDTO(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int?,
    @ColumnInfo(name = "askerId") var askerId: Int?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "img_url") var imageUrl: String?,
    @ColumnInfo(name = "question") var question: String?,
    @ColumnInfo(name = "comments") var comments: List<Comment>?
)
