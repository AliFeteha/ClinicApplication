package com.example.android.clinicapp.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(app: Application) : BaseViewModel(app) {

    private val _finishedFlag = MutableLiveData(false)
    val finishedFlag :LiveData<Boolean>
        get() = _finishedFlag
    val id :MutableLiveData<String> = MutableLiveData(null)
    val name :MutableLiveData<String> = MutableLiveData("")
    val email :MutableLiveData<String> = MutableLiveData("")
    val password:MutableLiveData<String> = MutableLiveData("")
    val repo = Repo(app.applicationContext)
    val inputAccountType:MutableLiveData<String> = MutableLiveData("")
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
        Log.i(" ViewModel-Login",email.value.toString())
        if (validate(true))
            runBlocking {
                if (checkAccountValidity(email.value!!)) {
                    if (checkEmailAndPassword(email.value!!, password.value!!)) {
                        finishActivity()
                    }
                }
            }
    }
    fun register(){
        if (validate(false))
            if (checkAccountValidityReg(email.value!!)) {
                registerNewAccount()
                finishActivity()
            }
    }
    fun validate(loginType:Boolean): Boolean {
        Log.i(" ViewModel-Login",email.value.toString())
        if (loginType){
            if (email.value != "" || password.value != "")
                return true
            else
                showInvalidInput()
            return false
        }
        else{
            if (email.value != "" || password.value != "" || inputAccountType.value != "" || name.value != ""){
                if (password.value!!.length < 8)
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
        type = if (inputAccountType.value == "Doctor")
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


    //check if the account already existed in the email remote database table and return
    private fun checkAccountValidity(email:String) :Boolean{
            if (repo.verifyEmailExists(email))
                return true
            else
                invalidEmailLogin()
            return false
    }
    // check the email and password and return it's id with the call
    private suspend fun checkEmailAndPassword(email: String, password:String):Boolean{
        if (repo.authenticate(email, password)) {
            repo.loginAuth()
            return true
        } else {
            invalidPasswordLogin()
            return false
        }
    }



    //check if the account already existed in the email remote database table and return false if it does existed
    private fun checkAccountValidityReg(email:String) :Boolean{
        if (!repo.verifyEmailExists(email))
            return true
        else
            invalidEmail()
        return false
    }
    private fun registerNewAccount(){
        runBlocking {
             launch (Dispatchers.IO) {
                 repo.registerAuth(type!!, password.value!!)
             }
        }
    }
}