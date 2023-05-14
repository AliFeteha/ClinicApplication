package com.example.android.clinicapp.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.android.clinicapp.R
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient
import com.example.android.clinicapp.data.consts.Type
import com.example.android.clinicapp.databinding.FragmentLogInBinding
import com.example.android.clinicapp.utils.PreferenceControl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class LogIn : BaseFragment() {
    override val _viewModel: LoginViewModel by  inject()
    lateinit var binding:FragmentLogInBinding
    private val repo = _viewModel.repo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_log_in,container,false)
        return binding.root
    }

}
