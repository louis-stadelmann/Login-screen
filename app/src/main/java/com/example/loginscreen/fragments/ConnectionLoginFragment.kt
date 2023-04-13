package com.example.loginscreen.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.loginscreen.R
import com.example.loginscreen.viewModels.connection.ConnectionViewModel

class ConnectionLoginFragment : Fragment() {
    private lateinit var connectionVW: ConnectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        connectionVW = ViewModelProvider(requireActivity())[ConnectionViewModel::class.java]
        return inflater.inflate(R.layout.connection_login_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginBtn = view.findViewById<Button>(R.id.btn_signup_screen_login)
        val signupBtn = view.findViewById<Button>(R.id.btn_login_screen_signup)

        val frameLayout = view.findViewById<FrameLayout>(R.id.fl_signup_screen)
        val screen = view.findViewById<ConstraintLayout>(R.id.cl_login_screen)
        val etPseudo = view.findViewById<EditText>(R.id.et_signup_screen_pseudo)
        val etPassword = view.findViewById<EditText>(R.id.et_signup_screen_password)
        val tvPseudo = view.findViewById<TextView>(R.id.tv_login_screen_pseudo)
        val tvWrongLogin = view.findViewById<TextView>(R.id.tv_login_screen_wrong_password)

        connectionVW.username.observe(viewLifecycleOwner) {
            tvPseudo.text = it.toString()
        }

        connectionVW.token.observe(viewLifecycleOwner) {
            if (TextUtils.isEmpty(it) && !TextUtils.equals(it, "null")) {
                tvWrongLogin.visibility = View.VISIBLE
            }
        }

        frameLayout.visibility = View.GONE

        loginBtn.setOnClickListener {
            if (frameLayout.visibility == View.GONE) {
                show(frameLayout)
            } else {
                //TODO implement call to login
            }
        }

        signupBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fl_main, ConnectionSignUpFragment(), "signup_screen")
                ?.commit()
        }

        screen.setOnClickListener {
            if (frameLayout.visibility == View.VISIBLE) {
                hide(frameLayout)
                if (tvWrongLogin.visibility == View.VISIBLE) {
                    tvWrongLogin.visibility = View.INVISIBLE
                }
            }
        }

        etPseudo.doAfterTextChanged {
            connectionVW.setUsername(it.toString())
        }

        etPassword.doAfterTextChanged {
            connectionVW.setPassword(it.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hide(view: View) {
        view.animate()
            .translationY(activity?.windowManager?.currentWindowMetrics?.bounds?.height()?.toFloat()
                ?: 1000f)
            .alpha(0f)
            .setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.GONE
                }
            })
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun show(view: View) {
        view.alpha = 0f
        view.y = activity?.windowManager?.currentWindowMetrics?.bounds?.height()?.toFloat() ?: 1000f
        view.visibility = View.VISIBLE

        view.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(200)
            .setListener(null)

    }
}