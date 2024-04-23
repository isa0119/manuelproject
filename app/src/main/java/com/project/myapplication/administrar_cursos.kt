package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class administrar_cursos : AppCompatActivity() {
    private lateinit var DB:BD
    private lateinit var CursoViewModel : cursos_admin_viewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_cursos)
        DB = BD(this)
        CursoViewModel = cursos_admin_viewmodel(this)
        CursoViewModel.render()

        val RecyclerView:RecyclerView = findViewById(R.id.RecyclerCursosAdmin)
        CursoViewModel.Curso.observe(this){
            CursosList-> val adapter = Cursos_admin_adapter(
                CursosList,
                onEditClicked = { Curso-> val intent = Intent(this, Edit_Curso::class.java)
                    intent.putExtra("IDcurso", Curso.IDcurso.toInt())
                    startActivity(intent)
                },
                onDeleteClicked = { Curso-> EliminarCurso(Curso.IDcurso) })
            RecyclerView.adapter = adapter
            RecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
    fun EliminarCurso(IDcurso:Int){
        if (IDcurso > -1){
            if(DB.eliminarCurso(IDcurso)){
                val intent = Intent(this, administrar_cursos::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "fallo al eliminar curso", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "no se recibio valor del id del curso", Toast.LENGTH_SHORT).show()
        }
    }
}