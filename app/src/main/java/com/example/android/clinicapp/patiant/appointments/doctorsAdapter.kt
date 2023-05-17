package com.example.android.clinicapp.patiant.appointments

import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseRecyclerViewAdapter
import com.example.android.clinicapp.data.consts.Appointment
import com.example.android.clinicapp.data.consts.Doctor


//Use data binding to show the reminder on the item
class doctorsAdapter(callBack: (selectedAppointment: Doctor) -> Unit) :
    BaseRecyclerViewAdapter<Doctor>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.doctor
}