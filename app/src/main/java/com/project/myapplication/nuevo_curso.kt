package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class nuevo_curso : AppCompatActivity() {
    private lateinit var DB:BD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_curso)
        DB = BD(this)

        val titulo: EditText = findViewById(R.id.CursoTitulo)
        val contenido: EditText = findViewById(R.id.Contenido)
        val btnsiguiente: Button = findViewById(R.id.btnsiguiente)

        btnsiguiente.setOnClickListener { nuevoCurso(titulo.text.toString(), contenido.text.toString()) }
    }
    fun nuevoCurso(Titulo:String, Contenido:String){
        if (Titulo.isNotEmpty() && Contenido.isNotEmpty()){
            if (DB.cursoexist(Titulo)){
                Toast.makeText(this, "ya existe un curso con este nombre", Toast.LENGTH_SHORT).show()
            }else{
                val query = DB.nuevocurso(Titulo, Contenido)
                if ( query != -1L){
                    val IDprueba = DB.nuevaPrueba(query.toInt(), Titulo)
                    val intent = Intent(this, nuevo_curso_prueba::class.java)
                    intent.putExtra("cursotitulo", Titulo)
                    intent.putExtra("IDprueba", IDprueba.toInt())
                    intent.putExtra("DIcurso", query)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "error al crear el curso", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this, "porfavor rellena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}