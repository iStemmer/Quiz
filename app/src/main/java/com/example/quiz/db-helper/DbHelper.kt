package com.example.quiz.`db-helper`

import com.example.quiz.App
import com.example.quiz.model.Category
import com.example.quiz.model.Question
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

val DB_NAME = "sqlite.db"
val DB_VERSION = 1

object DbHelper : SQLiteAssetHelper(App.instance, DB_NAME, null, DB_VERSION) {

    /*
        getAllCategories
     */
    fun getAllCategories(): List<Category> {
        val db = this.writableDatabase
        val cursor = db.rawQuery("Select * from Category;", null)
        val categories = arrayListOf<Category>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val category = Category(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    ""
                )
                categories.add(category);
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return categories
    }

    fun getQuestionByCategory(category:Int) : List<Question> {
        val db = this.writableDatabase
        val cursor = db.rawQuery(
            String.format(
                "SELECT * from Question where CategoryId= %d ORDER BY RANDOM LIMIT 30",
                category
            ), null)
        val questions = arrayListOf<Question>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val question = Question(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("QuestionText")),
                    cursor.getString(cursor.getColumnIndex("QuestionImage")),
                    cursor.getString(cursor.getColumnIndex("AnswerA")),
                    cursor.getString(cursor.getColumnIndex("AnswerB")),
                    cursor.getString(cursor.getColumnIndex("AnswerC")),
                    cursor.getString(cursor.getColumnIndex("AnswerD")),
                    cursor.getString(cursor.getColumnIndex("CorrectAnswer")),
                    cursor.getInt(cursor.getColumnIndex("IsImageInteger")),
                    cursor.getInt(cursor.getColumnIndex("CategoryId"))
                )
                questions.add(question);
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return questions
    }



}
