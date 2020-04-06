package com.example.myapplication.fragments.gameFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.fragments.MenuGameOptionsFragment

class GameTicTacFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_tic_tac, container, false)

        view.findViewById<Button>(R.id.ticTacQuit).setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuGameOptionsFragment(), "") }
        view.findViewById<Button>(R.id.ticTacStart).setOnClickListener{ (activity as MenuActivity).replaceFragment(GameTicTacPlayFragment(), "")}

        return view
    }

}
