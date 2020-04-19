package com.example.myapplication.fragments.managerMenu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R


class ManagerSurveyFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_manager_survey, container, false)
        runGraidentAnimation(view)

        /* Initialize buttons */
        val getSurveyButton = view.findViewById<Button>(R.id.button_get_surveys)
        val nextButton = view.findViewById<Button>(R.id.button_next_survey)
        val previousButton = view.findViewById<Button>(R.id.button_previous_survey)

        getSurveyButton.setOnClickListener {
            // TODO: Database call to return list of surveys
            // TODO display first index responses

            /*

            view.findViewById<TextView>(R.id.question_1_response).text = RESPONSE 1 FROM SURVEY[INDEX]
            view.findViewById<TextView>(R.id.question_2_response).text = RESPONSE 2 FROM SURVEY[INDEX]
            view.findViewById<TextView>(R.id.question_3_response).text = RESPONSE 3 FROM SURVEY[INDEX]

             */

        }
        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.manager_survey)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}
