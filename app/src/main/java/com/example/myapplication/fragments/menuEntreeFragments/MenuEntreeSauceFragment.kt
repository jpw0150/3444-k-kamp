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
import com.example.myapplication.apipackage.ResponseTable
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            (activity as MenuActivity).entree_ID_String = "1" + (activity as MenuActivity).entree_ID_String
            (activity as MenuActivity).replaceFragment(MenuEntreeSauceQuantityFragment(), "")
        }

        bleuCheeseButton.setOnClickListener {
            (activity as MenuActivity).sauceType = getString(R.string.bleu_cheese)
            (activity as MenuActivity).entree_ID_String = "2" + (activity as MenuActivity).entree_ID_String
            (activity as MenuActivity).replaceFragment(MenuEntreeSauceQuantityFragment(), "")
        }

        honeyMustardButton.setOnClickListener{
            (activity as MenuActivity).sauceType = getString(R.string.honey_mustard)
            (activity as MenuActivity).entree_ID_String = "3" + (activity as MenuActivity).entree_ID_String
            (activity as MenuActivity).replaceFragment(MenuEntreeSauceQuantityFragment(), "")
        }

        noSauceButton.setOnClickListener{
            (activity as MenuActivity).sauceType = getString(R.string.none)
            (activity as MenuActivity).replaceFragment(MenuEntreeNoteFragment(), "")
        }

        /* Listeners to address Help and Refill requests */
        /* Send help notification to the waiter */
        helpButtonSauce.setOnClickListener{
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
        refillButtonSauce.setOnClickListener{
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
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.menu_entree_sauce)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
