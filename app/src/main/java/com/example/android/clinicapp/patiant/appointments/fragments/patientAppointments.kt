package com.example.android.clinicapp.patiant.appointments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentPatientAppointmentsBinding
import com.example.android.clinicapp.patiant.appointments.AppointmentsViewModel
import com.example.android.clinicapp.patiant.appointments.appointmentsListAdapter
import com.example.android.clinicapp.utils.PreferenceControl
import com.example.android.clinicapp.utils.setup
import org.koin.android.ext.android.inject

class patientAppointments() : BaseFragment() {
    override val _viewModel: AppointmentsViewModel by inject()
    private lateinit var binding: FragmentPatientAppointmentsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_patient_appointments, container, false)
            binding.newAppointmentButton.visibility = View.GONE

        binding.viewModel = _viewModel
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