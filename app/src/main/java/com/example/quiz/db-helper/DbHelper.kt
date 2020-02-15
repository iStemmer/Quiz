package com.example.quiz.`db-helper`

import com.example.quiz.App
import com.example.quiz.model.Category
import com.example.quiz.model.Question
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

val DB_NAME = "sqlite.db"
val DB_VERSION = 2

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

    fun getQuestionByCategory(category: Int): MutableList<Question> {
        val db = this.writableDatabase
        val cursor = db.rawQuery(
            "select * from Question where CategoryId=? order by id LIMIT 30;",
            arrayOf(category.toString())
        )
        val questions = arrayListOf<Question>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val question = Question(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("QuestionText")),
                    if (!cursor.isNull(cursor.getColumnIndex("QuestionImage"))) {
                        cursor.getString(cursor.getColumnIndex("QuestionImage"))
                    } else "",
                    cursor.getString(cursor.getColumnIndex("AnswerA")),
                    cursor.getString(cursor.getColumnIndex("AnswerB")),
                    cursor.getString(cursor.getColumnIndex("AnswerC")),
                    cursor.getString(cursor.getColumnIndex("AnswerD")),
                    cursor.getString(cursor.getColumnIndex("CorrectAnswer")),
                    cursor.getInt(cursor.getColumnIndex("IsImageInteger")) == 1,
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
