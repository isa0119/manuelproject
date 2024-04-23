package com.project.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Curso(
    val Titulo:String,
    val IDcurso:Int
)

class Cursos_admin_adapter(private val CursosList: List<Curso>, private val onEditClicked: (Curso) -> Unit,
                           private val onDeleteClicked: (Curso) -> Unit): RecyclerView.Adapter<Cursos_admin_adapter.cursosviewholder>(){
    class cursosviewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val Titulo:TextView = itemView.findViewById(R.id.Curso_titulo)
        val btnEditar:Button = itemView.findViewById(R.id.Editar_Curso)
        val btnEliminar:Button = itemView.findViewById(R.id.Eliminar)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cursosviewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cursos_admin_view, parent, false)
        return cursosviewholder(itemView)
    }

    override fun getItemCount() = CursosList.size

    override fun onBindViewHolder(holder: cursosviewholder, position: Int) {
        val currentItem = CursosList[position]
        holder.Titulo.text = currentItem.Titulo
        holder.btnEditar.setOnClickListener { onEditClicked (currentItem) }
        holder.btnEliminar.setOnClickListener { onDeleteClicked (currentItem) }
    }

}