package com.example.android.clinicapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.clinicapp.data.consts.EmergencyContact
import com.example.android.clinicapp.data.consts.MedicalInsurance


@Entity(tableName = "patients")
data class PatientsDTO(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "gender") var gender:String?,
    @ColumnInfo(name = "email") var email:String?,
    @ColumnInfo(name = "birthdate") var birthDate: String?,
    @ColumnInfo(name = "img_url") var img_url: String?,
    @ColumnInfo(name = "address") var address:String?,
    @ColumnInfo(name = "city") var city: String?,
    @ColumnInfo(name = "mobilePhone") var mobilePhone: String?,
    @ColumnInfo(name = "blood") var bloodType:String?,
    @ColumnInfo(name = "medicalIssues") var medicalIssues: List<String>,
    @ColumnInfo(name = "emergency") var emergencyContact: EmergencyContact?,
    @ColumnInfo(name = "insurance") var insurance: MedicalInsurance?
)
