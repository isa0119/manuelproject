package com.project.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Pregunta(
    val pregunta: String,
    val idprueba: Int,
    val respuesta: String,
    val opcion1: String,
    val opcion2: String,
    val opcion3: String,
)

class PreguntasAdapter (private val preguntaslista: List<Pregunta>):RecyclerView.Adapter<PreguntasAdapter.PreguntaViewHolder>(){
    class PreguntaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val PreguntaCampo:TextView = itemView.findViewById(R.id.PreguntaField)
        val opcion1:CheckBox = itemView.findViewById(R.id.opcion1)
        val opcion2:CheckBox = itemView.findViewById(R.id.opcion2)
        val opcion3: CheckBox = itemView.findViewById(R.id.opcion3)
    }

    private var puntos = 0

    fun getPuntos(): Int {
        return puntos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pregunta_view, parent, false)
        return PreguntaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PreguntaViewHolder, position: Int) {
        val currentItem = preguntaslista[position]
        holder.PreguntaCampo.text = currentItem.pregunta
        holder.opcion1.text = currentItem.opcion1
        holder.opcion2.text = currentItem.opcion2
        holder.opcion3.text = currentItem.opcion3
        holder.opcion1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.opcion2.isChecked = false
                holder.opcion3.isChecked = false
                evaluarRespuesta(currentItem.respuesta, holder.opcion1)
            }
        }

        holder.opcion2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.opcion1.isChecked = false
                holder.opcion3.isChecked = false
                evaluarRespuesta(currentItem.respuesta, holder.opcion2)
            }
        }

        holder.opcion3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.opcion1.isChecked = false
                holder.opcion2.isChecked = false
                evaluarRespuesta(currentItem.respuesta, holder.opcion3)
            }
        }
    }
    private fun evaluarRespuesta(respuestaCorrecta: String, checkBox: CheckBox) {
        if (checkBox.text.toString() == respuestaCorrecta) {
            puntos++
        }
    }

    override fun getItemCount() = preguntaslista.size
}