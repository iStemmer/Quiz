package com.example.quiz.common

import com.example.quiz.model.Category

class Common() {

    companion object {
        var selectedCategory: Category = Category()
        enum class ANSWER_TYPE{
            NO_ANSWER,
            WRONG_ANSWER,
            RIGHT_ANSWER
        }
    }
}