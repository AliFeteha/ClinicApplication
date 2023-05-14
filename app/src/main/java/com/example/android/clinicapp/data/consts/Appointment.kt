package com.example.android.clinicapp.data.consts

import java.io.Serializable
import javax.sql.StatementEvent

data class Appointment(
    var date : String,
    var dId:String,
    var dName:String,
    var id:String,
    var pId:String,
    var pName: String,
    var title :String
): Serializable
