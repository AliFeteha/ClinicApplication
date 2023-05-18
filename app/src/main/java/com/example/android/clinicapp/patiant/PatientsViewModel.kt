package com.example.android.clinicapp.patiant

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Appointment
import com.example.android.clinicapp.data.consts.Patient
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.utils.PreferenceControl
import kotlinx.coroutines.launch

class PatientsViewModel(app: Application) : BaseViewModel(app) {
    private val preferenceControl = PreferenceControl(app.applicationContext)
    private val repo = Repo(app.applicationContext)
    var patients = MutableLiveData<List<Patient>>()
    var doctor = repo.doctor
    init {
        /*viewModelScope.launch {
            repo.refreshDoctorProfile(preferenceControl.readId()!!)
        }*/
    }
    fun getPatients(){
        var patientToAdd = mutableListOf<Patient>()

    }
}