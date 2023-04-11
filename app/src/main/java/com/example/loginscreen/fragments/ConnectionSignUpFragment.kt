package com.example.loginscreen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.loginscreen.R
import com.example.loginscreen.viewModels.connection.ConnectionViewModel

class ConnectionSignUpFragment : Fragment() {
    lateinit var connectionVW: ConnectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        connectionVW = ViewModelProvider(requireActivity())[ConnectionViewModel::class.java]
        return inflater.inflate(R.layout.connection_signup_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backArrow = view.findViewById<ImageView>(R.id.iv_signup_screen_arrow_back)
        val btnLogin = view.findViewById<Button>(R.id.btn_signup_screen_login)

        backArrow.setOnClickListener {
            goToLoginFragment()
        }
        btnLogin.setOnClickListener {
            goToLoginFragment()
        }

    }

    private fun goToLoginFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_main, ConnectionLoginFragment(), "login_screen")
            ?.commit()
    }
}