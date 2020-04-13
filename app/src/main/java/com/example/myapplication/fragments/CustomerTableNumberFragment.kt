package com.example.myapplication.fragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.Button
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.activities.CustomerAccountActivity

import com.example.myapplication.R
import com.example.myapplication.activities.ChefActivity
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.apipackage.ResponseTables
import com.example.myapplication.apipackage.RetrofitClient
import kotlinx.android.synthetic.main.fragment_customer_table_number.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.myapplication.apipackage.ResponseTable

class CustomerTableNumberFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_customer_table_number, container, false)
        runGraidentAnimation(view)

        val nextButtonTable = view.findViewById<Button>(R.id.button_next_table_num)

        nextButtonTable.setOnClickListener {
            val tableNumber = view.findViewById<EditText>(R.id.table_number).text.toString()
            val tableNum = tableNumber.toInt()
            (activity as MenuActivity).table.number = tableNumber.toInt()


            /* Save table status to database */
            RetrofitClient.instance.updateTable(tableNum, "Ordering",
                needHelp = false,
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
                        "Assigning you your waiter",
                        Toast.LENGTH_LONG
                    ).show()
                        }
                    })

            (activity as MenuActivity).replaceFragment(MainMenuFragment(), "")
        }




        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.customer_table_number)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}
