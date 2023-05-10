package com.example.android.clinicapp.data.consts

data class Doctor(
    val id : Int,
    val name: String,
    val workingDays: List<Days>,
    val email: String,
    val imageURL: String,
    val city: String,
    val telephone: String,
    val address: String
)