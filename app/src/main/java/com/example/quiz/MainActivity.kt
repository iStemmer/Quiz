package com.example.quiz

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.`db-helper`.DbHelper
import com.example.quiz.adapter.CategoryAdapter
import com.example.quiz.common.SpaceDecoration

class MainActivity : AppCompatActivity() {

    lateinit var toolbar:Toolbar
    lateinit var recyclerCategory : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Quiz 2020"
        setSupportActionBar(toolbar)
        recyclerCategory = findViewById(R.id.recycler_category)
        recyclerCategory.setHasFixedSize(true)
        recyclerCategory.layoutManager = GridLayoutManager(this, 2)

        //get screen heightCategory
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height: Int = displayMetrics.heightPixels / 8 //Max size of item in Category
        var categoryAdapter = CategoryAdapter(this, DbHelper.getAllCategories())
        val spaceInPixel = 4
        recyclerCategory.addItemDecoration(SpaceDecoration(spaceInPixel))
        recyclerCategory.adapter = categoryAdapter


    }
}
