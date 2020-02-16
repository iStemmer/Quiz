package com.example.quiz.common

import com.example.quiz.QuestionFragment
import com.example.quiz.model.Category
import com.example.quiz.model.CurrentQuestion
import com.example.quiz.model.Question

object Common {

    val TOTAL_TIME = 20*60*1000
    var answerSheetList : MutableList<CurrentQuestion> = ArrayList()
    var questionList : MutableList<Question> = ArrayList()
    var fragmentList : MutableList<QuestionFragment> = ArrayList()

    var selected_values: MutableList<String> = ArrayList()

    var timer = 0
    var right_answer_count = 0
    var wrong_answer_count = 0
    var no_answer_count = 0
    var data_question = StringBuilder()

    var selectedCategory: Category? = null

    enum class ANSWER_TYPE{
        NO_ANSWER,
        WRONG_ANSWER,
        RIGHT_ANSWER
    }
}