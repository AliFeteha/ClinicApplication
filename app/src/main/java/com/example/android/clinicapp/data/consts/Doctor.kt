package com.example.android.clinicapp.data.consts

data class Doctor(
    val address: String,
    val city: String,
    val email: String,
    val id: String,
    val imageURL: String,
    val name: String,
    val telephone: String,
    val workingDays: List<Days>,


)