package com.example.myapplication.fragments.chefMenu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.activities.ChefActivity
import com.example.myapplication.activities.ManagerActivity
import com.example.myapplication.apipackage.ResponseTable
import com.example.myapplication.apipackage.RetrofitClient
import com.example.myapplication.fragments.managerMenu.ManagerMenuFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChefViewScheduleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chef_view_schedule, container, false)
        runGraidentAnimation(view)

        /* Initialize Buttons */
        val informationButton = view.findViewById<Button>(R.id.button_get_info_chef)
        val updateHoursButton = view.findViewById<Button>(R.id.button_update_hours_chef)
        val chef_id = view.findViewById<EditText>(R.id.id_chef)

        informationButton.setOnClickListener {
            //TODO: Send get request
            Toast.makeText((activity as ChefActivity).applicationContext,
                "Unable to retrieve information at this time", Toast.LENGTH_SHORT).show()
        }

       updateHoursButton.setOnClickListener {
           // TODO: Update chef hours in database
           Toast.makeText((activity as ChefActivity).applicationContext,
               "Unable to update hours at this time", Toast.LENGTH_SHORT).show()
       }

        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.chef_view_schedule)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}
