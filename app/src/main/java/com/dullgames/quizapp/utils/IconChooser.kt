package com.dullgames.quizapp.utils

import com.dullgames.quizapp.R

object IconChooser {
    var icons = intArrayOf(
        R.drawable.icon1,
        R.drawable.icon2,
        R.drawable.icon3,
        R.drawable.icon4,
        R.drawable.icon5,
        R.drawable.icon6,
        R.drawable.icon7
    )
    var initialIcon = 0
    fun getColor(): Int{
        initialIcon = (initialIcon + 1) % icons.size
        return icons[initialIcon]
    }
}