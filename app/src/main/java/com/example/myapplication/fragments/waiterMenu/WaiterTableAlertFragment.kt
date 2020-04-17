package com.example.myapplication.fragments.waiterMenu

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.ChefActivity
import com.example.myapplication.activities.CustomerAccountActivity
import com.example.myapplication.activities.WaiterActivity
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.apipackage.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import android.graphics.Typeface
import android.widget.Button
import com.example.myapplication.fragments.MenuPlaceOrderFragment
import kotlin.math.roundToInt

/*TODO:
*-create for-loop for alerts every time there is a customer who needs help
*-alerts will come up on waiter "table alerts" as a stack of alerts that need to be completed, then removed after completed
*/

class WaiterTableAlertFragment : Fragment() {

    /* Create empty table list */
    var serviceTableList = arrayListOf<Table>()
    lateinit var tableList: List<Table>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_waiter_table_alert, container, false)
        runGraidentAnimation(view)

        /* Initialize layout variable that will be dynamically programmed on */
        val layout = view.findViewById<LinearLayout>(R.id.waiter_notifications)


        /* First get all the tables */
        RetrofitClient.instance.allTables().enqueue(object: Callback<ResponseTables> {
            override fun onFailure(call: Call<ResponseTables>, t: Throwable) {
                Toast.makeText(
                    activity as CustomerAccountActivity,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(call: Call<ResponseTables>, response: Response<ResponseTables>) {

                /* Get all current status of all tables */
                tableList = response.body()!!.tables

                for (i in 0..tableList.size) {
                    val table = tableList[i]
                    if (table.needHelp || table.needRefill) {
                        serviceTableList.add(table)
                        val textView = TextView((activity as WaiterActivity))
                        val tableNum = table.number
                        val tableStatus = table.tableStatus

                        /* Display notification messege */
                        textView.text = createNotificationDisplay(tableNum, tableStatus)
                        setTextViewAttributes(textView)
                        layout.addView(textView)
                        addLineSeperator(layout)
                    }
                }
            }
        })

        /* Resolve notifications */
        if (serviceTableList.size > 0) {

            /* Create Resolve button */
            val resolveButton = Button(activity as WaiterActivity)
            resolveButton.text = getString(R.string.resolve_all)
            resolveButton.setBackgroundColor(Color.BLACK)
            resolveButton.setTextColor(Color.WHITE)
            layout.addView(resolveButton)

            /* When pressing resolve button reset all the serviced table values */
            resolveButton.setOnClickListener {
                for (i in 0..tableList.size){
                    val table = tableList[i]

                    /* Save updated table status to database */
                    RetrofitClient.instance.updateTable(table.number, getString(R.string.serviced),
                        needHelp = false,
                        needRefill = false
                        //TODO
                        //table.orderTotal
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
                                activity as WaiterActivity,
                                "Notification not properly resolved",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                }
            }
        }

        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.waiter_view_alerts)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    fun createNotificationDisplay(tableNum: Int, tableStatus: String): String {
        return "Table number $tableNum has send a request \n$tableStatus. \n"
    }

    /** HELPER METHOD: Method is used to display dynamic text properly */
    private fun setTextViewAttributes(textView: TextView) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(
            convertDpToPixel(16),
            convertDpToPixel(16),
            0, 0
        )

        textView.setTextColor(Color.BLACK)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        textView.layoutParams = params
    }


    /** Helper method for setTextAttributes() */
    private fun convertDpToPixel(dp: Int): Int {
        val metrics = Resources.getSystem().displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return px.roundToInt()
    }

    /** HELPER METHOD: Displays line */
    private fun addLineSeperator(layout:LinearLayout) {
        val lineLayout = LinearLayout(activity as WaiterActivity)
        lineLayout.setBackgroundColor(Color.BLACK)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            2
        )
        params.setMargins(0, convertDpToPixel(10), 0, convertDpToPixel(10))
        lineLayout.layoutParams = params
        layout.addView(lineLayout)
    }


}

