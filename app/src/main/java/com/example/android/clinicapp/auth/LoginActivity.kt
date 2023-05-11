package com.example.android.clinicapp.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.android.clinicapp.DoctorActivity
import com.example.android.clinicapp.PatientActivity
import com.example.android.clinicapp.R
import com.example.android.clinicapp.data.consts.Type
import com.example.android.clinicapp.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFlow()
    }

    // a function to control the flow of the activity
    private fun activityFlow(){
        if (checkLogin() == null) {
            login()
        }
        else if (checkLogin() == Type.Doctor.toString()){
            initializeLoginUI(Type.Doctor)
            finishActivity()
        }else {
            initializeLoginUI(Type.Patient)
            finishActivity()
        }
    }
    //Checks the saved preference if it already existed to require a login process
    private fun checkLogin(): String? {
        val sharedPreferences = getSharedPreferences(getString(R.string.user_preferance_file), MODE_PRIVATE)
        return sharedPreferences.getString(getString(R.string.preference_id),null)

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
    }

}