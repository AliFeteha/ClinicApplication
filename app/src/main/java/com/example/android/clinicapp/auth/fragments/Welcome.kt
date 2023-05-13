package com.example.android.clinicapp.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.data.consts.Days
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.Remote
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
        binding.viewModel = _viewModel
        val r = Remote()
        /*lifecycleScope.launch(Dispatchers.Main) {
            var a:MutableList<Appointment> =r.getAllAppointments();
            Log.i("Aaa",a.toString())
        }*/
        r.signUpDoctor(Doctor("cairo","aa","ssss","1235","aaaaa","ali","alii", "01212122121",listOf(Days.Friday)),"aaaaaaaaaa")
        Log.i("hey","from welcome")
        return binding.root
    }
}