package com.example.android.clinicapp.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.dashboard.fragments.DoctorDashboardDirections
import com.example.android.clinicapp.dashboard.fragments.PatientDashboardDirections
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient

class DashboardViewModel(val app:Application): BaseViewModel(app) {
    val patient :MutableLiveData<Patient> = MutableLiveData()
    val doctor :MutableLiveData<Doctor> = MutableLiveData()
    private val repo = Repo(app.applicationContext)
    fun navigateToAppointments(){
        navigationCommand.value =
                NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToPatientAppointments())
    }

    fun navigateToDoctorAppointments(){
        navigationCommand.value =
            NavigationCommand.To(DoctorDashboardDirections.actionDoctorDashboardToPatientAppointments2())
    }
    fun navigateToDoctorProfile() {
        navigationCommand.value =
            NavigationCommand.To(DoctorDashboardDirections.actionDoctorDashboardToDoctorProfileFragment())
    }
    fun navigateToPatientProfile(){
        navigationCommand.value =
            NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToPatientProfileFragment())
    }
    fun navigatePatientToForm(){
        navigationCommand.value =
            NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToForum())
    }
    fun navigateDoctorToForm(){
        navigationCommand.value =
            NavigationCommand.To(DoctorDashboardDirections.actionDoctorDashboardToForum2())
    }
    fun navigateDoctorToBroken(){
        navigationCommand.value =
            NavigationCommand.To(DoctorDashboardDirections.actionDoctorDashboardToForum2())
    }
    fun navigatePatientToBroken(){
        navigationCommand.value =
            NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToBroken2())
    }
    fun navigateDoctorToPatients(){
        navigationCommand.value =
            NavigationCommand.To(DoctorDashboardDirections.actionDoctorDashboardToBroken())
    }
    fun refreshPatient(){
        repo.refreshPatient(patient)
    }
    fun refreshDoctor(){
        repo.refreshDoctor(doctor)
    }
}