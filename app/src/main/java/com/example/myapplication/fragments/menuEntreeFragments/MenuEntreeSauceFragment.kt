package com.example.myapplication.fragments.menuEntreeFragments

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

/** This fragment class is Step 34in the ordering process in which the customer enters
 * the sauce wanted
 */
class MenuEntreeSauceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_entree_sauce, container, false)
        runGraidentAnimation(view)

        /* Initialize sauce option buttons */
        val ranchButton = view.findViewById<Button>(R.id.button_ranch)
        val bleuCheeseButton = view.findViewById<Button>(R.id.button_bleu_cheese)
        val honeyMustardButton = view.findViewById<Button>(R.id.button_honey_mustard)
        val noSauceButton = view.findViewById<Button>(R.id.button_no_sauce)

        val helpButtonSauce = view.findViewById<ImageButton>(R.id.button_help_image_sauce)
        val refillButtonSauce = view.findViewById<ImageButton>(R.id.button_refill_image_sauce)

        /* Store sauce option in order and proceed to nest fragment */

        ranchButton.setOnClickListener{
            (activity as MenuActivity).sauceType = getString(R.string.ranch)
            (activity as MenuActivity).entreeId+=100
            (activity as MenuActivity).replaceFragment(MenuEntreeSauceQuantityFragment(), "")
        }

        bleuCheeseButton.setOnClickListener {
            (activity as MenuActivity).sauceType = getString(R.string.bleu_cheese)
            (activity as MenuActivity).entreeId+=200
            (activity as MenuActivity).replaceFragment(MenuEntreeSauceQuantityFragment(), "")
        }

        honeyMustardButton.setOnClickListener{
            (activity as MenuActivity).sauceType = getString(R.string.honey_mustard)
            (activity as MenuActivity).entreeId+=300
            (activity as MenuActivity).replaceFragment(MenuEntreeSauceQuantityFragment(), "")
        }

        noSauceButton.setOnClickListener{
            (activity as MenuActivity).sauceType = getString(R.string.none)
            (activity as MenuActivity).entreeId+=400
            (activity as MenuActivity).replaceFragment(MenuEntreeNoteFragment(), "")
        }

        /* Listeners to address Help and Refill requests */
        helpButtonSauce.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonSauce.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }

        return view
    }


    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_entree_sauce)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
