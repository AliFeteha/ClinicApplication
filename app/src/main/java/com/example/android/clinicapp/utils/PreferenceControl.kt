package com.example.android.clinicapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient
import com.example.android.clinicapp.data.consts.Type


class PreferenceControl(context: Context) {


    val sharedPreferences: SharedPreferences =  PreferenceManager.getDefaultSharedPreferences(context)

    fun writePatient(profile: Patient){
        with (sharedPreferences.edit()) {
            putString(name, profile.name)
            putString(email, profile.email)
            putString(address, profile.address)
            putString(id, profile.id)
            putString(birthday, profile.birthDate)
            putString(blood, profile.bloodType)
            putString(city, profile.city)
            putString(gender, profile.gender)
            putString(img_url, profile.imageUrl)
            putString(phoneNumber, profile.mobilePhone)
            putString(medicalInsuranceName,profile.insurance.insuranceProvider)
            putString(medicalInsuranceId,profile.insurance.id)
            putString(emergencyContact,profile.emergencyContact.name)
            putString(emergencyNumber,profile.emergencyContact.phoneNumber)
            apply()
        }
        writeType(Type.Patient)
    }
    fun write(profile: Doctor){
        with (sharedPreferences.edit()) {
            putString(name, profile.name)
            putString(email, profile.email)
            putString(address, profile.address)
            putString(id, profile.id)
            putString(birthday, profile.telephone)
            putString(city, profile.city)
            putString(img_url, profile.imageURL)
            putString(phoneNumber, profile.telephone)
            apply()
        }
        writeType(Type.Doctor)
    }
    fun writePatient(nameVal:String, emailVal:String){
        with (sharedPreferences.edit()) {
            putString(name, nameVal)
            putString(email, emailVal)
            apply()
        }
    }
    fun writeId(value:String){
        with (sharedPreferences.edit()) {
            putString(id, value)
            apply()
        }
    }
    fun readType():String? = sharedPreferences.getString(type,null)


    fun writeType(value: Type ) {
        sharedPreferences.edit().putString(type, value.toString()).apply()
    }


    fun readId():String? {return sharedPreferences.getString(id,null)}

    fun readPatient():Patient{
        val profile = Patient()
        with(sharedPreferences){
            profile.name = getString(name,"")
            profile.email = getString(email, "")
            profile.address = getString(address, "")
            profile.id = getString(id, "")
            profile.birthDate = getString(birthday, "")
            profile.bloodType = getString(blood, "")
            profile.city = getString(city, "")
            profile.gender = getString(gender, "")
            profile.imageUrl = getString(img_url, "")
            profile.mobilePhone = getString(phoneNumber, "")
            profile.emergencyContact.name = getString(emergencyContact,"")
            profile.emergencyContact.phoneNumber = getString(emergencyNumber,"")
            profile.medicalIssues = listOf()
            profile.insurance.insuranceProvider = getString(medicalInsuranceName,"")!!
            profile.insurance.id = getString(medicalInsuranceId,"")!!
        }
        return profile
    }
    fun readDoctor():Doctor{
        val profile = Doctor()
        with(sharedPreferences){
             profile.name = getString(name,"")
             profile.email = getString(email,"")
             profile.address = getString(address,"")
             profile.id = getString(id,"")
             profile.telephone = getString(birthday,"")
             profile.city = getString(city,"")
             profile.imageURL = getString(img_url,"")
             profile.telephone = getString(phoneNumber,"")
        }
        return profile
    }
    fun clearPref(){
        sharedPreferences.edit().clear().apply()
    }

    fun readName(): String? = sharedPreferences.getString(name,"")


    companion object{
        const val name = "name"
        const val email = "email"
        const val address = "address"
        const val city = "city"
        const val birthday = "birthday"
        const val img_url = "img_url"
        const val phoneNumber = "phoneNumber"
        const val gender = "gender"
        const val blood = "blood"
        const val type = "type"
        const val id = "id"
        const val emergencyContact = "emergencyContactName"
        const val emergencyNumber = "emergencyContactNum"
        const val medicalInsuranceName = "Medical Insurance Provider"
        const val medicalInsuranceId = "Medical Insurance ID"
    }
}