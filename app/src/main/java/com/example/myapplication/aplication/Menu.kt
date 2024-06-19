package com.example.myapplication.aplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btn: Button = findViewById(R.id.buttonBasicLevel)
        btn.setOnClickListener {
            val intent = Intent(this, NivelBasico::class.java)
            startActivity(intent)
        }

        val btn1: Button = findViewById(R.id.buttonIntermediateLevel)
        btn1.setOnClickListener {
            val intent = Intent(this, NivelMedio::class.java)
            startActivity(intent)
        }

        val btn2: Button = findViewById(R.id.buttonAdvancedLevel)
        btn2.setOnClickListener {
            val intent = Intent(this, nivelAvanzado::class.java)
            startActivity(intent)
        }

    }
}