package com.example.android.clinicapp.patients

import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseRecyclerViewAdapter
import com.example.android.clinicapp.data.consts.Appointment


//Use data binding to show the reminder on the item
class patientsListAdapter(callBack: (selectedReminder: Appointment) -> Unit) :
    BaseRecyclerViewAdapter<Appointment>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.patient
}