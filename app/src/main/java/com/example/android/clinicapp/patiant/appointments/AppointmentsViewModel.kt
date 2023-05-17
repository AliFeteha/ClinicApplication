package com.example.android.clinicapp.patiant.appointments

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Appointment
import com.example.android.clinicapp.utils.PreferenceControl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AppointmentsViewModel(app: Application) : BaseViewModel(app) {
    var preferenceControl = PreferenceControl(app.applicationContext)
    val repo = Repo(app.applicationContext)
    private val context = app.applicationContext
    var appointments = MutableLiveData<List<Appointment>>(listOf())
    var case:MutableLiveData<List<Appointment>> = MutableLiveData()
    init{
        Log.i(" gettt","init")
        viewModelScope.launch {
            repo.refreshAllAppointments(case)
        }
    }
    fun getRecords(){
        Log.i(" gettt","getRecords")
        try {
            Log.i(" gettt","in try")
            val id = preferenceControl.readId()
            val type = preferenceControl.readType()
            if(type =="Doctor"){
                viewModelScope.launch {
                    repo.getDoctorRecords(appointments, id!!)
                }
            }else{
                Log.i(" gettt","in else")
                viewModelScope.launch {
                    repo.getPatientRecords(appointments, id!!)
                }
            }
        }catch (e:Exception){
            Log.i(" erorrrrrrrr",e.toString())
        }

    }
}