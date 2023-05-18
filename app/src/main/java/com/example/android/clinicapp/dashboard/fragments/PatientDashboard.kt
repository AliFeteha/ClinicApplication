package com.example.android.clinicapp.dashboard.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.dashboard.DashboardViewModel
import com.example.android.clinicapp.data.Remote
import com.example.android.clinicapp.databinding.FragmentPatientDashboardBinding
import com.example.android.clinicapp.utils.PreferenceControl
import org.koin.android.ext.android.inject

class PatientDashboard : BaseFragment() {
    override val _viewModel: DashboardViewModel by inject()
    lateinit var binding: FragmentPatientDashboardBinding
    private var r = Remote()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_patient_dashboard,container,false)
        binding.viewModel = _viewModel
       _viewModel.refreshPatient()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.patient.observe(viewLifecycleOwner){
            if (it.id != null){
                context?.let { it1 -> PreferenceControl(it1.applicationContext).writePatient(_viewModel.patient.value!!) }
            }
        }
    }

}