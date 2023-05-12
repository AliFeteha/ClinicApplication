package com.example.android.clinicapp.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.dashboard.DashboardViewModel
import com.example.android.clinicapp.databinding.FragmentDoctorDashboardBinding
import org.koin.android.ext.android.inject

class DoctorDashboard : BaseFragment() {
    override val _viewModel: DashboardViewModel by inject()
    lateinit var binding:FragmentDoctorDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = _viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_dashboard, container, false)
    }

}