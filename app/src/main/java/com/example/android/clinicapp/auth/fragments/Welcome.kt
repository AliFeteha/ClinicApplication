package com.example.android.clinicapp.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentWelcomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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
        binding.viewModel = _viewModel
        val r = remote()
        /*lifecycleScope.launch(Dispatchers.Main) {
            var a:MutableList<Appointment> =r.getAllAppointments();
            Log.i("Aaa",a.toString())
        }*/
        r.signUpDoctor(Doctor("cairo","aa","ssss","1235","aaaaa","ali","alii", listOf(Days.Friday)),"aaaaaaaaaa")
        Log.i("hey","from welcome")
        return binding.root
    }
}