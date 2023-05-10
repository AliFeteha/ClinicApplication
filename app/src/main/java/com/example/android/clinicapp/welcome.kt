package com.example.android.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.android.clinicapp.databinding.FragmentWelcomeBinding

class welcome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
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