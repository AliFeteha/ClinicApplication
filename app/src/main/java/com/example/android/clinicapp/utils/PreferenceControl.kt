package com.example.android.clinicapp.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import com.example.android.clinicapp.R
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient
import com.example.android.clinicapp.data.consts.Type

class PreferenceControl {
    
    val sharedPreferences = Activity().getPreferences(Context.MODE_PRIVATE)

    fun write(profile: Patient){
        with (sharedPreferences.edit()) {
            putString(Resources.getSystem().getString(R.string.name), profile.name)
            putString(Resources.getSystem().getString(R.string.email), profile.email)
            putString(Resources.getSystem().getString(R.string.address), profile.address)
            putString(Resources.getSystem().getString(R.string.preference_id), profile.id)
            putString(Resources.getSystem().getString(R.string.birthDate), profile.birthDate)
            putString(Resources.getSystem().getString(R.string.blood), profile.bloodType)
            putString(Resources.getSystem().getString(R.string.city), profile.city)
            putString(Resources.getSystem().getString(R.string.gender), profile.gender)
            putString(Resources.getSystem().getString(R.string.imgUrl), profile.imageUrl)
            putString(Resources.getSystem().getString(R.string.phone_number), profile.mobilePhone)
            apply()
        }
        writeType(Type.Patient.toString())
    }
    fun write(profile: Doctor){
        with (sharedPreferences.edit()) {
            putString(Resources.getSystem().getString(R.string.name), profile.name)
            putString(Resources.getSystem().getString(R.string.emailExample), profile.email)
            putString(Resources.getSystem().getString(R.string.email), profile.email)
            putString(Resources.getSystem().getString(R.string.address), profile.address)
            putString(Resources.getSystem().getString(R.string.preference_id), profile.id)
            putString(Resources.getSystem().getString(R.string.birthDate), profile.telephone)
            putString(Resources.getSystem().getString(R.string.city), profile.city)
            putString(Resources.getSystem().getString(R.string.imgUrl), profile.imageURL)
            putString(Resources.getSystem().getString(R.string.phone_number), profile.telephone)
            apply()
        }
        writeType(Type.Doctor.toString())
    }
    fun writeId(id:String){
        with (sharedPreferences.edit()) {
            putString(Resources.getSystem().getString(R.string.preference_id), id)
            apply()
        }
    }
    fun readType():String? = sharedPreferences.getString(Resources.getSystem().getString(R.string.type),null)


    fun writeType(value :String ) {
        sharedPreferences.edit().putString(Resources.getSystem().getString(R.string.type), value).apply()
    }


    fun readId():String? = sharedPreferences.getString(Resources.getSystem().getString(R.string.preference_id),null)

    fun readPatient():Patient{
        val profile = Patient()
        with(sharedPreferences){
            profile.name = getString(Resources.getSystem().getString(R.string.name),null)
            profile.email = getString(Resources.getSystem().getString(R.string.email), null)
            profile.address = getString(Resources.getSystem().getString(R.string.address), null)
            profile.id = getString(Resources.getSystem().getString(R.string.preference_id), null)
            profile.birthDate = getString(Resources.getSystem().getString(R.string.birthDate), null)
            profile.bloodType = getString(Resources.getSystem().getString(R.string.blood), null)
            profile.city = getString(Resources.getSystem().getString(R.string.city), null)
            profile.gender = getString(Resources.getSystem().getString(R.string.gender), null)
            profile.imageUrl = getString(Resources.getSystem().getString(R.string.imgUrl), null)
            profile.mobilePhone = getString(Resources.getSystem().getString(R.string.phone_number), null)
        }
        return profile
    }
    fun readDoctor():Doctor{
        val profile = Doctor()
        with(sharedPreferences){
             profile.name = getString(Resources.getSystem().getString(R.string.name),null)
             profile.email = getString(Resources.getSystem().getString(R.string.emailExample),null)
             profile.email = getString(Resources.getSystem().getString(R.string.email),null)
             profile.address = getString(Resources.getSystem().getString(R.string.address),null)
             profile.id = getString(Resources.getSystem().getString(R.string.preference_id),null)
             profile.telephone = getString(Resources.getSystem().getString(R.string.birthDate),null)
             profile.city = getString(Resources.getSystem().getString(R.string.city),null)
             profile.imageURL = getString(Resources.getSystem().getString(R.string.imgUrl),null)
             profile.telephone = getString(Resources.getSystem().getString(R.string.phone_number),null)
        }
        return profile
    }
    fun clearPref(){
        sharedPreferences.all.clear()
    }
}