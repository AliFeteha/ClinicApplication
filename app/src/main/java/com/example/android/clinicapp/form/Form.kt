package com.example.android.clinicapp.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.databinding.FragmentForumBinding
import com.example.android.clinicapp.patiant.appointments.BookViewModel
import org.koin.android.ext.android.inject

class Form : BaseFragment() {
    override val _viewModel: BookViewModel by inject()
    lateinit var binding: FragmentForumBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forum,container,false)
        binding.btnPilihKonselor.setOnClickListener {
            _viewModel.navigationCommand.value =
                NavigationCommand.Back
        }
        return binding.root
    }


}