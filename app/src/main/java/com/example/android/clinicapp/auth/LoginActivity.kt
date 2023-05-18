package com.example.android.clinicapp.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.android.clinicapp.DoctorActivity
import com.example.android.clinicapp.PatientActivity
import com.example.android.clinicapp.R
import com.example.android.clinicapp.data.Remote
import com.example.android.clinicapp.data.consts.Type
import com.example.android.clinicapp.data.local.LocalDB
import com.example.android.clinicapp.databinding.LoginActivityBinding
import com.example.android.clinicapp.utils.PreferenceControl
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    val _viewModel: LoginViewModel by inject()
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFlow()
        _viewModel.finishedFlag.observe(this, Observer {
            if (it) {
                _viewModel.loggedIn()
                initializeLoginUI(_viewModel.type!!)
                _viewModel.finishedFlag.value = false
            }
        })

    }


    // a function to control the flow of the activity
    private fun activityFlow(){
        if (checkLogin() == null) {
            login()
        }
        else if (checkLogin() == Type.Doctor.toString()){
            initializeLoginUI(Type.Doctor)
        }else {
            initializeLoginUI(Type.Patient)
        }
        _viewModel.clear()
    }
    //Checks the saved preference if it already existed to require a login process
    private fun checkLogin(): String? {
//        PreferenceControl(applicationContext).clearPref()
        val type = PreferenceControl(applicationContext).readType()
        return type

    }
    //initializing login UI for authentication
    private fun login(){
      binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

    }
    //ending current activity
    private fun finishActivity() = finish()
    //initializing the main activity based on the user type
    private fun initializeLoginUI(type:Type){
        if (type == Type.Patient)
            startActivity(Intent(this, PatientActivity::class.java))
        else
            startActivity(Intent(this, DoctorActivity::class.java))

        finishActivity()
    }

}