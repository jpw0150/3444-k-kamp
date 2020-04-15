package com.example.myapplication.fragments.waiterMenu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.WaiterActivity

class WaiterViewScheduleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_waiter_view_schedule, container, false)
        runGraidentAnimation(view)

        /* Initialize Buttons */
        val informationButton = view.findViewById<Button>(R.id.button_get_info_waiter)
        val updateHoursButton = view.findViewById<Button>(R.id.button_update_hours_waiter)
        val waiter_id = view.findViewById<EditText>(R.id.id_waiter)

        informationButton.setOnClickListener {
            //TODO: Send get request
            Toast.makeText((activity as WaiterActivity).applicationContext,
                "Unable to retrieve information at this time", Toast.LENGTH_SHORT).show()
        }

        updateHoursButton.setOnClickListener {
            // TODO: Update chef hours in database
            Toast.makeText((activity as WaiterActivity).applicationContext,
                "Unable to update hours at this time", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.waiter_view_schedule)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}
