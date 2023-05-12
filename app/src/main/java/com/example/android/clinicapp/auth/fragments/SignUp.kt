package com.example.android.clinicapp.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentSignUpBinding
import org.koin.android.ext.android.inject


class SignUp : BaseFragment() {
    override val _viewModel: LoginViewModel by  inject()
    lateinit var binding:FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)
        return binding.root
    }
}