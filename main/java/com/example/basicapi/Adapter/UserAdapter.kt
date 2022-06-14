package com.example.basicapi.Adapter

import com.example.basicapi.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAdapter {

        @GET("users/{userId}")
        fun getUser(@Path("userId") id: String): Call<User>

        companion object {
            val BASE_URL = "https://jsonplaceholder.typicode.com"
            fun create(): UserAdapter {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                return retrofit.create(UserAdapter::class.java)
            }
        }
    }
