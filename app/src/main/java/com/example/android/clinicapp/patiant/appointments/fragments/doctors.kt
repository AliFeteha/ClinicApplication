package com.example.android.clinicapp.patiant.appointments.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.room.InvalidationTracker
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.data.consts.Days
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.databinding.DoctorBinding
import com.example.android.clinicapp.databinding.FragmentDoctorsBinding
import com.example.android.clinicapp.patiant.appointments.BookViewModel
import com.example.android.clinicapp.patiant.appointments.appointmentsListAdapter
import com.example.android.clinicapp.patiant.appointments.doctorsAdapter
import com.example.android.clinicapp.utils.setup
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import java.util.*

class doctors() : BaseFragment() {
    override val _viewModel: BookViewModel by inject()
    lateinit var binding: FragmentDoctorsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_doctors,container,false)
        binding.viewModel = _viewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.case.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            runBlocking {
                Log.i(" testing123", it.toString())
                if (it != null){
                    _viewModel.repo.refreshDoctorsDataBaseFromRemote(it)
                }
            }
            _viewModel.getDoctor()
        })
        setupRecyclerView()


    }

    private fun setupRecyclerView() {
        val adapter = doctorsAdapter {
        }

//        setup the recycler view using the extension function
        binding.doctorsRecyclerView.setup(adapter)
        adapter.callback = {
            _viewModel.saveRecord(it)
            _viewModel.navigateToAppointments()

        }
    }

}