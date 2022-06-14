package com.example.basicapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basicapi.Adapter.UserAdapter
import com.example.basicapi.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val userAPIService = UserAdapter.create()

    private fun init() {
        val btnSearchUser = findViewById<Button>(R.id.btn_user)

        btnSearchUser.setOnClickListener{
            this.retrieveUser()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()
    }




    @SuppressLint("WrongViewCast")
    fun retrieveUser() {
        val userID = findViewById<EditText>(R.id.textView_UserID)
        val name = findViewById<EditText>(R.id.textView_name)
        val username = findViewById<EditText>(R.id.textView_Username)
        val email = findViewById<EditText>(R.id.textView_Email)
        val a_Street = findViewById<EditText>(R.id.textView_a_Street)
        val a_Suite = findViewById<EditText>(R.id.textView_a_Suite)
        val a_City = findViewById<EditText>(R.id.textView_a_City)
        val a_ZipCode = findViewById<EditText>(R.id.textView_a_Zipcode)

        val requestUser = userAPIService.getUser(userID.text.toString())

        requestUser.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()

                if (user == null) {
                    Toast.makeText(this@MainActivity, "Invalid UserID ! Check Again", Toast.LENGTH_LONG)
                } else {
                    name.setText(user.name)
                    username.setText(user.username)
                    email.setText(user.email)
                    a_Street.setText(user.address.street)
                    a_Suite.setText(user.address.suite)
                    a_City.setText(user.address.city)
                    a_ZipCode.setText(user.address.zipcode)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Unable to retrieve data. .", Toast.LENGTH_LONG).show()
            }
        })
    }
}