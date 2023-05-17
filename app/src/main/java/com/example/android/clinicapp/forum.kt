package com.example.android.clinicapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.databinding.FragmentDoctorsBinding
import com.example.android.clinicapp.databinding.FragmentForumBinding
import com.example.android.clinicapp.patiant.appointments.BookViewModel
import com.example.android.clinicapp.patiant.appointments.fragments.BookAnAppointmentDirections
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class forum : BaseFragment() {
    override val _viewModel: BookViewModel by inject()
    lateinit var binding: FragmentForumBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_forum,container,false)
        binding.btnPilihKonselor.setOnClickListener {
            _viewModel.navigationCommand.value =
                NavigationCommand.To(forumDirections.actionForumToPatientDashboard())
        }
        return binding.root
    }


}