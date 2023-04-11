package com.example.loginscreen

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.loginscreen.fragments.ConnectionLoginFragment

class ConnectionActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.connection_activity)

        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main, ConnectionLoginFragment(), "login_screen")
            .commit()
    }
}