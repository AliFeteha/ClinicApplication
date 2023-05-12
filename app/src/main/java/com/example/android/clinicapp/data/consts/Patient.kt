package com.example.android.clinicapp.data.consts

data class Patient(
    var address :String?=null,
    var birthDate :String?=null,
    var bloodType :String?=null,
    var city :String?=null,
    var email :String?=null,
    val emergencyContact: EmergencyContact? = null,
    var gender :String?=null,
    var id :String?=null,
    var imageUrl :String?=null,
    val insurance: MedicalInsurance? = null,
    val medicalIssues: List<String> = mutableListOf(),
    var mobilePhone :String?=null,
    var name :String?=null,


)
