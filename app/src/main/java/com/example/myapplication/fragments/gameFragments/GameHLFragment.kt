package com.example.myapplication.fragments.gameFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.example.myapplication.R
import com.example.myapplication.activities.MenuActivity
import com.example.myapplication.fragments.MenuGameOptionsFragment

class GameHLFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_h_l, container, false)

        val playButton = view.findViewById<Button>(R.id.startHiLo)
        val exitButton = view.findViewById<Button>(R.id.hlRootExitButton)

        playButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(GameHiLoPlayFragment(),"") }
        exitButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(MenuGameOptionsFragment(),"") }

        return view
    }
}
