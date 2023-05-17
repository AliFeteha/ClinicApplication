package com.example.android.clinicapp.patiant.appointments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseFragment
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.databinding.FragmentBookAnAppointmentBinding
import com.example.android.clinicapp.patiant.appointments.BookViewModel
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class BookAnAppointment() : BaseFragment() {
    override val _viewModel: BookViewModel by inject()
    lateinit var binding: FragmentBookAnAppointmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_an_appointment, container, false
        )
        binding.newAppointmentButton.setOnClickListener {

            _viewModel.navigationCommand.value =
                NavigationCommand.To(BookAnAppointmentDirections.actionBookAnAppointmentToDoctors())
        }
            binding.Calender.setOnDateChangeListener(
                CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                    // In this Listener we are getting values
                    // such as year, month and day of month
                    // on below line we are creating a variable
                    // in which we are adding all the variables in it.
                    val Date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)
                    val pattern = "dd-MM-yyyy"
                    val millis = convertDateToMillis(Date, pattern)
                    _viewModel.date =millis

                })

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    fun convertDateToMillis(date: String, pattern: String): Long {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val parsedDate = dateFormat.parse(date)

        return parsedDate?.time ?: 0
    }



}