package com.example.android.clinicapp.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.clinicapp.R
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentLogInBinding
import org.koin.android.ext.android.inject

class LogIn : BaseFragment() {
    override val _viewModel: LoginViewModel by  inject()
    lateinit var binding:FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_log_in,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.login.observe(viewLifecycleOwner, Observer {
            if (it) {
                if(checkAccountValidity(_viewModel.email)){
                    if (checkEmailAndPassword(_viewModel.email,_viewModel.password)) {
                        _viewModel.writePreference()
                        _viewModel.finishActivity()
                    }
                }

            }
            _viewModel.register.value = false
        })

    }

    private fun checkAccountValidity(email:String) :Boolean{
        //Todo check if the account already existed in the email remote database table and return
        // ----->True<----- if it does existed
        if (TODO(" fn call to return if it does existed"))
            return true
        else
            _viewModel.invalidEmailLogin()
        return false
    }
    private fun checkEmailAndPassword(email: String,Password:String):Boolean{
        // todo check the email and password and return it's id with the call and write it to the _viewModel.id
        if (TODO("if valid")) {
            _viewModel.id = TODO("corresponding Id")
            return true
        }
        else {
            _viewModel.invalidPasswordLogin()
            return false
        }
    }



}
