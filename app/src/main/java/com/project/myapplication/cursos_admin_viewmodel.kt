package com.project.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class cursos_admin_viewmodel(context: Context) : ViewModel(){
    private val _Cursos = MutableLiveData<List<Curso>>()
    private val DB: BD = BD(context)
    val Curso: LiveData<List<Curso>> = _Cursos

    fun render(){
        val cursolist = mutableListOf<Curso>()
        val query = DB.getCursos()
        if (query != null){
            while (query.moveToNext()){
                val Titulo = query.getString(query.getColumnIndex("nombre"))
                val IDcurso = query.getInt(query.getColumnIndex("idcurso"))
                val registro = Curso(Titulo, IDcurso)
                cursolist.add(registro)
            }
            _Cursos.value = cursolist
        }

    }
}