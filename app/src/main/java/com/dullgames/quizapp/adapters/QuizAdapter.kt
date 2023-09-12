package com.dullgames.quizapp.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.dullgames.quizapp.R
import com.dullgames.quizapp.activities.QuestionActivity
import com.dullgames.quizapp.models.Quiz
import com.dullgames.quizapp.utils.ColorChooser
import com.dullgames.quizapp.utils.IconChooser

class QuizAdapter(val context: Context, val quizzes: List<Quiz>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var quiz_title = itemView.findViewById<TextView>(R.id.quiz_title)
        val quiz_icon = itemView.findViewById<ImageView>(R.id.quiz_icon)
        val card = itemView.findViewById<CardView>(R.id.card_container)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item_layout, parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.quiz_title.text = quizzes[position].title
        holder.card.setBackgroundColor(Color.parseColor(ColorChooser.getColor()))
        holder.quiz_icon.setImageResource(IconChooser.getColor())
        holder.itemView.setOnClickListener {
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)
        }

    }
}