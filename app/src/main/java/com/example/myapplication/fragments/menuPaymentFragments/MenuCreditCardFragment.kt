package com.example.myapplication.fragments.menuPaymentFragments

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.CustomerAccountActivity
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
        val total = (activity as MenuActivity).getOrderTotal()
        val newTotal = view.findViewById<TextView>(R.id.card_total)
        newTotal.setTextColor(Color.BLACK)
        newTotal.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        newTotal.text = "$$total"

        /* Initialize payButton and setOnClickListener() */
        val payButton = view.findViewById<Button>(R.id.button_pay)
        payButton.setOnClickListener{




            /*  if (newTotal < total) {
                  Toast.makeText((activity as MenuActivity).applicationContext, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
              }*/

            (activity as MenuActivity).replaceFragment(MenuAddRewardsFragment(), "")

            /* else {
                 if((activity as CustomerAccountActivity).currentCustomer != null){
                     (activity as MenuActivity).replaceFragment(MenuAddRewardsFragment(), "")
                 }
                 else{
                     (activity as MenuActivity).replaceFragment(MenuFinalGameFragment(), "")
                 }
             }*/
        }
        /* [in listener] validate that EditText amount is greater than or equal to original payment amount */
        // REFER TO fragments.menuEntreeFragments.MenuEntreeQuantityFragment or MenuEntreeNote() fragment to see how to read and save EditText

        /* Validate that correct credit card info was put in (literally bs this method by just checking write number of characters were put) */
        /* If its not valid { Display Toast}
           TODO: else { check that currentCustomer is not null ((activity as CustomerAccountActivity).currentCustomer)
               if there is customer account --> go to MenuAddRewardsFragment() }
               if no customer account --> go to MenuFinalGameFragment()

               */
        val nobutton = view.findViewById<Button>(R.id.button_notip)
        val fifteenbutton = view.findViewById<Button>(R.id.button_fifteentip)
        val twentybutton = view.findViewById<Button>(R.id.button_twentytip)
        val twentyfivebutton = view.findViewById<Button>(R.id.button_twentyfivetip)

        nobutton.setOnClickListener {
            val notip = (activity as MenuActivity).calculateTip(0.0)
            val shownotip = view.findViewById<TextView>(R.id.card_total)
            shownotip.setTextColor(Color.BLACK)
            shownotip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            shownotip.text="$$notip"
        }

        fifteenbutton.setOnClickListener {
            val fifteentip = (activity as MenuActivity).calculateTip(0.15)
            val showfifteentip = view.findViewById<TextView>(R.id.card_total)
            showfifteentip.setTextColor(Color.BLACK)
            showfifteentip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            showfifteentip.text="$$fifteentip"
        }

        twentybutton.setOnClickListener {
            val twentytip = (activity as MenuActivity).calculateTip(0.20)
            val showtwentytip = view.findViewById<TextView>(R.id.card_total)
            showtwentytip.setTextColor(Color.BLACK)
            showtwentytip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            showtwentytip.text="$$twentytip"
        }

        twentyfivebutton.setOnClickListener {
            val twentyfivetip = (activity as MenuActivity).calculateTip(0.25)
            val showtwentyfivetip = view.findViewById<TextView>(R.id.card_total)
            showtwentyfivetip.setTextColor(Color.BLACK)
            showtwentyfivetip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            showtwentyfivetip.text="$$twentyfivetip"

        }

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
