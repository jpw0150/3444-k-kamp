package com.example.myapplication.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.fragments.gameFragments.GameBlackjackFragment
import com.example.myapplication.fragments.gameFragments.GameHLFragment
import com.example.myapplication.fragments.gameFragments.GameWarFragment
import com.example.myapplication.fragments.gameFragments.KidsModeLogoutFragment

//import com.example.myapplication.fragments.gameFragments.GameWarFragment
//import com.example.myapplication.fragments.gameFragments.GameTicTacFragment
//import com.example.myapplication.fragments.gameFragments.GameFlyFragment


class MenuGameOptionsFragment : Fragment() {
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_game_options, container, false)

        val blackjackButton = view.findViewById<Button>(R.id.blackjackButton)
        val hiLoButton = view.findViewById<Button>(R.id.hlButton)
        val warButton = view.findViewById<Button>(R.id.warButton)
        val ticTacButton = view.findViewById<Button>(R.id.tictacButton)
        val flyButton = view.findViewById<Button>(R.id.flyButton)
        val exitButton = view.findViewById<Button>(R.id.gameExitButton)

        blackjackButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(GameBlackjackFragment(),"") }
        hiLoButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(GameHLFragment(),"") }
        warButton.setOnClickListener{(activity as MenuActivity).replaceFragment(GameWarFragment(),"") }
        //ticTacButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(GameTicTacFragment(),"") }
        //flyButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(GameFlyFragment(),"") }
        exitButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(KidsModeLogoutFragment(),"") }

        return view
    }

}
