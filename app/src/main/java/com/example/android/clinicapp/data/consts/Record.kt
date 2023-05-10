package com.example.android.clinicapp.data.consts

data class Record (
    val id :Int,
    val patient: String,
    val patientId: Int,
    val doctor: String,
    val doctorId: Int,
    val date: String,
    val done: Boolean
    )