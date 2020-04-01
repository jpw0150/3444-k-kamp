package com.example.myapplication.fragments.menuPaymentFragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.media.Image
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

class MenuCreditCardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_credit_card, container, false)
        runGraidentAnimation(view)

        /* Create a TextView in the XML and put a logo or something at top */

        /* Get and display the new order total giving a 15%, 20%, and 25% tip by implementing a method in the MenuActivity class */

        /* Create an EditText in the XML for the amount the user wants to pay */

        /* Create respective EditText fields in the XML for card number, expiration date, and security code */

        /* Create a "Pay" button */

        /* Initialize payButton and setOnClickListener() */

            /* [in listener] validate that EditText amount is greater than or equal to original payment amount */
            // REFER TO fragments.menuEntreeFragments.MenuEntreeQuantityFragment or MenuEntreeNote() fragment to see how to read and save EditText

            /* Validate that correct credit card info was put in (literally bs this method by just checking write number of characters were put) */
            /* If its not valid { Display Toast}
               else {
               check that currentCustomer is not null ((activity as CustomerAccountActivity).currentCustomer)
                   if there is customer account --> go to MenuAddRewardsFragment() }
                   if no customer account --> go to MenuFinalGameFragment()

                   */



        /* Intialialize and set up help and refill button actions */
        val helpButtonMain = view.findViewById<ImageButton>(R.id.button_help_image_payment_credit_card)
        val refillButtonMain = view.findViewById<ImageButton>(R.id.button_refill_image_payment_credit_card)
        helpButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }
        return view
    }


    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_payment_credit_card)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    //TODO: Validate credit card method
}
