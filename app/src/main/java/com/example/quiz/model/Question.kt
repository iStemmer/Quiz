package com.example.quiz.model

data class Question(
    val id: Int,
    val questionText: String,
    val questionImage: String,
    val answerA: String,
    val answerB: String,
    val answerC: String,
    val answerD: String,
    val correctAnswer: String,
    val isImageQuestion: Boolean,
    val categoryId: Int
)