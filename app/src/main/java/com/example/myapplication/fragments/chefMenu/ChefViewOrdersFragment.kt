package com.example.myapplication.fragments.chefMenu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R

class ChefViewOrdersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chef_view_orders, container, false)

        runGraidentAnimation(view)


        return view
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ScrollView>(R.id.chef_view_orders)

        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}