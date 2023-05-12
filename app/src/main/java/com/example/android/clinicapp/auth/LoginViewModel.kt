package com.example.android.clinicapp.auth

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient
import com.example.android.clinicapp.data.consts.Type
import com.example.android.clinicapp.utils.PreferenceControl

class LoginViewModel(app: Application) : BaseViewModel(app) {

    private val _finishedFlag = MutableLiveData(false)
    val finishedFlag :LiveData<Boolean>
        get() = _finishedFlag
    val id:String?= null
    val name :String = ""
    val email :String = ""
    val password:String = ""
    private val inputAccountType:String = ""
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
    fun finishActivity(){
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
                if (password.length < 8)
                {
                    invalidPassword()
                    return false
                }
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
    fun invalidEmail(){
        showToast.value = "Already exciting email"
    }
    fun loggedIn(){
        showToast.value = "Logged In successfully"
    }
    fun registered(){
        showToast.value = "Signed Up successfully"
    }
    fun invalidEmailLogin(){
        showSnackBarInt.value = R.string.invalidEmail
    }
    private fun invalidPassword(){
        showSnackBarInt.value = R.string.invalidPassword
    }
    fun invalidPasswordLogin(){
        showSnackBarInt.value = R.string.invalidPasswordLogin
    }

}