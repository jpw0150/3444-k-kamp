package com.example.myapplication.fragments.menuPaymentFragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.CustomerAccountActivity
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.data_structs.Customer
import kotlinx.android.synthetic.main.fragment_menu_add_rewards.*

class MenuAddRewardsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_add_rewards, container, false)
        runGraidentAnimation(view)
/*
        /*  TODO Get the current customer */
        val cur Customer = (activity as CustomerAccountActivity).currentCustomer

        /* Increment customer number of times visited by 1 (check Customer data struct for properties) */
        curCustomer.numTimesVisited += 1

        /* Display new reward and num times visited value and make it look nice */
        val rewardLayout = view.findViewById<TextView>(R.id.rewardTextview)
        if(curCustomer.numTimesVisited == 1)
            rewardLayout.text = "You just had 1st meal in KKWH\nThank you for your visiting"
        if(curCustomer.numTimesVisited == 2)
            rewardLayout.text = "You just had 2nd meal in KKWH\nThank you for your visiting"
        if(curCustomer.numTimesVisited == 3)
            rewardLayout.text = "You just had 3rd meal in KKWH!\nThank you for your visiting"
        else
            rewardLayout.text = "You just had ${curCustomer.numTimesVisited}th meal in KKWH!\nThank you for your visiting"
*/

        /* Create a next button, set onClickListener and direct it to MenuFinalGameFragment */
        val rewardNextButton = view.findViewById<Button>(R.id.addrewardButton)

        rewardNextButton.setOnClickListener {
            (activity as MenuActivity).replaceFragment(MenuFinalGameFragment(), "")
        }

        /* Intialialize and set up help and refill button actions */
        val helpButtonMain = view.findViewById<ImageButton>(R.id.button_help_image_payment_add_rewards)
        val refillButtonMain = view.findViewById<ImageButton>(R.id.button_refill_image_payment_add_rewards)
        helpButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()
        }

        refillButtonMain.setOnClickListener{
            Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()
        }


        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_payment_add_rewards)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    /* TODO: Method to add rewards based off how how much they spent (make some fake equation or something */
    // fun addRewards(orderTotal: Double, customer: Customer): Double{
    /*some cool math stuff numRewards = mx + b shiii
     customer.numCredits += rewards
     return numRewards
     */

    // }
}
