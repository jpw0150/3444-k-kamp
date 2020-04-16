package com.example.myapplication.fragments.menuEntreeFragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.apipackage.ResponseTable
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** This fragment class is Step 3 in the ordering process in which the customer enters
 * the number of wings desired for a given flavor
 */
class MenuEntreeQuantityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_entree_quantity, container, false)
        runGraidentAnimation(view)

        /* Display price per wing depending on meat type */
        if ((activity as MenuActivity).getCurrentDay() == getString(R.string.wednesday)) {
            view.findViewById<TextView>(R.id.text_wing_price).text = getString(R.string.sixty_cent_wings)
        }


        val meatType = (activity as MenuActivity).meatType
        if(meatType == "Boneless"){ view.findViewById<TextView>(R.id.text_wing_price).text = getString(R.string.price_per_boneless_wing) }
        if(meatType == "Bone"){ view.findViewById<TextView>(R.id.text_wing_price).text = getString(R.string.price_per_bone_wing) }
        if(meatType == "Tenders"){ view.findViewById<TextView>(R.id.text_wing_price).text = getString(R.string.price_per_tender) }


        /* Initialize button to go to next fragment and save data */
        val nextButton = view.findViewById<Button>(R.id.button_next)
        val helpButtonQuantity = view.findViewById<ImageButton>(R.id.button_help_image_quantity)
        val refillButtonQuantity = view.findViewById<ImageButton>(R.id.button_refill_image_quantity)

        /* Save quantity and proceed to next fragment */
        nextButton.setOnClickListener{

            /* Check that a quantity of MIN_NUM_WINGS or more was entered */
            val wing_quantity = view.findViewById<EditText>(R.id.wing_quantity).text.toString().toInt()
            if (wing_quantity < (activity as MenuActivity).MIN_NUM_WINGS){
                Toast.makeText((activity as MenuActivity).applicationContext, "Please enter a valid amount", Toast.LENGTH_SHORT).show()

                /* Proceed if a quantity of more than MIN_NUM_WINGS was entered */
            } else {
                (activity as MenuActivity).numWings =
                    view.findViewById<EditText>(R.id.wing_quantity).text.toString().toInt()
                (activity as MenuActivity).replaceFragment(MenuEntreeSauceFragment(), "")
            }
        }

        /* Listeners to address Help and Refill requests */
        /* Send help notification to the waiter */
        helpButtonQuantity.setOnClickListener{
            //Toast.makeText((activity as MenuActivity).applicationContext, "A waiter will help you shortly", Toast.LENGTH_LONG).show()

            /* Save table status to the database  */
            RetrofitClient.instance.updateTable((activity as MenuActivity).table.number, "Needs Help",
                needHelp = true,
                needRefill = false
            ).enqueue(object: Callback<ResponseTable> {
                override fun onFailure(call: Call<ResponseTable>, t: Throwable) {
                    Toast.makeText(
                        activity as MenuActivity,
                        t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                override fun onResponse(call: Call<ResponseTable>, response: Response<ResponseTable>) {
                    Toast.makeText(
                        activity as MenuActivity,
                        "A waiter will help you shortly",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }


        /* Send refill notification to the waiter */
        refillButtonQuantity.setOnClickListener{
            //Toast.makeText((activity as MenuActivity).applicationContext, "A waiter refill your drink shortly", Toast.LENGTH_LONG).show()

            /* Save table status to database */
            RetrofitClient.instance.updateTable((activity as MenuActivity).table.number, "Needs Refill",
                needHelp = false,
                needRefill = true
            ).enqueue(object: Callback<ResponseTable> {
                override fun onFailure(call: Call<ResponseTable>, t: Throwable) {
                    Toast.makeText(
                        activity as MenuActivity,
                        t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                override fun onResponse(call: Call<ResponseTable>, response: Response<ResponseTable>) {
                    Toast.makeText(
                        activity as MenuActivity,
                        "A waiter will refill your drink shortly",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }

        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_entree_quantity)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
