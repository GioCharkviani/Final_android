package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.adapters.UserRecyclerAdapter.Companion.USER_ID
import com.example.myapplication.api.RestClient
import com.example.myapplication.api.dto.ReqResData
import com.example.myapplication.api.dto.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var nameView: TextView
    private lateinit var mailView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        imageView = findViewById(R.id.imageView2)
        nameView = findViewById(R.id.textView3)
        mailView = findViewById(R.id.textView4)
        val userId = intent.extras?.getLong(USER_ID, -1)
        if (userId != -1L){
            RestClient.reqResApi.getUser(userId!!).enqueue(object : Callback<ReqResData<User>>{
                override fun onResponse(
                    call: Call<ReqResData<User>>,
                    response: Response<ReqResData<User>>
                ) {
                    if (response.isSuccessful){
                        response.body()?.data?.let {
                            Picasso.get().load(it.avatar).into(imageView);
                            nameView.text = "${it.firstName} ${it.lastName}"
                            mailView.text = it.email
                        }
                    }
                }

                override fun onFailure(call: Call<ReqResData<User>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        var logOutBtn = findViewById<TextView>(R.id.log_out) as TextView;
        var textViewMain = findViewById<TextView>(R.id.textViewMain) as TextView;
        var topUsersBtn = findViewById<TextView>(R.id.topUsersBtn) as TextView;

        logOutBtn.setOnClickListener {
            val switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        }

        textViewMain.setOnClickListener {
            val switchActivityIntent = Intent(this, HomePageActivity::class.java)
            startActivity(switchActivityIntent)
        }

        topUsersBtn.setOnClickListener {
            val switchActivityIntent = Intent(this, TopUsersActivity::class.java)
            startActivity(switchActivityIntent)
        }

    }
}