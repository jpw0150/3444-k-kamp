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

class GameBlackjackFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_blackjack, container, false)

        val playButton = view.findViewById<Button>(R.id.startBlackjack)
        val chaosButton = view.findViewById<Button>(R.id.startChaosjack)
        val exitButton = view.findViewById<Button>(R.id.bjRootExitButton)

        playButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(GameBlackjackPlayFragment(),"") }
        chaosButton.setOnClickListener{ (activity as MenuActivity).replaceFragment(GameChaosjackPlayFragment(),"") }
        exitButton.setOnClickListener{(activity as MenuActivity).replaceFragment(MenuGameOptionsFragment(),"") }

        return view
    }
}
