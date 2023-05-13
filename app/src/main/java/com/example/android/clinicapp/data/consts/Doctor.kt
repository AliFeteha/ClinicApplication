package com.example.android.clinicapp.data.consts

data class Doctor(
    var address: String? = null,
    var city: String? = null,
    var email: String? = null,
    val gender:String? = null,
    var id: String? = null,
    var imageURL: String? = null,
    var name: String? = null,
    var telephone: String? = null,
    val workingDays: List<Days>? = null
)