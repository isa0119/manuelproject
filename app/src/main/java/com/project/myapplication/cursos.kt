package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class cursos : AppCompatActivity() {
    private lateinit var DB:BD
    private lateinit var CursoViewModel : cursos_viewModel
    private lateinit var SP : SharedPreferencess
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos)
        SP = SharedPreferencess(this)
        DB = BD(this)
        CursoViewModel = cursos_viewModel(this)
        CursoViewModel.render()

        val RecyclerView:RecyclerView = findViewById(R.id.recyclerviewCursos)
        CursoViewModel.Curso_user.observe(this){
            Cursoslist-> val adapter = cursos_adapter(
                Cursoslist,
                onSelectClicked = {
                                  Curso-> val intent = Intent(this, ver_curso::class.java)
                    intent.putExtra("IDcurso", Curso.IDcurso)
                    startActivity(intent)
                },
                onAddClicked = {
                        Curso-> añadirFavoritos(Curso.IDcurso)
                })
            RecyclerView.adapter = adapter
            RecyclerView.layoutManager = LinearLayoutManager(this)
        }

    }
    fun añadirFavoritos(IDcurso:Int){
        val ifexist = DB.cursoyaagregado(IDcurso, SP.iduser)
        if (ifexist){
            Toast.makeText(this, "ya tiene este curso agregado", Toast.LENGTH_SHORT).show()
        }else{
            val query = DB.añadirFavorito(IDcurso, SP.iduser)
            if (query != 1L){
                Toast.makeText(this, "Curso añadido a favoritos", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "fallo al agregar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}