package com.example.android.clinicapp.profile

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.example.android.clinicapp.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getTextArray
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.databinding.ProfileDoctorBinding
import org.koin.android.ext.android.inject


class DoctorProfileFragment: BaseFragment() {
    override val _viewModel: ProfileViewModel by inject()
    lateinit var binding: ProfileDoctorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = _viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_doctor, container, false)
    }
    //todo to be tested in the doctor profile view
    fun onClick(view:View) {
            val d :Dialog = Dialog(requireContext(), R.style.Theme_ClinicApp)
            d.setContentView(R.layout.profile_doctor)
            val list:Array<String> = resources.getStringArray(R.array.daysOfWeek)
            val adapter :ArrayAdapter<String>  = ArrayAdapter<String>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val s : Spinner=d.findViewById(R.id.workingDaysSpinner)
            s.adapter = adapter
            d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            d.show()
        }
}