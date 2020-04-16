package com.example.myapplication.fragments.menuEntreeFragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.apipackage.ResponseTable
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** This fragment class is Step 5 in the ordering process in which the customer enters
 * the number of sauce servings desired
 */
class MenuEntreeSauceQuantityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_entree_sauce_quantity, container, false)
        runGraidentAnimation(view)

        /*Initialize buttons */

        val nextButton = view.findViewById<Button>(R.id.button_next_sauce_quantity)
        val helpButtonQuantity = view.findViewById<ImageButton>(R.id.button_help_image_sauce_quantity)
        val refillButtonQuantity = view.findViewById<ImageButton>(R.id.button_refill_image_sauce_quantity)

        /* Save sauce quantity and proceed to next fragment */
        nextButton.setOnClickListener{
            val sauceQuantity = view.findViewById<EditText>(R.id.sauce_quantity).text.toString().toInt()
            /* Validate that quantity is at least 1 */
            if (sauceQuantity < 1) {
                Toast.makeText((activity as MenuActivity).applicationContext,
                    "Please enter a valid amount or select 'No Sauce", Toast.LENGTH_SHORT).show()
            }
            else {
                (activity as MenuActivity).sauceQuantity = view.findViewById<EditText>(R.id.sauce_quantity).text.toString().toInt()
                (activity as MenuActivity).replaceFragment(MenuEntreeNoteFragment(), "")
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
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_entree_sauce_quantity)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }


}
