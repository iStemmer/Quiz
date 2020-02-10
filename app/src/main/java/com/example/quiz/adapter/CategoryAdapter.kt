package com.example.quiz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.QuestionActivity
import com.example.quiz.R
import com.example.quiz.common.Common
import com.example.quiz.model.Category

class CategoryAdapter(var context: Context, var categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_category_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
        holder.txt_category_name.text = categories.get(position).name

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card_category = itemView.findViewById<CardView>(R.id.card_category)
        val txt_category_name = itemView.findViewById<TextView>(R.id.txt_category_name)

        fun bind() {
            itemView.setOnClickListener {
                Common.selectedCategory = categories.get(this.adapterPosition)
                val intent = Intent(context, QuestionActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}