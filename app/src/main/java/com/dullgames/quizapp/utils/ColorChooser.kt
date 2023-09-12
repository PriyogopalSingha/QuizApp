package com.dullgames.quizapp.utils

object ColorChooser {
    var colors = arrayOf(
        "#f542ce",
        "#f7ee39",
        "#39f755",
        "#f50f1a",
        "#480ff5",
        "#0ff5e2",
        "#f5820f",
        "#f5f50f",
        "#1d5745",
        "#021d30"
    )
    var initialColor = 0
    fun getColor(): String{
        initialColor = (initialColor + 1) % colors.size
        return colors[initialColor]
    }
}