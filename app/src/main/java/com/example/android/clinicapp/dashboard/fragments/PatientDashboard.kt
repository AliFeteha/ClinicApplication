package com.example.android.clinicapp.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.dashboard.DashboardViewModel
import com.example.android.clinicapp.databinding.FragmentPatientDashboardBinding
import org.koin.android.ext.android.inject

class PatientDashboard : BaseFragment() {
    override val _viewModel: DashboardViewModel by inject()
    lateinit var binding:FragmentPatientDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_patient_dashboard, container, false
        )
        binding.viewModel = _viewModel
        return binding.root
    }

}