package com.example.android.clinicapp.patiant.appointments

import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseRecyclerViewAdapter
import com.example.android.clinicapp.data.consts.Appointment


//Use data binding to show the reminder on the item
class appointmentsListAdapter(callBack: (selectedReminder: Appointment) -> Unit) :
    BaseRecyclerViewAdapter<Appointment>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.appointment
}