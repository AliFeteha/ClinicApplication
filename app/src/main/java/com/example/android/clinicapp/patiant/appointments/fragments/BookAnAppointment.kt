package com.example.android.clinicapp.patiant.appointments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentBookAnAppointmentBinding
import com.example.android.clinicapp.patiant.appointments.AppontmentsViewModel
import org.koin.android.ext.android.inject

class BookAnAppointment : BaseFragment() {

    override val _viewModel: AppontmentsViewModel by inject()
    lateinit var binding: FragmentBookAnAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_an_appointment, container, false
        )
        binding.viewModel = _viewModel
        return binding.root
    }
}