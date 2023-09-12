package com.dullgames.quizapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dullgames.quizapp.R
import com.dullgames.quizapp.models.Question

class QuestionAdapter(val context: Context, val question: Question):Adapter<QuestionAdapter.QuizViewHolder>() {
    private var optionsList: List<String> = listOf(question.option1, question.option2, question.option3, question.option4)

    inner class QuizViewHolder(itemView: View): ViewHolder(itemView){
        var option = itemView.findViewById<TextView>(R.id.option_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.single_question_item,parent, false)
        return QuizViewHolder(v)
    }

    override fun getItemCount(): Int {
        return optionsList.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.option.text = optionsList[position]
        holder.itemView.setOnClickListener {
            question.userAnswer = optionsList[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == optionsList[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }
}