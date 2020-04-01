package com.example.myapplication.fragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.Button

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity


class MenuPlaceOrderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_place_order, container, false)
        runGraidentAnimation(view)

        val yesButton = view.findViewById<Button>(R.id.button_yes)
        val noButton = view.findViewById<Button>(R.id.button_no)

        yesButton.setOnClickListener {
            Toast.makeText((activity as MenuActivity).applicationContext, "Order placed successfully!", Toast.LENGTH_LONG).show()
            (activity as MenuActivity).addOrder()
            (activity as MenuActivity).resetOrder()
            (activity as MenuActivity).replaceFragment(MainMenuFragment(), "")
        }

        noButton.setOnClickListener {
            (activity as MenuActivity).replaceFragment(MainMenuFragment(), "")
        }

        /* Intialialize and set up help and refill button actions */
        val helpButtonMain = view.findViewById<ImageButton>(R.id.button_help_image_order)
        val refillButtonMain = view.findViewById<ImageButton>(R.id.button_refill_image_order)
        helpButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }

        return view
    }


    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_place_order)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}
