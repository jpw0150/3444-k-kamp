package com.example.myapplication.fragments.waiterMenu

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.CustomerAccountActivity
import com.example.myapplication.apipackage.DefaultResponse
import com.example.myapplication.apipackage.RetrofitClient
import com.example.myapplication.apipackage.ResponseTables
import com.example.myapplication.apipackage.Table

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/*TO DO:
*-create for-loop for alerts every time there is a customer who needs help
*-alerts will come up on waiter "table alerts" as a stack of alerts that need to be completed, then removed after completed
*/

class WaiterTableAlertFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_waiter_table_alert, container, false)
        runGraidentAnimation(view)

        /* Create empty table list */
        var tableList: List<Table>

        /* First get all the tables */
        RetrofitClient.instance.allTables().enqueue(object: Callback<ResponseTables> {
            override fun onFailure(call: Call<ResponseTables>, t: Throwable) {
                Toast.makeText(
                    activity as CustomerAccountActivity,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<ResponseTables>,
                response: Response<ResponseTables>
            ) {

            }


        })



        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.waiter_table_alert)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
