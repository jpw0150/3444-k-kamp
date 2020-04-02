package com.example.myapplication.fragments

import android.graphics.drawable.AnimationDrawable
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
import com.example.myapplication.fragments.gameFragments.KidsModeLoginFragment
import com.example.myapplication.fragments.menuDrinksFragments.MenuDrinksFragment
import com.example.myapplication.fragments.menuEntreeFragments.MenuEntreeMeatFragment
import com.example.myapplication.fragments.menuSidesFragments.MenuSidesFragment


class MainMenuFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_menu_container, container, false)
        runGraidentAnimation(view)

        /* Initialize button variables */
        val entreeButton = view.findViewById<Button>(R.id.button_entree)
        val sidesButton = view.findViewById<Button>(R.id.button_sides)
        val drinksButton = view.findViewById<Button>(R.id.button_drinks)
        val specialsButton = view.findViewById<Button>(R.id.button_specials)
        val gamesButton = view.findViewById<Button>(R.id.button_games)
        val kidsMenuButton = view.findViewById<Button>(R.id.button_kids_menu)


        val helpButtonMain = view.findViewById<ImageButton>(R.id.button_help_image_main_menu)
        val cartButton = view.findViewById<ImageButton>(R.id.button_cart_main_menu)
        val refillButtonMain = view.findViewById<ImageButton>(R.id.button_refill_image_main_menu)

        /* Set up button actions to go to respective page */

        // TODO: set up all these screens
        entreeButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuEntreeMeatFragment(),"") }
        sidesButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuSidesFragment(),"") }
        drinksButton.setOnClickListener{(activity as MenuActivity).replaceFragment(MenuDrinksFragment(),"") }
        specialsButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuSpecialsFragment(),"") }
        gamesButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(KidsModeLoginFragment(),"") }
        kidsMenuButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuKidsMealsFragment(),"")}


        helpButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }

        //TODO: Implement View Cart

        return view
    }


    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.main_menu)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }


}