package com.project.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class cursos_viewModel(context: Context):ViewModel() {
    private val _Cursos = MutableLiveData<List<Curso_user>>()
    private val DB: BD = BD(context)
    val Curso_user: LiveData<List<Curso_user>> = _Cursos

    fun render(){
        val cursolist = mutableListOf<Curso_user>()
        val query = DB.getCursos()
        if (query != null){
            while (query.moveToNext()){
                val Titulo = query.getString(query.getColumnIndex("nombre"))
                val IDcurso = query.getInt(query.getColumnIndex("idcurso"))
                val registro = Curso_user(Titulo, IDcurso)
                cursolist.add(registro)
            }
            _Cursos.value = cursolist
        }

    }

    fun rendermiscursos(IDuser:Int){
        val cursolist = mutableListOf<Curso_user>()
        val cursoCursors = DB.getCurso_user(IDuser)
        for (cursoCursor in cursoCursors) {
            cursoCursor.use { cursor ->
                while (cursor.moveToNext()) {
                    val Titulo = cursor.getString(cursor.getColumnIndex("nombre"))
                    val IDcurso = cursor.getInt(cursor.getColumnIndex("idcurso"))
                    val registro = Curso_user(Titulo, IDcurso)
                    cursolist.add(registro)
                }
            }
        }
        _Cursos.value = cursolist
    }
}