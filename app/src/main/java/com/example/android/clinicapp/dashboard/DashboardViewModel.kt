package com.example.android.clinicapp.dashboard

import android.app.Application
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.dashboard.fragments.DoctorDashboardDirections
import com.example.android.clinicapp.dashboard.fragments.PatientDashboardDirections

class DashboardViewModel(val app:Application): BaseViewModel(app) {


    fun navigateToAppointments(){
        navigationCommand.value =
                NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToPatientAppointments())
    }

    fun navigateToDoctorProfile() {
        navigationCommand.value =
            NavigationCommand.To(DoctorDashboardDirections.actionDoctorDashboardToDoctorProfileFragment())
    }
    fun navigateToPatientProfile(){
        navigationCommand.value =
            NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToPatientProfileFragment())
    }
}