package com.example.myapplication.aplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration : AppCompatActivity() {
    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        authService = ApiClient.client.create(AuthService::class.java)

        val btn: Button = findViewById(R.id.createAccountButton)
        btn.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailInput).text.toString()
            val password = findViewById<EditText>(R.id.passwordInput).text.toString()

            val request = RegisterRequest(email, password)
            authService.register(request).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        Toast.makeText(this@Registration, "Registration successful. User ID: ${registerResponse?.userId}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Registration, MainActivity2::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@Registration, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@Registration, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}