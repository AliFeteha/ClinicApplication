package com.example.android.clinicapp.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import com.example.android.clinicapp.R
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient

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
    }
    fun readPatient():Patient{
        val profile = Patient()
        with(sharedPreferences){
            getString(Resources.getSystem().getString(R.string.name),profile.name)
            getString(Resources.getSystem().getString(R.string.email), profile.email)
            getString(Resources.getSystem().getString(R.string.address), profile.address)
            getString(Resources.getSystem().getString(R.string.preference_id), profile.id)
            getString(Resources.getSystem().getString(R.string.birthDate), profile.birthDate)
            getString(Resources.getSystem().getString(R.string.blood), profile.bloodType)
            getString(Resources.getSystem().getString(R.string.city), profile.city)
            getString(Resources.getSystem().getString(R.string.gender), profile.gender)
            getString(Resources.getSystem().getString(R.string.imgUrl), profile.imageUrl)
            getString(Resources.getSystem().getString(R.string.phone_number), profile.mobilePhone)
        }
        return profile
    }
    fun readDoctor():Doctor{
        val profile = Doctor()
        with(sharedPreferences){
            getString(Resources.getSystem().getString(R.string.name), profile.name)
            getString(Resources.getSystem().getString(R.string.emailExample), profile.email)
            getString(Resources.getSystem().getString(R.string.email), profile.email)
            getString(Resources.getSystem().getString(R.string.address), profile.address)
            getString(Resources.getSystem().getString(R.string.preference_id), profile.id)
            getString(Resources.getSystem().getString(R.string.birthDate), profile.telephone)
            getString(Resources.getSystem().getString(R.string.city), profile.city)
            getString(Resources.getSystem().getString(R.string.imgUrl), profile.imageURL)
            getString(Resources.getSystem().getString(R.string.phone_number), profile.telephone)
        }
        return profile
    }
    fun clearPref(){
        sharedPreferences.all.clear()
    }
}