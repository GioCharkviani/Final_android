package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.UserRecyclerAdapter
import com.example.myapplication.api.RestClient
import com.example.myapplication.api.dto.ReqResData
import com.example.myapplication.api.dto.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopUsersActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_users)

        recyclerView = findViewById(R.id.recyclerView)
        RestClient.initClient()
        RestClient.reqResApi.getUsers(2).enqueue(object : Callback<ReqResData<List<User>>>{
            override fun onResponse(
                call: Call<ReqResData<List<User>>>,
                response: Response<ReqResData<List<User>>>
            ) {
                if (response.isSuccessful){
                    response.body()?.data?.let {
                        recyclerView.adapter = UserRecyclerAdapter(it)
                        recyclerView.layoutManager = LinearLayoutManager(this@TopUsersActivity)
                    }
                }
            }

            override fun onFailure(call: Call<ReqResData<List<User>>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        var logOutBtn = findViewById<TextView>(R.id.log_out) as TextView;
        var textViewMain = findViewById<TextView>(R.id.textViewMain) as TextView;

        logOutBtn.setOnClickListener {
            val switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        }

        textViewMain.setOnClickListener {
            val switchActivityIntent = Intent(this, HomePageActivity::class.java)
            startActivity(switchActivityIntent)
        }
    }
}