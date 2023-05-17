package com.example.android.clinicapp.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.dashboard.DashboardViewModel
import com.example.android.clinicapp.databinding.FragmentDoctorDashboardBinding
import com.example.android.clinicapp.databinding.FragmentPatientDashboardBinding
import com.example.android.clinicapp.utils.PreferenceControl
import org.koin.android.ext.android.inject

class DoctorDashboard : BaseFragment() {
    override val _viewModel: DashboardViewModel by inject()
    lateinit var binding:FragmentDoctorDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_doctor_dashboard, container, false
        )
        binding.viewModel = _viewModel
        _viewModel.refreshDoctor()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.doctor.observe(viewLifecycleOwner){
            if (it.id != null){
                context?.let { it1 -> PreferenceControl(it1.applicationContext).write(_viewModel.doctor.value!!) }
            }
        }
    }
}