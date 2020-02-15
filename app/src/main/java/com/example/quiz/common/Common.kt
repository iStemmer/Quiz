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


    var selectedCategory: Category? = null

    enum class ANSWER_TYPE{
        NO_ANSWER,
        WRONG_ANSWER,
        RIGHT_ANSWER
    }
}