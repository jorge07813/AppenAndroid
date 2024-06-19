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

class MainActivity2 : AppCompatActivity() {

    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        authService = ApiClient.client.create(AuthService::class.java)

        val btnRegister: Button = findViewById(R.id.button_register)
        btnRegister.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        val btnLogin: Button = findViewById(R.id.button_login)
        btnLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.edit_text_email).text.toString()
            val password = findViewById<EditText>(R.id.edit_text_password).text.toString()

            val request = LoginRequest(email, password)
            authService.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        Toast.makeText(this@MainActivity2, "Welcome ${user?.username}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity2, Menu::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity2, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity2, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}