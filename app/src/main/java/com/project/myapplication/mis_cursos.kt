package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class mis_cursos : AppCompatActivity() {
    private lateinit var DB:BD
    private lateinit var CursoViewModel : cursos_viewModel
    private lateinit var SP : SharedPreferencess
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_cursos)
        SP = SharedPreferencess(this)
        DB = BD(this)
        CursoViewModel = cursos_viewModel(this)
        CursoViewModel.rendermiscursos(SP.iduser)

        val RecyclerView: RecyclerView = findViewById(R.id.recyclerviewCursos)
        CursoViewModel.Curso_user.observe(this){
                Cursoslist-> val adapter = mis_cursos_adapter(
            Cursoslist,
            onSelectClicked = {
                    Curso-> val intent = Intent(this, ver_curso::class.java)
                intent.putExtra("IDcurso", Curso.IDcurso)
                startActivity(intent)
            },
            onDeleteClicked = {
                    Curso-> eliminarFavoritos(Curso.IDcurso)
            })
            RecyclerView.adapter = adapter
            RecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
    fun eliminarFavoritos(IDcurso:Int){
        if (IDcurso > -1){
            if(DB.eliminarmicurso(IDcurso)){
                val intent = Intent(this, mis_cursos::class.java)
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