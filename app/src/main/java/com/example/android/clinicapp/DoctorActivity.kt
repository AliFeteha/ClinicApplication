package com.example.android.clinicapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.android.clinicapp.auth.LoginActivity
import com.example.android.clinicapp.databinding.DoctorActivityBinding
import com.example.android.clinicapp.utils.PreferenceControl


class DoctorActivity: AppCompatActivity() {

    private lateinit var binding:DoctorActivityBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLogin()
        binding = DataBindingUtil.setContentView(this, R.layout.doctor_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun checkLogin(){
        if (PreferenceControl(applicationContext).readId() == null)
            logout()
    }
    private fun logout(){
        PreferenceControl(applicationContext).clearPref()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}