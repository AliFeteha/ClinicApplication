package com.example.android.clinicapp.patiant.appointments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Appointment
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.utils.PreferenceControl
import kotlinx.coroutines.launch


class AppointmentViewModel(app: Application) : BaseViewModel(app) {
    val preferenceControl = PreferenceControl()
    private val repo = Repo(app.applicationContext)
    var appointments = MutableLiveData<List<Appointment>>()
    init {
        viewModelScope.launch {
            repo.refreshAllAppointments()
        }
        val preferenceControl = PreferenceControl()
    }

    fun getRecoeds(){
        val id = preferenceControl.readId()
        val type = preferenceControl.readType()
        if(type =="Doctor"){
            viewModelScope.launch {
                appointments = repo.getDoctorRecords(id!!)
            }
        }else{
            viewModelScope.launch {
                appointments = repo.getPatientRecords(id!!)
            }
        }
    }
}