package com.example.android.clinicapp.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.ProfilePatientBinding
import org.koin.android.ext.android.inject


class PatientProfileFragment: BaseFragment() {
    override val _viewModel: ProfileViewModel by inject()
    lateinit var binding: ProfilePatientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = _viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_patient, container, false)
    }

}