package com.example.myapplication.aplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val contrasena: String)
data class LoginResponse(val token: String, val username: String)

data class RegisterRequest(val email: String, val contrasena: String)
data class RegisterResponse(val message: String, val userId: Int)

interface AuthService {
    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("users/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}