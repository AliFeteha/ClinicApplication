package com.example.android.clinicapp.auth

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.data.consts.Type

class LoginViewModel(app: Application) : BaseViewModel(app) {

    private val _finishedFlag = MutableLiveData(false)
    val finishedFlag :LiveData<Boolean>
        get() = _finishedFlag
    val name :String = ""
    val email :String = ""
    val password:String = ""
    val inputAccountType:String = ""
    var type:Type? = null
    val login = MutableLiveData(false)
    val register = MutableLiveData(false)
    // Welcoming and login viewModel
    fun navigateToLogin(){
        navigationCommand.value = NavigationCommand.To(WelcomeDirections.actionWelcomeToLogIn())
    }
    fun navigateToRegistration(){
        navigationCommand.value = NavigationCommand.To(WelcomeDirections.actionWelcomeToSignUp())
    }
    fun navigateToDashBoard(){
        _finishedFlag.value = true
    }
    fun login(){
        if (validate(true)){
            login.value = true
        }
    }
    fun register(){
        if (validate(false)){
            register.value = true
        }
    }
    fun validate(loginType:Boolean): Boolean {
        if (loginType){
            if (email != "" || password != "")
                return true
            else
                showInvalidInput()
            return false
        }
        else{
            if (email != "" || password != "" || inputAccountType != "" || name != ""){
                convertType()
                return true
            }
            else
                showInvalidInput()
            return false
        }


    }
    private fun convertType(){
        type = if (inputAccountType == "Doctor")
            Type.Doctor
        else
            Type.Patient
    }
    private fun showInvalidInput(){
        showSnackBarInt.value = R.string.invalidInput
    }

}