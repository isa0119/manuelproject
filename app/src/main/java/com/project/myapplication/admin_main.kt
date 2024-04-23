package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class admin_main : AppCompatActivity() {
    private lateinit var SP:SharedPreferencess
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        SP = SharedPreferencess(this)

        val btnCerrar:Button = findViewById(R.id.CerrarSesion)
        btnCerrar.setOnClickListener { CerrarSesion() }
        val btnAddCurso:Button = findViewById(R.id.Cursos)
        btnAddCurso.setOnClickListener {
            val intent = Intent(this, nuevo_curso::class.java)
            startActivity(intent)
        }
        val btnAdministrarCursos:Button = findViewById(R.id.Miscursos)
        btnAdministrarCursos.setOnClickListener {
            val intent = Intent(this, administrar_cursos::class.java)
            startActivity(intent)
        }

    }
    fun CerrarSesion(){
        SP.isLoggedIn = false
        val intent = Intent(this, MainActivity::class.java)
        SP.CerrarSesion()
        startActivity(intent)
        finish()
    }
}