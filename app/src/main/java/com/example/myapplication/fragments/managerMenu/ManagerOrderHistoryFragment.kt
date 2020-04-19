package com.example.myapplication.fragments.managerMenu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.ChefActivity
import com.example.myapplication.activities.ManagerActivity
import com.example.myapplication.apipackage.Order
import com.example.myapplication.apipackage.ResponseOrders
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagerOrderHistoryFragment : Fragment() {

    var index = 0
    var indez = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_manager_order_history, container, false)
        runGraidentAnimation(view)

        val getManOrder = view.findViewById<Button>(R.id.manOrdHistGetAll)

        getManOrder.setOnClickListener {
            RetrofitClient.instance.getOrdersManager()
                .enqueue(object : Callback<ResponseOrders> {
                    override fun onFailure(call: Call<ResponseOrders>, t: Throwable) {
                        Toast.makeText(activity as ManagerActivity, "Failure", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onResponse(
                        call: Call<ResponseOrders>,
                        response: Response<ResponseOrders>
                    ) {

                        Toast.makeText(activity as ManagerActivity, "NICCEE", Toast.LENGTH_SHORT)
                            .show()


                        val output = response.body()?.orders

                        if (output != null) {
                            view.findViewById<EditText>(R.id.managerOrderHistID).hint =
                                output.get(index).id.toString()
                        }

                        if (output != null) {
                            view.findViewById<EditText>(R.id.managerOrderHistTable).hint =
                                output.get(index).tableNum.toString()
                        }
                        if (output != null) {
                            var outputString = ""
                            for (indez in 0..(output.get(index).entree.size - 1)) {

                                outputString += output.get(index).entree.get(indez).quantity.toString() + " " +
                                        output.get(index).entree.get(indez).meatType + " " +
                                        output.get(index).entree.get(indez).flavor + " " +
                                        output.get(index).entree.get(indez).sauceType + "\n"
                            }
                            indez = 0
                            view.findViewById<EditText>(R.id.managerOrderHistEntree).hint = outputString


                        }
                        if (output != null) {
                            var outputString = ""
                            for (indez in 0..(output.get(index).entree.size - 1)) {
                                outputString += output.get(index).side.get(indez).quantity.toString() + " " +
                                        output.get(index).side.get(indez).item + "\n"
                            }
                            indez = 0
                            view.findViewById<EditText>(R.id.managerOrderHistSide).hint = outputString

                        }
                        if (output != null) {
                            var outputString = ""
                            for (indez in 0..(output.get(index).entree.size - 1)) {
                                outputString += output.get(index).drink.get(indez).quantity.toString() + " " +
                                        output.get(index).drink.get(indez).item + "\n"
                            }
                            indez = 0
                            view.findViewById<EditText>(R.id.managerOrderHistDrink).hint = outputString

                        }
                        if (output != null) {
                            view.findViewById<EditText>(R.id.managerOrderHistNote).hint =
                                output.get(index).note
                        }
                        if (output != null) {
                            view.findViewById<EditText>(R.id.managerOrderHistTotal).hint =
                                output.get(index).orderTotal.toString()
                        }
                        if (output != null) {
                            view.findViewById<EditText>(R.id.managerOrderHistStatus).hint =
                                output.get(index).status.toString()
                        }

                        view.findViewById<Button>(R.id.manOrdHistPrev)
                            .setOnClickListener { orderPrevious(output) }
                        view.findViewById<Button>(R.id.manOrdHistNext)
                            .setOnClickListener { orderNext(output) }
                    }

                })
        }

        return view
    }


    fun orderPrevious(orderList: List<Order>?) {
        if (index == 0) {
            if (orderList != null) {
                index = orderList.size - 1
            }
        } else {
            index -= 1
        }




        view?.findViewById<EditText>(R.id.managerOrderHistID)
            ?.apply { hint = orderList?.get(index)?.id.toString() }
        view?.findViewById<EditText>(R.id.managerOrderHistTable)
            ?.apply { hint = orderList?.get(index)?.tableNum.toString() }

        var outputString = ""
        if (orderList != null) {
            for (indez in 0..(orderList.get(index).entree.size - 1)) {
                outputString +=
                    orderList?.get(index).entree.get(indez).quantity.toString() + " " +
                            orderList?.get(index).entree.get(indez).meatType + " " +
                            orderList?.get(index).entree.get(indez).flavor + " " +
                            orderList?.get(index).entree.get(indez).sauceType + "\n"
            }
        }
        indez = 0
        view?.findViewById<EditText>(R.id.managerOrderHistEntree)?.apply { hint = outputString }

        outputString = ""
        if (orderList != null) {
            for (indez in 0..(orderList.get(index).entree.size - 1)) {
                outputString += orderList.get(index).side.get(indez).quantity.toString() + " " +
                        orderList.get(index).side.get(indez).item + "\n"
            }
        }
        indez = 0
        view?.findViewById<EditText>(R.id.managerOrderHistSide)?.apply { hint = outputString }

        outputString = ""
        if (orderList != null) {
            for (indez in 0..(orderList.get(index).entree.size - 1)) {
                outputString += orderList.get(index).drink.get(indez).quantity.toString() + " " +
                        orderList.get(index).drink.get(indez).item + "\n"
            }
        }
        indez = 0
        view?.findViewById<EditText>(R.id.managerOrderHistDrink)?.apply { hint = outputString }
        view?.findViewById<EditText>(R.id.managerOrderHistNote)
            ?.apply { hint = orderList?.get(index)?.note }
        view?.findViewById<EditText>(R.id.managerOrderHistTotal)
            ?.apply { hint = orderList?.get(index)?.orderTotal.toString() }
        view?.findViewById<EditText>(R.id.managerOrderHistStatus)
            ?.apply { hint = orderList?.get(index)?.status.toString() }


    }

    fun orderNext(orderList: List<Order>?) {
        if (orderList != null) {
            if (index == orderList.size - 1) {
                index = 0
            }
        } else {
            index += 1
        }


        view?.findViewById<EditText>(R.id.managerOrderHistID)
            ?.apply { hint = orderList?.get(index)?.id.toString() }
        view?.findViewById<EditText>(R.id.managerOrderHistTable)
            ?.apply { hint = orderList?.get(index)?.tableNum.toString() }

        var outputString = ""
        if (orderList != null) {
            for (indez in 0..(orderList.get(index).entree.size - 1)) {
                outputString +=
                    orderList?.get(index).entree.get(indez).quantity.toString() + " " +
                            orderList?.get(index).entree.get(indez).meatType + " " +
                            orderList?.get(index).entree.get(indez).flavor + " " +
                            orderList?.get(index).entree.get(indez).sauceType + "\n"
            }
        }
        indez = 0
        view?.findViewById<EditText>(R.id.managerOrderHistEntree)?.apply { hint = outputString }

        outputString = ""
        if (orderList != null) {
            for (indez in 0..(orderList.get(index).entree.size - 1)) {
                outputString += orderList.get(index).side.get(indez).quantity.toString() + " " +
                        orderList.get(index).side.get(indez).item + "\n"
            }
        }
        indez = 0
        view?.findViewById<EditText>(R.id.managerOrderHistSide)?.apply { hint = outputString }

        outputString = ""
        if (orderList != null) {
            for (indez in 0..(orderList.get(index).entree.size - 1)) {
                outputString += orderList.get(index).drink.get(indez).quantity.toString() + " " +
                        orderList.get(index).drink.get(indez).item + "\n"
            }
        }
        indez = 0
        view?.findViewById<EditText>(R.id.managerOrderHistDrink)?.apply { hint = outputString }
        view?.findViewById<EditText>(R.id.managerOrderHistNote)
            ?.apply { hint = orderList?.get(index)?.note }
        view?.findViewById<EditText>(R.id.managerOrderHistTotal)
            ?.apply { hint = orderList?.get(index)?.orderTotal.toString() }
        view?.findViewById<EditText>(R.id.managerOrderHistStatus)
            ?.apply { hint = orderList?.get(index)?.status.toString() }

    }
        private fun runGraidentAnimation(v: View) {
            val constraintLayout = v.findViewById<ConstraintLayout>(R.id.manager_view_orders)

            val animationDrawable = constraintLayout?.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(2000)
            animationDrawable.setExitFadeDuration(4000)
            animationDrawable.start()
        }


}

