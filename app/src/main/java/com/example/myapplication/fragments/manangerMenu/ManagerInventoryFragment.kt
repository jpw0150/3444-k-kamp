package com.example.myapplication.fragments.manangerMenu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.ManagerActivity
import com.example.myapplication.apipackage.Ingredient
import com.example.myapplication.apipackage.ResponseIngredients
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ManagerInventoryFragment : Fragment() {
    var index = 0
    var manIngs = List<Ingredient>(1) {
        Ingredient(
            0,
            "",
            0
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_employee_inventory, container, false)
        runGradientAnimation(view)

        view.findViewById<Button>(R.id.managerInventoryFetch).setOnClickListener{
            RetrofitClient.instance.allingredients().enqueue(object: Callback<ResponseIngredients> {
                override fun onFailure(call: Call<ResponseIngredients>, t: Throwable) {
                    view.findViewById<TextView>(R.id.managerInventoryPosition).apply{ text = "FETCH UNSUCCESSFUL" }
                }
                override fun onResponse(call: Call<ResponseIngredients>, response: Response<ResponseIngredients>) {
                    manIngs = response.body()!!.ingredients
                    view.findViewById<TextView>(R.id.managerInventoryPosition).apply{ text = (index+1).toString() + " OF " + manIngs.size.toString() }
                }
            })

            index = 0
            view.findViewById<TextView>(R.id.managerInventoryName).apply { text = manIngs[index].food}
            view.findViewById<EditText>(R.id.managerInventoryQuantity).apply { hint = manIngs[index].amount.toString() }
        }

        view.findViewById<Button>(R.id.managerInventoryLeave).setOnClickListener{ (activity as ManagerActivity).replaceFragment(ManagerMenuFragment(), "") }
        view.findViewById<Button>(R.id.managerInventoryBack).setOnClickListener{ ingsBack() }
        view.findViewById<Button>(R.id.managerInventoryUpdate).setOnClickListener{ ingsUpdate() }
        view.findViewById<Button>(R.id.managerInventoryNext).setOnClickListener{ ingsForward() }
        view.findViewById<Button>(R.id.managerInventoryAdd).setOnClickListener{ ingsAdd() }
        view.findViewById<Button>(R.id.managerInventoryDelete).setOnClickListener{ ingsDelete() }
        //view.findViewById<TextView>(R.id.managerInventoryPosition).apply{ text = (index+1).toString() + " OF " + manIngs.size.toString() }

        return view
    }

    private fun runGradientAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.managerInventory)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    fun ingsBack() {
        if (index == 0) { index = manIngs.size - 1 }
        else index -= 1

        view?.findViewById<TextView>(R.id.managerInventoryPosition)?.apply{ text = (index+1).toString() + " OF " + manIngs.size.toString()}
        view?.findViewById<TextView>(R.id.managerInventoryName)?.apply { text = manIngs[index].food }
        view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.apply { hint = manIngs[index].amount.toString()}
    }
    fun ingsForward() {
        if (index == manIngs.size - 1) index = 0
        else index += 1

        view?.findViewById<TextView>(R.id.managerInventoryPosition)?.apply{ text = (index+1).toString() + " OF " + manIngs.size.toString()}
        view?.findViewById<TextView>(R.id.managerInventoryName)?.apply { text = manIngs[index].food }
        view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.apply { hint = manIngs[index].amount.toString()}
    }
    fun ingsUpdate() {
        val ingId = manIngs[index].id
        var name = ""
        if (view?.findViewById<EditText>(R.id.managerInventoryName)?.text.isNullOrEmpty()) {name = manIngs[index].food}
        else {
            name = view?.findViewById<EditText>(R.id.managerInventoryName)?.text.toString()
            manIngs[index].food = view?.findViewById<EditText>(R.id.managerInventoryName)?.text.toString()
        }
        var quantity = 0
        if (view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.text.isNullOrEmpty()) {quantity = manIngs[index].amount}
        else {
            quantity = view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.text.toString().toInt()
            manIngs[index].amount = view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.text.toString().toInt()
        }

        //TODO: Update the currently targeted ingredient with the new data.
        RetrofitClient.instance.updateIngredient(ingId, name, quantity)

    }
    fun ingsAdd() {
        var name = ""
        if (view?.findViewById<EditText>(R.id.managerInventoryName)?.text.isNullOrEmpty()) {name = manIngs[index].food}
        else {
            name = view?.findViewById<EditText>(R.id.managerInventoryName)?.text.toString()
            manIngs[index].food = view?.findViewById<EditText>(R.id.managerInventoryName)?.text.toString()
        }
        var quantity = 0
        if (view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.text.isNullOrEmpty()) {quantity = manIngs[index].amount}
        else {
            quantity = view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.text.toString().toInt()
            manIngs[index].amount = view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.text.toString().toInt()
        }

        RetrofitClient.instance.createingredient(name, quantity)

        RetrofitClient.instance.allingredients().enqueue(object: Callback<ResponseIngredients> {
            override fun onFailure(call: Call<ResponseIngredients>, t: Throwable) {

            }
            override fun onResponse(call: Call<ResponseIngredients>, response: Response<ResponseIngredients>) {
                manIngs = response.body()!!.ingredients
            }
        })

        index = 0
        view?.findViewById<TextView>(R.id.managerInventoryName)?.apply { text = manIngs[index].food}
        view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.apply { hint = manIngs[index].amount.toString() }
    }
    fun ingsDelete() {
        RetrofitClient.instance.trashfood(manIngs[index].id)

        RetrofitClient.instance.allingredients().enqueue(object: Callback<ResponseIngredients> {
            override fun onFailure(call: Call<ResponseIngredients>, t: Throwable) {

            }
            override fun onResponse(call: Call<ResponseIngredients>, response: Response<ResponseIngredients>) {
                manIngs = response.body()!!.ingredients
            }
        })

        index = 0
        view?.findViewById<TextView>(R.id.managerInventoryName)?.apply { text = manIngs[index].food}
        view?.findViewById<EditText>(R.id.managerInventoryQuantity)?.apply { hint = manIngs[index].amount.toString() }
    }
}
