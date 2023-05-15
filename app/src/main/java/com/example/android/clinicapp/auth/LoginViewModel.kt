package com.example.android.clinicapp.auth

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.R
import com.example.android.clinicapp.base.BaseViewModel
import com.example.android.clinicapp.base.NavigationCommand
import com.example.android.clinicapp.data.Repo
import com.example.android.clinicapp.data.consts.FirebaseControl
import com.example.android.clinicapp.data.consts.Type
import com.example.android.clinicapp.utils.PreferenceControl
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
    val inputAccountType:MutableLiveData<Int> = MutableLiveData(1)
    val showLoadingBar:MutableLiveData<Boolean> = MutableLiveData(false)
    var type:Type? = null
    val firebaseControl:MutableLiveData<FirebaseControl> = MutableLiveData()

    fun clear(){
        name.value = ""
        email.value = ""
        password.value = ""
    }
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
        if (validate(true))
                checkAccountValidity(email.value!!)
            }

    fun writeType(){
        if (inputAccountType.value == 0){
            type = Type.Doctor
        }
        else
            type = Type.Patient
    }
    fun writePref(id:String?){
        writeType()
        if (id != null) {
            PreferenceControl(context = getApplication<Application>().applicationContext).writeId(id)
        }
        PreferenceControl(context = getApplication<Application>().applicationContext).writeType(type!!)
        PreferenceControl(context = getApplication<Application>().applicationContext).write(name.value!!,email.value!!)
    }
    fun register(){
        if (validate(false))
            checkAccountValidityReg(email.value!!)
    }
    fun validate(loginType:Boolean): Boolean {
        if (loginType){
            if (email.value != "") {
                if (password.value != "")
                    return true
                else
                    invalidPasswordLogin()
            }
            else
                showInvalidInput()
            return false
        }
        else{
            if (email.value != "" || password.value != "" || name.value != ""){
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
        type = if (inputAccountType.value == 0)
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
    private fun checkAccountValidity(email:String) {
        repo.verifyEmailExists(firebaseControl,email)

    }
    // check the email and password and return it's id with the call
//    private suspend fun checkEmailAndPassword(email: String, password:String):Boolean{
//        repo.loginAuth()
//        return true
//    }




    //check if the account already existed in the email remote database table and return false if it does existed
    private fun checkAccountValidityReg(email:String){
            repo.verifyEmailExists(firebaseControl,email)
    }

    private fun registerNewAccount(){
        writePref(null)
        runBlocking {
             launch (Dispatchers.IO) {
                 repo.registerAuth(type!!, password.value!!)
             }
            finishActivity()
        }
    }

    fun validityCallBackRegistration(firebaseControl: FirebaseControl){
        if (firebaseControl.email == null)
            registerNewAccount()
        else
            invalidEmail()
    }

    fun loginValidityCallBack(firebaseControl: FirebaseControl){
        if (firebaseControl.email != null)
            if (firebaseControl.email == email.value && password.value == firebaseControl.password) {
                writePref(firebaseControl.id!!)
                finishActivity()
            }
        else
            invalidEmailLogin()
    }
}