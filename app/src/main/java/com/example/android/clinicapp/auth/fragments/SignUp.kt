package com.example.android.clinicapp.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.clinicapp.R
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.base.BaseFragment
import org.koin.android.ext.android.inject


/**
 * A simple [Fragment] subclass.
 * Use the [signUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp : BaseFragment() {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    override val _viewModel: LoginViewModel by  inject()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}