package com.example.android.clinicapp.profile

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.example.android.clinicapp.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.ProfileDoctorBinding
import org.koin.android.ext.android.inject


class DoctorProfileFragment: BaseFragment() {
    override val _viewModel: ProfileViewModel by inject()
    lateinit var binding: ProfileDoctorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_doctor,container,false)
        binding.viewModel = _viewModel
        _viewModel.loadValues()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.firebaseControl.observe(viewLifecycleOwner, Observer {
            if (it.email == null)
                _viewModel.modifyDoctor()
            else
                _viewModel.invalidEmail()
        })
    }

}