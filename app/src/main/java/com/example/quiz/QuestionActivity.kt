package com.example.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.quiz.`db-helper`.DbHelper
import com.example.quiz.adapter.GridAnswerAdapter
import com.example.quiz.adapter.MyFragmentAdapter
import com.example.quiz.common.Common
import com.example.quiz.model.CurrentQuestion
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.content_question.*
import java.util.concurrent.TimeUnit

class QuestionActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var countDownTimer: CountDownTimer
    var timePlay = Common.TOTAL_TIME
    lateinit var adapter: GridAnswerAdapter

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_drawer_content_desc, R.string.close_drawer_content
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        genQuestion()
        //navView.setNavigationItemSelectedListener(this)
        if (Common.questionList.size > 0) {
            //show timer
            txt_timer.visibility = View.VISIBLE
            txt_question_right.visibility = View.VISIBLE

            countTimer()

            //gen item for
            genItems()
            grid_answer.setHasFixedSize(true)
            grid_answer.layoutManager =
                GridLayoutManager(this, if (Common.questionList.size > 1) Common.questionList.size / 2 else 1)
            adapter = GridAnswerAdapter(this, Common.answerSheetList)
            grid_answer.adapter = adapter

            //gen fragment list
            genFragmentList()
            val fragmentAdapter = MyFragmentAdapter(supportFragmentManager, this, Common.fragmentList)
            viewpager.offscreenPageLimit = Common.questionList.size
            viewpager.adapter = fragmentAdapter

            sliding_tabs.setupWithViewPager(viewpager)

            //Event
            viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                val SCROLLING_RIGHT = 0;
                val SCROLLING_LEFT = 1;
                val SCROLLING_UNDETERMINED = 2

                val currentScrollDirection = SCROLLING_UNDETERMINED

                private val isScrollDirectionUndetermined: Boolean
                    get() = currentScrollDirection == SCROLLING_UNDETERMINED

                private val isScrollDirectionRight: Boolean
                    get() = currentScrollDirection == SCROLLING_RIGHT

                private val isScrollDirectionLeft: Boolean
                    get() = currentScrollDirection == SCROLLING_LEFT

                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                }

            })
        }

    }

    private fun genFragmentList() {
        for (i in Common.questionList.indices) {
            val bundle = Bundle()
            bundle.putInt("index", i)
            val fragment = QuestionFragment()
            fragment.arguments = bundle

            Common.fragmentList.add(fragment)
        }
    }

    private fun genItems() {
        for (i in Common.questionList.indices)
            Common.answerSheetList.add(CurrentQuestion(i, Common.ANSWER_TYPE.NO_ANSWER))
    }

    private fun countTimer() {
        countDownTimer = object : CountDownTimer(Common.TOTAL_TIME.toLong(), 1000) {
            override fun onFinish() {
                finishGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                txt_timer.text = String.format(
                    "%02d:$02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                )
                timePlay -= 1000
            }

        }

    }

    private fun finishGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun genQuestion() {
        Common.questionList = DbHelper.getQuestionByCategory(Common.selectedCategory!!.id)
        if (Common.questionList.size == 0) {
            MaterialStyledDialog.Builder(this)
                .setTitle("Oopops")
                .setIcon(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
                .setDescription("We don't have any question in this ${Common.selectedCategory!!.image}")
                .setPositiveText("Ok")
                .onPositive { dialog, which ->
                    dialog.dismiss()
                    finish()
                }.show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.question, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun onSupportNavigateUp(): Boolean {
////        val navController = findNavController(R.id.nav_host_fragment)
////        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}
