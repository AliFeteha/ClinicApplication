package com.example.android.clinicapp.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentWelcomeBinding
import org.koin.android.ext.android.inject

class Welcome : BaseFragment() {

    override val _viewModel: LoginViewModel by  inject()

    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )
        binding.button1Welcome.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_welcome_to_logIn)
        }
        binding.button2Welcome.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_welcome_to_signUp)
        }


        return binding.root
    }
}