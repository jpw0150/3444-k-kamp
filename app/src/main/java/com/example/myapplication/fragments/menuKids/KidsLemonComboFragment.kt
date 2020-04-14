package com.example.myapplication.fragments.menuKids

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity


class KidsLemonComboFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kids_lemon_combo, container, false)
        runGraidentAnimation(view)

        val helpButtonNote = view.findViewById<ImageButton>(R.id.button_help_image_kids_lemon)
        val refillButtonNote = view.findViewById<ImageButton>(R.id.button_refill_image_kids_lemon)

        /* Listeners to address Help and Refill requests */
        helpButtonNote.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext,
                "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonNote.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext,
                "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }

        return view
    }
    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_kids_lemon)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }


}
