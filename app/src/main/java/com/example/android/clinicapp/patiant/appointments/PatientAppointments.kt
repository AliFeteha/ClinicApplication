package com.example.android.clinicapp.patiant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.databinding.FragmentPatientAppointmentsBinding
import com.example.android.clinicapp.patiant.appointments.AppointmentViewModel
import com.example.android.clinicapp.patiant.appointments.appointmentsListAdapter
import com.example.android.clinicapp.utils.PreferenceControl
import com.example.android.clinicapp.utils.setup

class patientAppointments(override val _viewModel: AppointmentViewModel) : BaseFragment() {
    private lateinit var binding: FragmentPatientAppointmentsBinding
    private val preferenceControl=PreferenceControl()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_patient_appointments, container, false)
        binding.viewModel = _viewModel
        if(preferenceControl.readType()=="Doctor"){
            binding.newAppointmentButton.visibility = View.GONE
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        setupRecyclerView()
        _viewModel.getRecoeds()
    }
    private fun setupRecyclerView() {
        val adapter = appointmentsListAdapter {
        }
//        setup the recycler view using the extension function
        binding.appointmentsRecyclerView.setup(adapter)
    }
}