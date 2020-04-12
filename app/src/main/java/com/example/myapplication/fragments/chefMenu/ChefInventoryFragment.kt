package com.example.myapplication.fragments.chefMenu

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
import com.example.myapplication.activities.ChefActivity
import com.example.myapplication.apipackage.Api
import com.example.myapplication.apipackage.Ingredient
import com.example.myapplication.apipackage.ResponseIngredients
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChefInventoryFragment : Fragment() {
    var index = 0
    var chefIngs = List<Ingredient>(1) {Ingredient(0, "", 0)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chef_inventory, container, false)
        runGradientAnimation(view)

        view.findViewById<Button>(R.id.chefInventoryFetch).setOnClickListener{
            RetrofitClient.instance.allingredients().enqueue(object: Callback<ResponseIngredients> {
                override fun onFailure(call: Call<ResponseIngredients>, t: Throwable) {
                    view.findViewById<TextView>(R.id.chefInventoryPosition).apply{ text = "FETCH UNSUCCESSFUL" }
                }
                override fun onResponse(call: Call<ResponseIngredients>, response: Response<ResponseIngredients>) {
                    chefIngs = response.body()!!.Ingredients
                    view.findViewById<TextView>(R.id.chefInventoryPosition).apply{ text = (index+1).toString() + " OF " + chefIngs.size.toString()}
                }
            })

            index = 0
            view.findViewById<TextView>(R.id.chefInventoryName).apply { text = chefIngs[index].food}
            view.findViewById<EditText>(R.id.chefInventoryQuantity).apply { hint = chefIngs[index].amount.toString() }
        }

        view.findViewById<Button>(R.id.chefInventoryLeave).setOnClickListener{ (activity as ChefActivity).replaceFragment(ChefMenuFragment(), "") }
        view.findViewById<Button>(R.id.chefInventoryBack).setOnClickListener{ ingsBack() }
        view.findViewById<Button>(R.id.chefInventoryUpdate).setOnClickListener{ ingsUpdate() }
        view.findViewById<Button>(R.id.chefInventoryNext).setOnClickListener{ ingsForward() }
        view.findViewById<TextView>(R.id.chefInventoryPosition).apply{ text = (index+1).toString() + " OF " + chefIngs.size.toString()}

        return view
    }

    private fun runGradientAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.chef_inventory)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    fun ingsBack() {
        if (index == 0) { index = chefIngs.size - 1 }
        else index -= 1

        view?.findViewById<TextView>(R.id.chefInventoryPosition)?.apply{ text = (index+1).toString() + " OF " + chefIngs.size.toString()}
        view?.findViewById<TextView>(R.id.chefInventoryName)?.apply { text = chefIngs[index].food }
        view?.findViewById<EditText>(R.id.chefInventoryQuantity)?.apply { hint = chefIngs[index].amount.toString()}
    }
    fun ingsForward() {
        if (index == chefIngs.size - 1) index = 0
        else index += 1

        view?.findViewById<TextView>(R.id.chefInventoryPosition)?.apply{ text = (index+1).toString() + " OF " + chefIngs.size.toString()}
        view?.findViewById<TextView>(R.id.chefInventoryName)?.apply { text = chefIngs[index].food }
        view?.findViewById<EditText>(R.id.chefInventoryQuantity)?.apply { hint = chefIngs[index].amount.toString()}
    }
    fun ingsUpdate() {
        val ingId = chefIngs[index].id
        val name = chefIngs[index].food
        var quantity = 0
        if (view?.findViewById<EditText>(R.id.chefInventoryQuantity)?.text.isNullOrEmpty()) {quantity = chefIngs[index].amount}
        else {
            quantity = view?.findViewById<EditText>(R.id.chefInventoryQuantity)?.text.toString().toInt()
            chefIngs[index].amount = view?.findViewById<EditText>(R.id.chefInventoryQuantity)?.text.toString().toInt()
        }

        //TODO: Update the currently targeted ingredient with the new data.
        RetrofitClient.instance.updateIngredient(ingId, name, quantity)

    }
}
