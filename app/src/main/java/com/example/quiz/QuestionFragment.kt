package com.example.quiz


import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.quiz.`interface`.IAnswerSelect
import com.example.quiz.common.Common
import com.example.quiz.model.CurrentQuestion
import com.example.quiz.model.Question
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_question.*
import java.lang.Exception
import java.lang.StringBuilder

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment() : Fragment(), IAnswerSelect {

    lateinit var txt_question_text: TextView

    lateinit var ckb_A: CheckBox
    lateinit var ckb_B: CheckBox
    lateinit var ckb_C: CheckBox
    lateinit var ckb_D: CheckBox

    lateinit var layout_image: FrameLayout
    lateinit var progress_bar: ProgressBar

    var question: Question? = null
    var questionIndex = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_question, container, false)

        questionIndex = arguments!!.getInt("index", -1)

        layout_image = itemView.findViewById(R.id.layout_image) as FrameLayout
        question = Common.questionList[questionIndex]
        if (question != null) {
            progress_bar = itemView.findViewById(R.id.progress_bar) as ProgressBar
            if (question!!.isImageQuestion) {
                val imageView = itemView.findViewById<View>(R.id.image_question) as ImageView

                //get image from the internet
                Picasso.get().load(question!!.questionImage).into(imageView, object : Callback {
                    override fun onSuccess() {
                        progress_bar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        image_question.setImageResource(R.drawable.ic_error_outline_black_24dp)
                    }

                })
            } else {
                layout_image.visibility = View.GONE
                txt_question_text = itemView.findViewById(R.id.txt_question_text) as TextView
                txt_question_text.text = question!!.questionText
                ckb_A = itemView.findViewById(R.id.ckb_A) as CheckBox
                ckb_A.text = question!!.answerA
                ckb_B = itemView.findViewById(R.id.ckb_B) as CheckBox
                ckb_B.text = question!!.answerB

                ckb_C = itemView.findViewById(R.id.ckb_C) as CheckBox
                ckb_C.text = question!!.answerA

                ckb_D = itemView.findViewById(R.id.ckb_D) as CheckBox
                ckb_D.text = question!!.answerA

                //Event
                ckb_A.setOnCheckedChangeListener { compoundButton, isChecked ->
                    if (isChecked)
                        Common.selected_values.add(ckb_A.text.toString())
                    else {
                        Common.selected_values.remove(ckb_A.text.toString())
                    }
                }

                ckb_B.setOnCheckedChangeListener { compoundButton, isChecked ->
                    if (isChecked)
                        Common.selected_values.add(ckb_B.text.toString())
                    else {
                        Common.selected_values.remove(ckb_B.text.toString())
                    }
                }
                ckb_C.setOnCheckedChangeListener { compoundButton, isChecked ->
                    if (isChecked)
                        Common.selected_values.add(ckb_C.text.toString())
                    else {
                        Common.selected_values.remove(ckb_C.text.toString())
                    }
                }

                ckb_D.setOnCheckedChangeListener { compoundButton, isChecked ->
                    if (isChecked)
                        Common.selected_values.add(ckb_D.text.toString())
                    else {
                        Common.selected_values.remove(ckb_D.text.toString())
                    }
                }


            }
        }
        return itemView
    }

    override fun selectedAnswer(): CurrentQuestion {
        Common.selected_values.distinct()
        if (Common.answerSheetList[questionIndex].type == Common.ANSWER_TYPE.NO_ANSWER) {
            val currentQuestion = CurrentQuestion(questionIndex, Common.ANSWER_TYPE.NO_ANSWER)
            val result = StringBuilder()
            if (Common.selected_values.size > 1) {
                val arrayAnswer = Common.selected_values.toTypedArray()
                for (i in arrayAnswer.indices) {
                    if (i < arrayAnswer.size - 1)
                        result.append(arrayAnswer[i].substring(0, 1)).append(",")
                    else
                        result.append(arrayAnswer[i]).substring(0, 1)
                }
            } else if (Common.selected_values.size == 1) {
                val arrayAnswer = Common.selected_values.toTypedArray()
                result.append((arrayAnswer[0]).substring(0, 1))
            }
            if (question != null) {
                if (TextUtils.isEmpty(result)) {
                    if (result.toString() == question!!.correctAnswer) {
                        currentQuestion.type = Common.ANSWER_TYPE.RIGHT_ANSWER
                    } else {
                        currentQuestion.type = Common.ANSWER_TYPE.WRONG_ANSWER
                    }
                } else {
                    currentQuestion.type = Common.ANSWER_TYPE.NO_ANSWER
                }
            } else {
                Toast.makeText(activity, "Cannot get question", Toast.LENGTH_SHORT).show()
                currentQuestion.type = Common.ANSWER_TYPE.NO_ANSWER
            }
            Common.selected_values.clear()
            return currentQuestion
        } else {
            return Common.answerSheetList[questionIndex]
        }
    }

    override fun showCorrectAnswer() {
        val correctAnswers = question!!.correctAnswer.split(",".toRegex())
            .dropLastWhile { it.isEmpty() }
        for (answer in correctAnswers) {
            if (answer.equals("A")) {
                ckb_A.setTypeface(null, Typeface.BOLD)
                ckb_A.setTextColor(Color.RED)
            } else if (answer.equals("B")) {
                ckb_B.setTypeface(null, Typeface.BOLD)
                ckb_B.setTextColor(Color.RED)
            } else if (answer.equals("C")) {
                ckb_C.setTypeface(null, Typeface.BOLD)
                ckb_C.setTextColor(Color.RED)
            } else if (answer.equals("D")) {
                ckb_D.setTypeface(null, Typeface.BOLD)
                ckb_D.setTextColor(Color.RED)
            }
        }
    }

    override fun disableAnswer() {
        ckb_A.isEnabled = false
        ckb_B.isEnabled = false
        ckb_C.isEnabled = false
        ckb_D.isEnabled = false
    }

    override fun resetQuestion() {
        ckb_A.isEnabled = true
        ckb_B.isEnabled = true
        ckb_C.isEnabled = true
        ckb_D.isEnabled = true

        ckb_A.isChecked = false
        ckb_B.isChecked = false
        ckb_C.isChecked = false
        ckb_D.isChecked = false

        ckb_A.setTypeface(null, Typeface.NORMAL)
        ckb_A.setTextColor(Color.BLACK)

        ckb_B.setTypeface(null, Typeface.NORMAL)
        ckb_B.setTextColor(Color.BLACK)

        ckb_C.setTypeface(null, Typeface.NORMAL)
        ckb_C.setTextColor(Color.BLACK)

        ckb_D.setTypeface(null, Typeface.NORMAL)
        ckb_D.setTextColor(Color.BLACK)

        Common.selected_values.clear()


    }

}
