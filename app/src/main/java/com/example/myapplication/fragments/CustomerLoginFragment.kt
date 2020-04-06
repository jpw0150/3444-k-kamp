package com.example.myapplication.fragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Button
import android.widget.EditText
import android.content.Intent

import com.example.myapplication.R
import com.example.myapplication.activities.CustomerAccountActivity
import com.example.myapplication.activities.MenuActivity


class CustomerLoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_customer_login, container, false)
        runGraidentAnimation(view)

        val loginButton = view.findViewById<Button>(R.id.customer_login_button)
        val database = (activity as CustomerAccountActivity).customer_db
        loginButton.setOnClickListener{
            /* Take user input */
            val username = view.findViewById<EditText>(R.id.customer_username).text.toString()
            val password = view.findViewById<EditText>(R.id.customer_password).text.toString()

            if ((activity as CustomerAccountActivity).verifyLogin(username, password)) {
                (activity as CustomerAccountActivity).currentCustomer = database.getCustomer(username)
                val intent = Intent (activity, MenuActivity::class.java)
                activity?.startActivity(intent)
            }
        }
        return view
    }

    private fun runGraidentAnimation(v: View) {
        val relativeLayout = v.findViewById<RelativeLayout>(R.id.fragment_customer_login)
        val animationDrawable = relativeLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }


}
