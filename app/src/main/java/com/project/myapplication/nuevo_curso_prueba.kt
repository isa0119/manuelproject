package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class nuevo_curso_prueba : AppCompatActivity() {
    private lateinit var DB:BD
    private lateinit var PreguntaViewModel : preguntasviewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_curso_prueba)
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
            val RecyclerView:RecyclerView = findViewById(R.id.recyclerPreguntaPrueba)
            RecyclerView.adapter = adapter
            RecyclerView.layoutManager = LinearLayoutManager(this)
        }

        val pregunta:EditText = findViewById(R.id.pregunta)
        pregunta.setText(PruebaID.toString())
        val respuesta:EditText = findViewById(R.id.respuesta)
        val opcion1:EditText = findViewById(R.id.opcion1)
        val opcion2:EditText = findViewById(R.id.opcion2)
        opcion2.text.toString()
        val opcion3:EditText = findViewById(R.id.opcion3)
        opcion3.text.toString()
        val btnAdd:Button = findViewById(R.id.btnadd)
        val btnend:Button = findViewById(R.id.btnend)

        btnAdd.setOnClickListener { if (pregunta.text.toString().isNotEmpty() &&
                                        respuesta.text.toString().isNotEmpty() &&
                                        opcion1.text.toString().isNotEmpty() &&
                                        opcion2.text.toString().isNotEmpty() &&
                                        opcion3.text.toString().isNotEmpty()){AddPregunta(PruebaID, pregunta.text.toString(), respuesta.text.toString(), opcion1.text.toString(), opcion2.text.toString(), opcion3.text.toString())}else{
            Toast.makeText(this, "rellena todos los campos", Toast.LENGTH_SHORT).show()
                                        } }
        btnend.setOnClickListener { val intent = Intent(this, admin_main::class.java)
            startActivity(intent)
            finish()}

    }
    fun AddPregunta(IDprueba:Int, Pregunta:String, Respuesta:String, opcion1:String, opcion2: String, opcion3: String){
        if (opcion1 == Respuesta || opcion2 == Respuesta || opcion3 == Respuesta){
            val query = DB.nuevaPregunta(IDprueba, Pregunta, Respuesta, opcion1, opcion2, opcion3)
            if (query != -1L){
                val intent = Intent(this, nuevo_curso_prueba::class.java)
                intent.putExtra("IDprueba", IDprueba)
                startActivity(intent)
                finish()
            }
        }else{
            Toast.makeText(this, "ninguna opcion coincide con la respuesta", Toast.LENGTH_SHORT).show()
        }

    }
}