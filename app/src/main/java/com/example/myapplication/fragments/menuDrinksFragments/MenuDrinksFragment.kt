package com.example.myapplication.fragments.menuDrinksFragments

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
import com.example.myapplication.apipackage.ResponseTable
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** This class is step 1 in ordering a drink
 * in which the customer selects a drink option and
 * can view drink descriptions
 */
class MenuDrinksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_drinks, container, false)
        runGraidentAnimation(view)

        /* Initialize icon info buttons */
        val lemonadeInfoButton = view.findViewById<ImageButton>(R.id.button_lemonade_image)
        val sodaInfoButton = view.findViewById<ImageButton>(R.id.button_soda_image)
        val teaInfoButton = view.findViewById<ImageButton>(R.id.button_tea_image)
        val waterInfoButton = view.findViewById<ImageButton>(R.id.button_water_image)

        /* Listeners to direct to respective description fragment */
        lemonadeInfoButton.setOnClickListener { (activity as MenuActivity).replaceFragment(MenuDrinksLemonadeFragment(), "") }
        sodaInfoButton.setOnClickListener { (activity as MenuActivity).replaceFragment(MenuDrinksSodaFragment(), "") }
        teaInfoButton.setOnClickListener { (activity as MenuActivity).replaceFragment(MenuDrinksTeaFragment(), "") }
        waterInfoButton.setOnClickListener { (activity as MenuActivity).replaceFragment(MenuDrinksWaterFragment(), "") }


        /* Initialize drink option buttons */
        val lemonadeButton = view.findViewById<Button>(R.id.button_lemonade)
        val sodaButton = view.findViewById<Button>(R.id.button_soda)
        val teaButton = view.findViewById<Button>(R.id.button_tea)
        val waterButton = view.findViewById<Button>(R.id.button_water)

        /* Listeners to store side and direct to proceeding fragment */
        lemonadeButton.setOnClickListener {
            (activity as MenuActivity).drinkItem = getString(R.string.lemonade)
            (activity as MenuActivity).replaceFragment(MenuDrinksQuantityFragment(), "")
        }

        sodaButton.setOnClickListener {
            (activity as MenuActivity).drinkItem = getString(R.string.soft_drink)
            (activity as MenuActivity).replaceFragment(MenuDrinksQuantityFragment(), "")
        }

        teaButton.setOnClickListener {
            (activity as MenuActivity).drinkItem = getString(R.string.sweet_tea)
            (activity as MenuActivity).replaceFragment(MenuDrinksQuantityFragment(), "")
        }

        waterButton.setOnClickListener {
            (activity as MenuActivity).drinkItem = getString(R.string.water)
            (activity as MenuActivity).replaceFragment(MenuDrinksQuantityFragment(), "")
        }

        /* Initialize help and refill buttons */
        val helpButtonDrink = view.findViewById<ImageButton>(R.id.button_help_image_drinks)
        val refillButtonDrink = view.findViewById<ImageButton>(R.id.button_refill_image_drinks)

        /* Listeners to address Help and Refill requests */
        /* Send help notification to the waiter */
        helpButtonDrink.setOnClickListener{
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
        refillButtonDrink.setOnClickListener{
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
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_drink)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
