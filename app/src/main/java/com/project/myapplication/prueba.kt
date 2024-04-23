package com.project.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class prueba : AppCompatActivity() {
    private lateinit var DB:BD
    private lateinit var PreguntaViewModel : preguntasviewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)
        DB = BD(this)
        PreguntaViewModel = preguntasviewmodel(this)
        val pruebatitulo = intent.getStringExtra("Prueba")
        if (!pruebatitulo.isNullOrEmpty()){
            val TextView: TextView = findViewById(R.id.bienvenida)
            TextView.setText(pruebatitulo)
        }

        val PruebaID = intent.getIntExtra("IDprueba", -1)
        PreguntaViewModel.render(PruebaID)

        PreguntaViewModel.pregunta.observe(this){
                Preguntalist->
            val adapter = PreguntasAdapter(Preguntalist)
            val RecyclerView: RecyclerView = findViewById(R.id.recyclerPreguntaPrueba)
            RecyclerView.adapter = adapter
            RecyclerView.layoutManager = LinearLayoutManager(this)
            val btnVerificar: Button = findViewById(R.id.btnsubmit)
            btnVerificar.setOnClickListener {
                val puntos = adapter.getPuntos() // Obtén los puntos del adaptador
                Toast.makeText(this, "Puntos: $puntos", Toast.LENGTH_SHORT).show()
            }
        }

    }
}