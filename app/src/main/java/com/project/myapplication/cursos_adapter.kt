package com.project.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class Curso_user(
    val Titulo:String,
    val IDcurso:Int
)
class cursos_adapter (private val CursosList: List<Curso_user>, private val onSelectClicked: (Curso_user) -> Unit,
                      private val onAddClicked: (Curso_user) -> Unit): RecyclerView.Adapter<cursos_adapter.cursos_user_viewholder>() {
    class cursos_user_viewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val Titulo: TextView = itemView.findViewById(R.id.Curso_titulo)
        val btnVer: Button = itemView.findViewById(R.id.Ver_Curso)
        val btnAñadir: Button = itemView.findViewById(R.id.Agregar_favoritos)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): cursos_user_viewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cursos_view, parent, false)
        return cursos_user_viewholder(itemView)
    }

    override fun onBindViewHolder(holder: cursos_user_viewholder, position: Int) {
        val currentItem = CursosList[position]
        holder.Titulo.text = currentItem.Titulo
        holder.btnVer.setOnClickListener { onSelectClicked (currentItem) }
        holder.btnAñadir.setOnClickListener { onAddClicked (currentItem) }
    }

    override fun getItemCount()= CursosList.size
}