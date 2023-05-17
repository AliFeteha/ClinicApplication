package com.example.android.clinicapp.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.FragmentPatientsBinding
import com.example.android.clinicapp.utils.PreferenceControl
import com.example.android.clinicapp.utils.setup
import org.koin.android.ext.android.inject

class Patients : BaseFragment() {
    override val _viewModel: PatientsViewModel by inject()
    private lateinit var binding: FragmentPatientsBinding
    private val preferenceControl= context?.let { PreferenceControl(it) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_patients, container, false)
        binding.viewModel = _viewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
    }
    private fun setupRecyclerView() {
        val adapter = patientsListAdapter {
        }
//        setup the recycler view using the extension function
        binding.patientsRecyclerView.setup(adapter)
    }
}