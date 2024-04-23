package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ver_curso : AppCompatActivity() {
    private lateinit var DB : BD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_curso)
        DB = BD(this)
        val btnPrueba:Button = findViewById(R.id.btnprueba)
        val IDcurso = intent.getIntExtra("IDcurso", -1)
        if (IDcurso != -1){
            val query = DB.getCurso(IDcurso)
            if (query != null){
                if (query.moveToFirst()){
                    val Tituloquery = query.getString(query.getColumnIndex("nombre"))
                    val Titulo: TextView = findViewById(R.id.bienvenida)
                    Titulo.setText(Tituloquery)
                    val contenidoquery = query.getString(query.getColumnIndex("contenido"))
                    val contenido: TextView = findViewById(R.id.Contenido)
                    contenido.setText(contenidoquery)
                    btnPrueba.setOnClickListener { AccederPrueba(IDcurso) }
                }
            }else{
                Toast.makeText(this, "no se encontro el curso", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Error al recibir ID del curso", Toast.LENGTH_SHORT).show()
        }

    }
    fun AccederPrueba(IDcurso: Int){
        val query = DB.getPrueba(IDcurso)
        if (query != null){
            if (query.moveToFirst()){
                val IDprueba = query.getInt(query.getColumnIndex("idprueba"))
                val tituloprueba = query.getString(query.getColumnIndex("nombre"))
                val intent = Intent(this, prueba::class.java)
                intent.putExtra("IDprueba", IDprueba.toInt())
                intent.putExtra("Prueba", tituloprueba)
                startActivity(intent)
                finish()
            }
        }else{
            Toast.makeText(this, "no se encontraron resultados de pruebas", Toast.LENGTH_SHORT).show()
        }
    }
}