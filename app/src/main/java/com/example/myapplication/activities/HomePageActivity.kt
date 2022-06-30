package com.example.myapplication.activities

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class HomePageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        var logOutBtn = findViewById<TextView>(R.id.log_out) as TextView;
        var topUsersBtn = findViewById<TextView>(R.id.topUsersBtn) as TextView;

        logOutBtn.setOnClickListener {
            val switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        }

        topUsersBtn.setOnClickListener {
            val switchActivityIntent = Intent(this, TopUsersActivity::class.java)
            startActivity(switchActivityIntent)
        }

        val reciver: BroadcastReceiver = MyBroadcastReceiver()
        val filterI = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(reciver, filterI)
    }
}