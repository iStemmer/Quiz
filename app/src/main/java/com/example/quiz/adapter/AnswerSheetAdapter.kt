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
import com.example.quiz.model.CurrentQuestion
import com.example.quiz.model.Question

class AnswerSheetAdapter(var context: Context, var currentQuestions: List<CurrentQuestion>) :
    RecyclerView.Adapter<AnswerSheetAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_grid_sheet_answer_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return currentQuestions.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (currentQuestions.get(i))
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question_item = itemView.findViewById<CardView>(R.id.question_item)

        fun bind() {
            itemView.setOnClickListener {
                Common.selectedCategory = categories.get(this.adapterPosition)
                val intent = Intent(context, QuestionActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}