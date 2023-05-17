package com.example.android.clinicapp.patiant.appointments

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.dashboard.fragments.PatientDashboardDirections
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Appointment
import com.example.android.clinicapp.data.consts.Days
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.patiant.appointments.fragments.doctorsDirections
import com.example.android.clinicapp.patiant.appointments.fragments.patientAppointmentsDirections
import com.example.android.clinicapp.utils.PreferenceControl
import com.example.android.clinicapp.utils.TypeConverter
import kotlinx.coroutines.launch
import java.util.*

class BookViewModel(app: Application) : BaseViewModel(app) {
    var date:Long =0
    var preferenceControl = PreferenceControl(app.applicationContext)
    val typeConverter = TypeConverter()
    val repo = Repo(app.applicationContext)
    private val context = app.applicationContext
    var Doctors = MutableLiveData<List<Doctor>>(listOf())
    var case:MutableLiveData<List<Doctor>> = MutableLiveData()
    init{
        Log.i(" gettt","init")
        viewModelScope.launch {
            repo.refreshAllDoctors(case)
        }
    }
    fun getDoctor(){
        try {

            viewModelScope.launch{
                repo.getDoctors(Doctors,
                    typeConverter.fromStringsToDays(getDayNameFromMillis(date))!!
                )
                Log.i(" aloooo t test data coming or not ",Doctors.toString())
            }
        }catch (e:Exception){
            Log.i(" erorrrrrrrr",e.toString())
        }
    }
    fun saveRecord(doctor: Doctor){
        viewModelScope.launch{
            val days = typeConverter.daysListToString(doctor.workingDays!!).trim('[',']')
            repo.addAppointment(Appointment(days,
                doctor.id!!, doctor.name!!, UUID.randomUUID().toString(),
                preferenceControl.readId()!!, preferenceControl.readName()!!,"tenth appointment"))
        }
    }
    fun navigateToAppointments(){
        navigationCommand.value =
            NavigationCommand.To(doctorsDirections.actionDoctorsToPatientDashboard())
    }
    fun getDayNameFromMillis(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val dayNames = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday")

        return dayNames[dayOfWeek - 1]
    }

}