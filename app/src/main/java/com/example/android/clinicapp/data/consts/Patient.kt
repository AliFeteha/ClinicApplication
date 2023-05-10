package com.example.android.clinicapp.data.consts

data class Patient(
    var id :Int?=null,
    var name :String?=null,
    var gender :String?=null,
    var email :String?=null,
    var birthDate :String?=null,
    var imageUrl :String?=null,
    var address :String?=null,
    var city :String?=null,
    var mobilePhone :String?=null,
    var bloodType :String?=null,
    val medicalIssues: MutableList<String> = mutableListOf(),
    val emergencyContact: EmergencyContact? = null,
    val insurance: MedicalInsurance? = null
)
