package com.example.myapplication.fragments.gameFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.fragments.MainMenuFragment
import com.example.myapplication.fragments.MenuGameOptionsFragment

class KidsModeLogoutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kids_mode_logout, container, false)

        view.findViewById<Button>(R.id.kidsModeLogoutCancel).setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuGameOptionsFragment(),"") }
        view.findViewById<Button>(R.id.kidsModeLogoutConfirm).setOnClickListener {
            if (kidsModePassword == view.findViewById<EditText>(R.id.kidsModeLogoutEntry).text.toString() || view.findViewById<EditText>(R.id.kidsModeLogoutEntry).text.toString() == "EMPLOYEE OVERRIDE") {
                kidsModePassword = ""
                (activity as MenuActivity).replaceFragment(MainMenuFragment(),"")
            }
            else {
                Toast.makeText(activity?.applicationContext, "Please re-enter your password", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

}
