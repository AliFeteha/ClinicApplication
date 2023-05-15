package com.example.android.clinicapp.patiant.appointments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Appointment
import com.example.android.clinicapp.data.consts.Type
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.utils.PreferenceControl
import kotlinx.coroutines.launch


class AppointmentsViewModel(app: Application) : BaseViewModel(app) {
    private val repo = Repo(app.applicationContext)
    private val context = app.applicationContext
    var appointments = MutableLiveData<List<Appointment>>()
    init {
        viewModelScope.launch {
            repo.refreshAllAppointments()
        }
    }

    fun getRecoeds(){
        val id = PreferenceControl(context).readId()
        val type = PreferenceControl(context).readType()
        if(type ==Type.Doctor.toString()){
            viewModelScope.launch {
                appointments = id?.let { repo.getDoctorRecords(it) }!!
            }
        }else{
            viewModelScope.launch {
                appointments = id?.let { repo.getPatientRecords(it) }!!
            }
        }
    }
}