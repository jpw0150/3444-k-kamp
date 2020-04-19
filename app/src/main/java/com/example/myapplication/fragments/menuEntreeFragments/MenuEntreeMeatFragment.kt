package com.example.myapplication.fragments.menuEntreeFragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MenuEntreeMeatFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_entree, container, false)
        runGraidentAnimation(view)

        (activity as MenuActivity).entreeId
        /* Initialize meat type buttons */
        val boneless_button = view.findViewById<Button>(R.id.button_boneless)
        val bone_button = view.findViewById<Button>(R.id.button_bone)
        val tenders_button = view.findViewById<Button>(R.id.button_tenders)

        val helpButtonMeat = view.findViewById<ImageButton>(R.id.button_help_image_meat)
        val refillButtonMeat = view.findViewById<ImageButton>(R.id.button_refill_image_meat)


        /* Save selected meat type to order and proceed to step 2 */
        boneless_button.setOnClickListener{
            (activity as MenuActivity).meatType = "Boneless"
            (activity as MenuActivity).entreeId+=2
            (activity as MenuActivity).replaceFragment(MenuEntreeFlavorFragment(), "")


        }

        bone_button.setOnClickListener{
            (activity as MenuActivity).meatType = "Bone"
            (activity as MenuActivity).entreeId+=1
            (activity as MenuActivity).replaceFragment(MenuEntreeFlavorFragment(), "")

        }

        tenders_button.setOnClickListener{
            (activity as MenuActivity).meatType = "Tenders"
            (activity as MenuActivity).entreeId+=3
            (activity as MenuActivity).replaceFragment(MenuEntreeFlavorFragment(), "")

        }


        /* Listeners to address Help and Refill requests */
        helpButtonMeat.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonMeat.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }


        return view
    }


    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.me_1)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}