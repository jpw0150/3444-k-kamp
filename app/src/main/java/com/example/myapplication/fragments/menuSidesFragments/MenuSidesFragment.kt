package com.example.myapplication.fragments.menuSidesFragments

import android.graphics.drawable.AnimationDrawable
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity

/** This class shows the customer all the available sides, lets the customer view side descriptions, and select a side. */
class MenuSidesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_sides, container, false)
        runGraidentAnimation(view)

        /* Initialize icon info buttons */
        val friesInfoButton = view.findViewById<ImageButton>(R.id.button_fries_image)
        val cornInfoButton = view.findViewById<ImageButton>(R.id.button_corn_image)
        val veggieSticksInfoButton = view.findViewById<ImageButton>(R.id.button_veggie_sticks_image)

        /* Listeners to direct to respective description fragment */
        friesInfoButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuSideFriesFragment(), "") }
        cornInfoButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuSidesCornFragment(), "") }
        veggieSticksInfoButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuSideVeggieFragment(), "") }

        /* Initialize side option buttons */
        val friesButton = view.findViewById<Button>(R.id.button_fries)
        val cornButton = view.findViewById<Button>(R.id.button_corn)
        val veggieSticksButton = view.findViewById<Button>(R.id.button_veggie_sticks)


        /* Listeners to store side and direct to proceeding fragment */
        friesButton.setOnClickListener {
            (activity as MenuActivity).sideItem = getString(R.string.cajun_fries)
            (activity as MenuActivity).replaceFragment(MenuSidesQuantityFragment(), "")
        }

        cornButton.setOnClickListener {
            (activity as MenuActivity).sideItem = getString(R.string.fried_corn)
            (activity as MenuActivity).replaceFragment(MenuSidesQuantityFragment(), "")
        }

        veggieSticksButton.setOnClickListener {
            (activity as MenuActivity).sideItem = getString(R.string.veggie_sticks)
            (activity as MenuActivity).replaceFragment(MenuSidesQuantityFragment(), "")
        }


        /* Initialize help and refill buttons */
        val helpButtonSide = view.findViewById<ImageButton>(R.id.button_help_image_sides)
        val refillButtonSide = view.findViewById<ImageButton>(R.id.button_refill_image_sides)

        /* Listeners to address Help and Refill requests */
        helpButtonSide.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonSide.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_side)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}
