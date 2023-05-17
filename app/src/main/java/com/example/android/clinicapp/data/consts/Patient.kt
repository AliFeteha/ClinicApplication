package com.example.android.clinicapp.data.consts

import java.io.Serializable

data class Patient(
    var address :String?=null,
    var birthDate :String?=null,
    var bloodType :String?=null,
    var city :String?=null,
    var email :String?=null,
    var emergencyContact: EmergencyContact = EmergencyContact(null,null),
    var gender :String?=null,
    var id :String?=null,
    var imageUrl :String?=null,
    var insurance: MedicalInsurance = MedicalInsurance(),
    var medicalIssues: List<String> = mutableListOf(),
    var mobilePhone :String?=null,
    var name :String?=null,


    ): Serializable
