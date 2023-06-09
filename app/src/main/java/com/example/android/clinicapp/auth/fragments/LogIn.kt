package com.example.android.clinicapp.auth.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.clinicapp.R
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.data.Remote
import com.example.android.clinicapp.data.consts.Type
import com.example.android.clinicapp.databinding.FragmentLogInBinding
import com.example.android.clinicapp.utils.PreferenceControl
import org.koin.android.ext.android.inject

class LogIn : BaseFragment() {
    override val _viewModel: LoginViewModel by  inject()
    lateinit var binding:FragmentLogInBinding
    val remote = Remote()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_log_in,container,false)
        binding.viewModel = _viewModel
        return binding.root
    }

    //for observations
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.firebaseControl.observe(viewLifecycleOwner, Observer {
            if (_viewModel.email.value != "")
                _viewModel.loginValidityCallBack(it)
        })

        _viewModel.patient.observe(viewLifecycleOwner, Observer {
            if(it.id != null){
                _viewModel.type = Type.Patient
                PreferenceControl(requireContext()).writePatient(it!!)
                _viewModel.finishActivity()
            }
        })

        _viewModel.doctor.observe(viewLifecycleOwner, Observer {
            if(it.email != null){
                _viewModel.type = Type.Doctor
                PreferenceControl(requireContext()).write(it!!)
                _viewModel.finishActivity()
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        _viewModel.clear()
    }

}
