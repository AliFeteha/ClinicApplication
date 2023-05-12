package com.example.android.clinicapp.dashboard

import android.app.Application
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.dashboard.fragments.DoctorDashboardDirections
import com.example.android.clinicapp.dashboard.fragments.PatientDashboardDirections
import com.example.android.clinicapp.data.consts.Type

class DashboardViewModel(val app:Application): BaseViewModel(app) {


    fun navigateToAppointments(type:String){
        if (type == Type.Doctor.toString())
            navigationCommand.value =
                NavigationCommand.To(DoctorDashboardDirections.actionDoctorDashboardToPatientAppointments())
        else
            navigationCommand.value =
                NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToPatientAppointments())
    }

    fun navigateToForm(type:String){
        if (type == Type.Doctor.toString())
            navigationCommand.value =
                NavigationCommand.To(DoctorDashboardDirections.actionWelcomeToLogIn())
        else
            navigationCommand.value =
                NavigationCommand.To(PatientDashboardDirections.actionPatientDashboardToPatientAppointments())
    }

}