package com.example.quiz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.QuestionActivity
import com.example.quiz.R
import com.example.quiz.common.Common
import com.example.quiz.model.CurrentQuestion

class GridAnswerAdapter(internal var context: Context, internal var answerSheetList: List<CurrentQuestion>) :
    RecyclerView.Adapter<GridAnswerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_grid_answer_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return answerSheetList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (answerSheetList[position].type == Common.ANSWER_TYPE.RIGHT_ANSWER) {
            holder.question_item.setBackgroundResource(R.drawable.grid_item_right_answer)
        } else if (answerSheetList[position].type == Common.ANSWER_TYPE.WRONG_ANSWER) {
            holder.question_item.setBackgroundResource(R.drawable.grid_item_wrong_answer)
        } else {
            holder.question_item.setBackgroundResource(R.drawable.grid_item_no_answer)
        }
        holder.bind()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question_item = itemView.findViewById<View>(R.id.question_item)

        fun bind() {
            itemView.setOnClickListener {
                val intent = Intent(context, QuestionActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}