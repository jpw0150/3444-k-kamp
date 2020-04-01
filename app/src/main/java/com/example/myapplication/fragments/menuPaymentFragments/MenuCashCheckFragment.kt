package com.example.myapplication.fragments.menuPaymentFragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
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

class MenuCashCheckFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_cash_check, container, false)
        runGraidentAnimation(view)

        /* Create a TextView in the XML and put a logo or something at top */

        /* Get and display the new order total giving a 15%, 20%, and 25% tip by implementing a method in the MenuActivity class */

        /* Create a "Pay" button */

        /* Initialize payButton and setOnClickListener() */

            /* [in listener] display toast that waiter is coming to collect bill */
            /* go to MenuAddRewardsFragment() */


        /* Intialialize and set up help and refill button actions */
        val helpButtonMain = view.findViewById<ImageButton>(R.id.button_help_image_payment_cash_check)
        val refillButtonMain = view.findViewById<ImageButton>(R.id.button_refill_image_payment_cash_check)
        helpButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_payment_cash_check)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }


}
