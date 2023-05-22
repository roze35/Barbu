package com.example.barbu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.barbu.Position
import com.example.barbu.adapter.HandAdapter
import com.example.barbu.R
import com.example.barbu.Referee
import com.example.barbu.adapter.TrickAdapter
import com.example.barbu.databinding.EastIgBinding
import com.example.barbu.databinding.NorthIgBinding
import com.example.barbu.databinding.SouthIgBinding
import com.example.barbu.databinding.WestIgBinding

class IGFragment(p: Position) : Fragment() {
    private lateinit var bindingSouth: SouthIgBinding
    private lateinit var bindingWest: WestIgBinding
    private lateinit var bindingNorth:NorthIgBinding
    private lateinit var bindingEast:EastIgBinding

    private var positionIG : Position=p

    // TODO faire deux xml différents en fonction de l'orientation : si horizontal trick horizontal
    //  si vercitale trick vertical

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //TODO :  factoriser le code ci-dessous
        when(positionIG) {
            Position.SOUTH -> {
                bindingSouth =
                    DataBindingUtil.inflate(inflater, R.layout.south_ig, container, false)
                val handAdapter = HandAdapter(Referee.southPlayer.hand)
                bindingSouth.rvHand.adapter = handAdapter
                val trickAdapter = TrickAdapter(Referee.trick.cards)
                bindingSouth.rvTrick.adapter = trickAdapter
                return (bindingSouth.root)
            }

            Position.WEST -> {
                bindingWest =
                    DataBindingUtil.inflate(inflater, R.layout.west_ig, container, false)
                val handAdapter = HandAdapter(Referee.westPlayer.hand)
                bindingWest.rvHand.adapter = handAdapter
                val trickAdapter = TrickAdapter(Referee.trick.cards)
                bindingWest.rvTrick.adapter = trickAdapter
                return (bindingSouth.root)
            }

            Position.NORTH -> {
                bindingNorth =
                    DataBindingUtil.inflate(inflater, R.layout.north_ig, container, false)
                val handAdapter = HandAdapter(Referee.westPlayer.hand)
                bindingNorth.rvHand.adapter = handAdapter
                val trickAdapter = TrickAdapter(Referee.trick.cards)
                bindingNorth.rvTrick.adapter = trickAdapter
                return (bindingNorth.root)
            }

            Position.EAST -> {
                bindingEast =
                    DataBindingUtil.inflate(inflater, R.layout.east_ig, container, false)
                val handAdapter = HandAdapter(Referee.eastPlayer.hand)
                bindingEast.rvHand.adapter = handAdapter
                val trickAdapter = TrickAdapter(Referee.trick.cards)
                bindingEast.rvTrick.adapter = trickAdapter
                return (bindingEast.root)
            }
        }


    }



}