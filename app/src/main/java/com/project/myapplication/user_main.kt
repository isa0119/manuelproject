package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class user_main : AppCompatActivity() {
    private lateinit var SP:SharedPreferencess
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        SP= SharedPreferencess(this)

        val textView:TextView = findViewById(R.id.bienvenida)
        textView.text = "Bienvenido ${SP.user}"
        val btnCerrar:Button = findViewById(R.id.CerrarSesion)
        btnCerrar.setOnClickListener { CerrarSesion() }

    }
    fun CerrarSesion(){
        SP.isLoggedIn = false
        val intent = Intent(this, MainActivity::class.java)
        SP.CerrarSesion()
        startActivity(intent)
        finish()
    }
}