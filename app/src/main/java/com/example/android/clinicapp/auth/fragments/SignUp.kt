package com.example.android.clinicapp.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.clinicapp.R
import com.example.android.clinicapp.auth.LoginActivity
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentSignUpBinding
import com.example.android.clinicapp.utils.PreferenceControl
import org.koin.android.ext.android.inject
import java.util.prefs.PreferenceChangeEvent


class SignUp : BaseFragment() {
    override val _viewModel: LoginViewModel by  inject()
    lateinit var binding:FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)
        binding.viewModel = _viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.register.observe(viewLifecycleOwner, Observer {
            if (it) {
                if(checkAccountValidity(_viewModel.email)){
                    registerNewAccount()
                    // todo write a fn to write the current profile to the preference file
                    getProfileId(email = _viewModel.email)
                    _viewModel.writePreference()
                    _viewModel.finishActivity()
                }

            }
            _viewModel.register.value = false
        })

    }

    private fun getProfileId(email:String) {
        TODO("make a remote call and get the id of the profile and write it to (_viewModel.id)")
        _viewModel.id = TODO()
    }

    private fun checkAccountValidity(email:String) :Boolean{
        //Todo check if the account already existed in the email remote database table and return false if it does existed
        if (TODO( "fn call to return if it wasn't existed"))
            return true
        else
            _viewModel.invalidEmail()
        return false
    }
    private fun registerNewAccount(){
        //todo register new profile with the viewmodel's value name, email, password.....etc
    }


}