package com.example.android.clinicapp.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.*
import com.example.android.clinicapp.utils.PreferenceControl

class ProfileViewModel(app:Application) :BaseViewModel(app){

    val name : MutableLiveData<String> = MutableLiveData("")
    val email : MutableLiveData<String> = MutableLiveData("")
    val mobileNumber : MutableLiveData<String?> = MutableLiveData("")
    val city : MutableLiveData<String?> = MutableLiveData("")
    val gender: MutableLiveData<String?> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val repo = Repo(app.applicationContext)
    val app = app
    val address : MutableLiveData<String?> = MutableLiveData("")
    val birthDate : MutableLiveData<String?> = MutableLiveData("")
    val imgURL : MutableLiveData<String?> = MutableLiveData("")
    val blood : MutableLiveData<String?> = MutableLiveData("")
    val insuranceId : MutableLiveData<String> = MutableLiveData("")
    val insuranceProvider : MutableLiveData<String> = MutableLiveData("")
    val emergencyContact : MutableLiveData<String?> = MutableLiveData("")
    val contactNumber : MutableLiveData<String> = MutableLiveData("")
    val workingDays : MutableLiveData<List<Days>> = MutableLiveData(listOf())
    val spinnerItems:MutableLiveData<Int> = MutableLiveData(0)
    val firebaseControl : MutableLiveData<FirebaseControl> = MutableLiveData(FirebaseControl(email.value,"",""))
    var type: Type? = null
    val flag:MutableLiveData<Boolean> = MutableLiveData(false)
    private val patient: Patient
    private val doctor :Doctor

    fun navigateBack(){
            navigationCommand.value = NavigationCommand.Back
    }
    init {
        patient = PreferenceControl(app.applicationContext).readPatient()
        doctor = PreferenceControl(app.applicationContext).readDoctor()
        Log.i(" lolat neek", doctor.toString())

    }

    //todo saving mechanism to remote and append to preferance and database
    private fun convertToPatient(): Patient {
        patient.name = name.value
        patient.address = address.value
        patient.email = email.value
        patient.imageUrl = imgURL.value
        patient.mobilePhone = mobileNumber.value
        patient.city = city.value
        patient.gender = gender.value
        patient.id = PreferenceControl(app.applicationContext).readId()
        patient.bloodType = blood.value
        patient.name = name.value
        patient.insurance = MedicalInsurance(insuranceProvider.value!!,insuranceId.value!!)
        patient.emergencyContact = EmergencyContact(emergencyContact.value,contactNumber.value)
        patient.birthDate = birthDate.value
        val workingDay:MutableLiveData<Int> = MutableLiveData(0)
        return patient
    }

    private fun convertToDoctor(): Doctor {
        doctor.name = name.value
        doctor.address = address.value
        doctor.email = email.value
        doctor.city = city.value
        doctor.workingDays = workingDays.value
        doctor.telephone = mobileNumber.value
        doctor.id = PreferenceControl(app.applicationContext).readId()
        doctor.imageURL = imgURL.value
        return doctor
    }
    private fun invalidInput() {
        showSnackBarInt.value = R.string.invalidInputPart2
    }
    private fun validate(): Boolean {
        return (name.value != "" && email.value != "")
    }

    fun saveDoctor(){
        if (validate()) {
            if (PreferenceControl(app.applicationContext).readDoctor().email != email.value)
                checkAccountExist()
            else{
                modifyDoctor()
            }
        }
        else
            invalidInput()
    }
    fun invalidEmail(){
        showToast.value = "Email is already registered"
    }

    private fun modificationMessage(){
        showToast.value = "modified successfully"
    }

    fun modifyDoctor(){
        convertToDoctor()
        val oldEmail = PreferenceControl(app.applicationContext).readPatient().email
        PreferenceControl(app.applicationContext).write(doctor)
        repo.overrideSign(doctor,oldEmail!!,flag)
    }
    fun finishReg(){
        modificationMessage()
        navigateBack()
    }

    fun modifyPatient(){
        convertToPatient()
        val oldEmail = PreferenceControl(app.applicationContext).readPatient().email
        repo.overrideSignPatient(patient, oldEmail!!, flag)
        PreferenceControl(app.applicationContext).writePatient(patient)
    }

    //check if the account already existed in the email remote database table and return false if it does existed
    private fun checkAccountExist(){
        repo.verifyEmailExists(firebaseControl,email.value!!)
    }

    fun savePatient(){
        if (validate()) {
            if (PreferenceControl(app.applicationContext).readDoctor().email != email.value)
                checkAccountExist()
            else{
                modifyPatient()
            }
        }
        else
            invalidInput()

    }

    private fun loadSpinnerValue():Days{
        return when(spinnerItems.value){
            0->  Days.Sunday
            1->  Days.Monday
            2->  Days.Tuesday
            3->  Days.Wednesday
            4->  Days.Thursday
            5->  Days.Friday
            6->  Days.Saturday
            else -> Days.Sunday
        }
    }
    fun loadValues(){
        val type = PreferenceControl(app.applicationContext).readType()
        if (type == Type.Patient.toString())
            loadPatient()
        else
            loadDoctor()

    }

    private fun loadDoctor() {
        val doctor = PreferenceControl(app.applicationContext).readDoctor()
         name.value = doctor.name!!
         address.value = doctor.address!!
         email.value = doctor.email!!
         city.value = doctor.city!!
        workingDays.value = listOf(loadSpinnerValue())
         mobileNumber.value = doctor.telephone!!
         imgURL.value = doctor.imageURL!!

    }

    private fun loadPatient() {
        val patient = PreferenceControl(app.applicationContext).readPatient()

        Log.i(" testing from remote", patient.toString() + " ${name.value.toString()}")
          name.value = patient.name!!
          address.value = patient.address
          email.value = patient.email!!
          imgURL.value = patient.imageUrl
          mobileNumber.value = patient.mobilePhone
          city.value = patient.city
          gender.value = patient.gender
          blood.value = patient.bloodType
        insuranceProvider.value = patient.insurance.insuranceProvider
        insuranceId.value = patient.insurance.id
        emergencyContact.value = patient.emergencyContact.name
        birthDate.value = patient.birthDate

    }

}