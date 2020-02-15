package com.example.quiz.`interface`

import com.example.quiz.model.CurrentQuestion

interface IAnswerSelect {
    fun selectedAnswer(): CurrentQuestion
    fun showCorrectAnswer()
    fun disableAnswer()
    fun resetQuestion()
}