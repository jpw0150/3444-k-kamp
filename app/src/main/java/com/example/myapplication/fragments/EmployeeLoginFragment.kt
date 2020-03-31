package com.example.myapplication.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout

import com.example.myapplication.R


class EmployeeLoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_employee_login, container, false)
        runGraidentAnimation(view)
        val username = view.findViewById<EditText>(R.id.employee_username)
        username.getText().toString()
        val password = view.findViewById<EditText>(R.id.employee_password)
        password.getText().toString()



        return view
    }

    private fun runGraidentAnimation(v: View) {
        val relativeLayout = v.findViewById<RelativeLayout>(R.id.fragment_employee_login)
        val animationDrawable = relativeLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}

