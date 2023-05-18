package com.example.android.clinicapp.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.ProfilePatientBinding
import org.koin.android.ext.android.inject


class PatientProfileFragment: BaseFragment() {
    override val _viewModel: ProfileViewModel by inject()
    lateinit var binding: ProfilePatientBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.profile_patient,container,false)
        binding.viewModel = _viewModel

        _viewModel.loadValues()
        _viewModel.firebaseControl.observe(viewLifecycleOwner) {
            if (it.email == null && it.password != "")
                _viewModel.modifyPatient()
            else
                _viewModel.invalidEmail()
        }
        _viewModel.flag.observe(viewLifecycleOwner){
            if (it){
                _viewModel.finishReg()
                _viewModel.flag.value = false
            }
        }
        return binding.root
    }

}