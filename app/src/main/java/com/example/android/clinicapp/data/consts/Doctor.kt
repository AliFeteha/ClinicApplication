package com.example.android.clinicapp.data.consts

data class Doctor(
    val address: String? = null,
    val city: String? = null,
    val email: String? = null,
    val gender:String? = null,
    val id: String? = null,
    val imageURL: String? = null,
    val name: String? = null,
    val telephone: String? = null,
    val workingDays: List<Days>? = null
)