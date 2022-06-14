package com.example.basicapi.model

data class User(
    val id: Number,
    val name: String,
    val username: String,
    val email: String,
    val address: Address
)