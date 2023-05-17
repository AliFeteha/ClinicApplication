package com.example.android.clinicapp.utils

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.android.clinicapp.App
import com.example.android.clinicapp.R
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient
import com.example.android.clinicapp.data.consts.Type


class PreferenceControl(context: Context) {


    val sharedPreferences: SharedPreferences =  PreferenceManager.getDefaultSharedPreferences(context)

    fun write(profile: Patient){
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
    fun write(nameVal:String,emailVal:String){
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
    fun readType():String? = sharedPreferences.getString(type,"Patient")


    fun writeType(value: Type ) {
        sharedPreferences.edit().putString(type, value.toString()).apply()
    }


    fun readId():String? = sharedPreferences.getString(id,"aa")
    fun readName():String? = sharedPreferences.getString(name,"ali")
    fun readPatient():Patient{
        val profile = Patient()
        with(sharedPreferences){
            profile.name = getString(name,null)
            profile.email = getString(email, null)
            profile.address = getString(address, null)
            profile.id = getString(id, null)
            profile.birthDate = getString(birthday, null)
            profile.bloodType = getString(blood, null)
            profile.city = getString(city, null)
            profile.gender = getString(gender, null)
            profile.imageUrl = getString(img_url, null)
            profile.mobilePhone = getString(phoneNumber, null)
        }
        return profile
    }
    fun readDoctor():Doctor{
        val profile = Doctor()
        with(sharedPreferences){
             profile.name = getString(name,null)
             profile.email = getString(email,null)
             profile.address = getString(address,null)
             profile.id = getString(id,null)
             profile.telephone = getString(birthday,null)
             profile.city = getString(city,null)
             profile.imageURL = getString(img_url,null)
             profile.telephone = getString(phoneNumber,null)
        }
        return profile
    }
    fun clearPref(){
        sharedPreferences.edit().clear().apply()
    }
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
    }
}