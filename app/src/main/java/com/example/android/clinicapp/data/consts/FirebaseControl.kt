package com.example.android.clinicapp.data.consts

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FirebaseControl(
    val email: String? = null,
    val id:String? = null,
    val password :String?=null
)
