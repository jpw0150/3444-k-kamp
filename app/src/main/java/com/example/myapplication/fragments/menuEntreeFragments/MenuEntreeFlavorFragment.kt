package com.example.myapplication.fragments.menuEntreeFragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.fragments.menuEntreeFragments.menuEntreeFlavorFragments.*


class MenuEntreeFlavorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_entree_flavor, container, false)
        runGraidentAnimation(view)

        /* Initialize icons that will lead to additional information about the flavor */
        val bbq_info_button = view.findViewById<ImageButton>(R.id.button_bbq_image)
        val lemon_info_button = view.findViewById<ImageButton>(R.id.button_lemon_image)
        val cajun_info_button = view.findViewById<ImageButton>(R.id.button_cajun_image)
        val garlic_parm_info_button = view.findViewById<ImageButton>(R.id.button_garlic_parm_image)
        val buffalo_info_button = view.findViewById<ImageButton>(R.id.button_buffalo_image)
        val hawaiian_info_button = view.findViewById<ImageButton>(R.id.button_hawaiian_image)

        val helpButtonFlavors = view.findViewById<ImageButton>(R.id.button_help_image_flavors)
        val refillButtonFlavors = view.findViewById<ImageButton>(R.id.button_refill_image_flavors)


        /* Direct user to flavor description fragments */
        /* TODO: for improvement implement a more aesethic photo gallery of each dish */
        bbq_info_button.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuEntreeFlavorBBQ(), "") }
        lemon_info_button.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuEntreeFlavorLemonPepper(), "") }
        cajun_info_button.setOnClickListener { (activity as MenuActivity).replaceFragment(MenuEntreeFlavorCajun(),"") }
        garlic_parm_info_button.setOnClickListener { (activity as MenuActivity).replaceFragment(MenuEntreeFlavorGarlicParm(),"") }
        buffalo_info_button.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuEntreeFlavorBuffalo(), "") }
        hawaiian_info_button.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuEntreeFlavorHawaiian(), "") }

        /* Initialize flavor type buttons */
        val bbq_button = view.findViewById<Button>(R.id.button_bbq)
        val lemon_button = view.findViewById<Button>(R.id.button_lemon)
        val cajun_button = view.findViewById<Button>(R.id.button_cajun)
        val garlic_parm_button = view.findViewById<Button>(R.id.button_garlic_parm)
        val buffalo_button = view.findViewById<Button>(R.id.button_buffalo)
        val hawaiian_button = view.findViewById<Button>(R.id.button_hawaiian)

        /* Save selected flavor type to order and proceed to step 3 */
        bbq_button.setOnClickListener{
            (activity as MenuActivity).flavorType = "Barbecue"
            (activity as MenuActivity).entreeId+=10
            (activity as MenuActivity).replaceFragment(MenuEntreeQuantityFragment(), "")
        }
        lemon_button.setOnClickListener{
            (activity as MenuActivity).flavorType = "Lemon Pepper"
            (activity as MenuActivity).entreeId+=60
            (activity as MenuActivity).replaceFragment(MenuEntreeQuantityFragment(), "")
        }
        cajun_button.setOnClickListener {
            (activity as MenuActivity).flavorType = "Cajun"
            (activity as MenuActivity).entreeId+=30
            (activity as MenuActivity).replaceFragment(MenuEntreeQuantityFragment(), "")
        }
        garlic_parm_button.setOnClickListener {
            (activity as MenuActivity).flavorType = "Garlic Parmesan"
            (activity as MenuActivity).entreeId+=40
            (activity as MenuActivity).replaceFragment(MenuEntreeQuantityFragment(), "")
        }
        buffalo_button.setOnClickListener{
            (activity as MenuActivity).flavorType = "Buffalo"
            (activity as MenuActivity).entreeId+=20
            (activity as MenuActivity).replaceFragment(MenuEntreeQuantityFragment(), "")
        }
        hawaiian_button.setOnClickListener{
            (activity as MenuActivity).flavorType = "Hawaiian"
            (activity as MenuActivity).entreeId+=50
            (activity as MenuActivity).replaceFragment(MenuEntreeQuantityFragment(), "")
        }

        /* Listeners to address Help and Refill requests */
        helpButtonFlavors.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonFlavors.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_entree_flavor)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
