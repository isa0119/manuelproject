package com.project.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class mis_cursos_adapter (private val CursosList: List<Curso_user>, private val onSelectClicked: (Curso_user) -> Unit,
                          private val onDeleteClicked: (Curso_user) -> Unit): RecyclerView.Adapter<mis_cursos_adapter.mis_cursos_viewholder>() {
    class mis_cursos_viewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val Titulo: TextView = itemView.findViewById(R.id.Curso_titulo)
        val btnVer: Button = itemView.findViewById(R.id.Ver_Curso)
        val btnEliminar: Button = itemView.findViewById(R.id.Eliminar_favoritos)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): mis_cursos_viewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mi_curso_view, parent, false)
        return mis_cursos_viewholder(itemView)
    }

    override fun onBindViewHolder(holder: mis_cursos_viewholder, position: Int) {
        val currentItem = CursosList[position]
        holder.Titulo.text = currentItem.Titulo
        holder.btnVer.setOnClickListener { onSelectClicked (currentItem) }
        holder.btnEliminar.setOnClickListener { onDeleteClicked (currentItem) }
    }

    override fun getItemCount()= CursosList.size
}