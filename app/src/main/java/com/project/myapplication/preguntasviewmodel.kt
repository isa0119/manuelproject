package com.project.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class preguntasviewmodel(context: Context) : ViewModel() {
    private val _preguntas = MutableLiveData<List<Pregunta>>()
    private val DB: BD = BD(context)
    val pregunta: LiveData<List<Pregunta>> = _preguntas

    fun render(IDprueba:Int){
        val preguntalist = mutableListOf<Pregunta>()
        val query = DB.getPreguntas(IDprueba)
        if (query != null){
            while (query.moveToNext()){
                val pregunta = query.getString(query.getColumnIndex("pregunta"))
                val respuesta = query.getString(query.getColumnIndex("respuesta"))
                val opcion1 = query.getString(query.getColumnIndex("opcion1"))
                val opcion2 = query.getString(query.getColumnIndex("opcion2"))
                val opcion3 = query.getString(query.getColumnIndex("opcion3"))
                val registro = Pregunta(pregunta, IDprueba, respuesta, opcion1, opcion2, opcion3)
                preguntalist.add(registro)
            }
            _preguntas.value = preguntalist
    }
}}