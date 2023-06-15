package com.example.barbu.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.barbu.Position
import com.example.barbu.R
import com.example.barbu.Referee
import com.example.barbu.databinding.StartIgBinding

class IGFragmentStart: Fragment() {
    private lateinit var bindingStart: StartIgBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingStart= DataBindingUtil.inflate(inflater,R.layout.start_ig,container,false)
        bindingStart.btStart.setOnClickListener {
            Referee.dealCards()
            val action = IGFragmentStartDirections.actionIGFragmentStartToIGFragment(Position.SOUTH)
            findNavController().navigate(action)
        }
        return(bindingStart.root)
    }
}