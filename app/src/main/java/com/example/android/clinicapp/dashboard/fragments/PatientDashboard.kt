package com.example.android.clinicapp.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.databinding.FragmentLogInBinding
import org.koin.android.ext.android.inject

class PatientDashboard : BaseFragment() {
    override val _viewModel: BaseViewModel by inject()
    lateinit var binding: FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_patient_dashboard,container,false)
        return binding.root

    }

}