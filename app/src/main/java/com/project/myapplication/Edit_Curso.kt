package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Edit_Curso : AppCompatActivity() {
    private lateinit var DB : BD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_curso)
        DB = BD(this)
        val bntActualizar:Button = findViewById(R.id.btnActualizar)
        val btnPrueba:Button = findViewById(R.id.btnPrueba)
        val IDcurso = intent.getIntExtra("IDcurso", -1)
        if (IDcurso != -1){
            val query = DB.getCurso(IDcurso)
            if (query != null){
                if (query.moveToFirst()){
                    val Tituloquery = query.getString(query.getColumnIndex("nombre"))
                    val Titulo:EditText = findViewById(R.id.CursoTitulo)
                    Titulo.setText(Tituloquery)
                    val contenidoquery = query.getString(query.getColumnIndex("contenido"))
                    val contenido:EditText = findViewById(R.id.Contenido)
                    contenido.setText(contenidoquery)
                    bntActualizar.setOnClickListener { Actualizar(IDcurso, Titulo.text.toString(), contenido.text.toString()) }
                    btnPrueba.setOnClickListener { AccederPrueba(IDcurso) }
                }
            }else{
                Toast.makeText(this, "no se encontro el curso", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Error al recibir ID del curso", Toast.LENGTH_SHORT).show()
        }

    }
    fun Actualizar(IDcurso:Int, Titulo:String, contenido:String){
        if (Titulo.isNotEmpty() && contenido.isNotEmpty()){
            if (DB.actualizarCurso(IDcurso, Titulo, contenido)){
                val intent = Intent(this, administrar_cursos::class.java)
                startActivity(intent)
                finish()
            }
        }else{
            Toast.makeText(this, "no deje campos vacios", Toast.LENGTH_SHORT).show()
        }
    }
    fun AccederPrueba(IDcurso: Int){
        val query = DB.getPrueba(IDcurso)
        if (query != null){
            if (query.moveToFirst()){
                val IDprueba = query.getInt(query.getColumnIndex("idprueba"))
                val tituloprueba = query.getString(query.getColumnIndex("nombre"))
                val intent = Intent(this, nuevo_curso_prueba::class.java)
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