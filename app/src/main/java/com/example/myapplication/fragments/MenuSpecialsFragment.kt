package com.example.myapplication.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.fragments.menuDrinksFragments.MenuDrinksFragment
import com.example.myapplication.fragments.menuEntreeFragments.MenuEntreeMeatFragment
import com.example.myapplication.fragments.menuKids.MenuKidsMealsFragment


class MenuSpecialsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_specials, container, false)
        runGraidentAnimation(view)
        val currentDay = (activity as MenuActivity).getCurrentDay()
        view.findViewById<TextView>(R.id.today_special_day).text = "Today's Special: $currentDay"
        val redeemButton = view.findViewById<Button>(R.id.button_redeem)

        /* Sunday Special */
        if (currentDay == getString(R.string.sunday)) {
            view.findViewById<TextView>(R.id.text_specials_msg).text =
                getString(R.string.sunday_deal)
            redeemButton.visibility = View.VISIBLE
            redeemButton.setOnClickListener {
                Toast.makeText(
                    (activity as MenuActivity).applicationContext,
                    "Discount is already applied",
                    Toast.LENGTH_SHORT
                ).show()
                (activity as MenuActivity).replaceFragment(MenuDrinksFragment(), "")
            }
        }
        /* Monday Special */
        if (currentDay == getString(R.string.monday)) {
            view.findViewById<TextView>(R.id.text_specials_msg).text =
                getString(R.string.monday_deal)
            redeemButton.visibility = View.VISIBLE
            redeemButton.setOnClickListener {
                Toast.makeText(
                    (activity as MenuActivity).applicationContext,
                    "Discount is already applied",
                    Toast.LENGTH_SHORT
                ).show()
                (activity as MenuActivity).replaceFragment(MenuKidsMealsFragment(), "")
            }
        }

        /* Wednesday Special */
        if (currentDay == getString(R.string.wednesday)) {
            view.findViewById<TextView>(R.id.text_specials_msg).text =
                getString(R.string.thursday_deal)
            redeemButton.visibility = View.VISIBLE
            redeemButton.setOnClickListener {
                Toast.makeText(
                    (activity as MenuActivity).applicationContext,
                    "Discount is already applied",
                    Toast.LENGTH_SHORT
                ).show()
                (activity as MenuActivity).replaceFragment(MenuEntreeMeatFragment(), "")
            }
        }

         /* Thursday Special */
            if (currentDay == getString(R.string.thursday)) {
                view.findViewById<TextView>(R.id.text_specials_msg).text = getString(R.string.thursday_deal)
                redeemButton.visibility = View.VISIBLE
                redeemButton.setOnClickListener {
                    Toast.makeText((activity as MenuActivity).applicationContext,
                        "Order your wings first!", Toast.LENGTH_SHORT).show()
                    (activity as MenuActivity).replaceFragment(MenuEntreeMeatFragment(), "")
                }
            }

        /* Friday Special */
        if (currentDay == getString(R.string.friday)) {
            view.findViewById<TextView>(R.id.text_specials_msg).text = getString(R.string.friday_deal)
            redeemButton.visibility = View.VISIBLE
            redeemButton.setOnClickListener {
                Toast.makeText((activity as MenuActivity).applicationContext,
                    "A waiter will come serve your shortly", Toast.LENGTH_SHORT).show()
                (activity as MenuActivity).replaceFragment(MainMenuFragment(), "")
            }
        }

        return view
    }
    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_specials)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }



}
