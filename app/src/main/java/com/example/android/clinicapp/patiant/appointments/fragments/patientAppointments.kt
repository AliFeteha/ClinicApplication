package com.example.android.clinicapp.patiant.appointments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.dashboard.fragments.DoctorDashboardDirections
import com.example.android.clinicapp.dashboard.fragments.PatientDashboardDirections
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.databinding.FragmentPatientAppointmentsBinding
import com.example.android.clinicapp.patiant.appointments.AppointmentsViewModel
import com.example.android.clinicapp.patiant.appointments.appointmentsListAdapter
import com.example.android.clinicapp.utils.setup
import kotlinx.coroutines.runBlocking
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
        Log.i(" patientttttt","lolllllll")
        binding.viewModel = _viewModel
        binding.newAppointmentButton.setOnClickListener {
            _viewModel.navigationCommand.value =
                NavigationCommand.To(patientAppointmentsDirections.actionPatientAppointmentsToBookAnAppointment())

        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.case.observe(viewLifecycleOwner, Observer {
            runBlocking {
                Log.i(" testing123", it.toString())
                if (it != null)
                    _viewModel.repo.refreshDataBaseFromRemote(it)
            }

            _viewModel.getRecords()
        })
        setupRecyclerView()


    }


    private fun setupRecyclerView() {
        val adapter = appointmentsListAdapter {
        }
//        setup the recycler view using the extension function
        binding.appointmentsRecyclerView.setup(adapter)
    }
}