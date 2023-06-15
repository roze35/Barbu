package com.example.barbu.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.barbu.GraphicalPlayer
import com.example.barbu.ObserverTrick
import com.example.barbu.Player
import com.example.barbu.Position
import com.example.barbu.R
import com.example.barbu.Referee
import com.example.barbu.Referee.Companion.trick
import com.example.barbu.adapter.HandAdapter
import com.example.barbu.cardGame.Card
import com.example.barbu.databinding.EastIgBinding
import com.example.barbu.databinding.NorthIgBinding
import com.example.barbu.databinding.SouthIgBinding
import com.example.barbu.databinding.WestIgBinding

class IGFragment() : Fragment(),ObserverTrick {
    private val args : IGFragmentArgs by navArgs()
    private lateinit var position: Position
    private lateinit var bindingSouth: SouthIgBinding
    private lateinit var bindingWest: WestIgBinding
    private lateinit var bindingNorth:NorthIgBinding
    private lateinit var bindingEast:EastIgBinding
    private lateinit var currentBinding :ViewDataBinding


    // TODO faire deux xml différents en fonction de l'orientation : si horizontal trick horizontal
    //  si vercitale trick vertical

    // XXX qui met a jour position que contient position de referee, ce n'est pas a l'IG de changer
    fun updateIG(){
        var newPos=Referee.currentPosition
        val action=IGFragmentDirections.actionIGFragmentSelf(newPos)
        currentBinding.root.findNavController().navigate(action)
    }


    fun buildSouthIG(inflater: LayoutInflater,container:ViewGroup?) {
        //Log.d("affichage","creatoin de l'ig south")
        bindingSouth =
            DataBindingUtil.inflate(inflater, R.layout.south_ig, container, false)
        val handAdapter = HandAdapter(Referee.southPlayer.hand,Referee.southPlayer is GraphicalPlayer,{updateIG()})
        bindingSouth.rvHand.adapter = handAdapter
        val trickAdapter = HandAdapter(Referee.trick.cards,false,{})
        bindingSouth.rvTrick.adapter = trickAdapter
        currentBinding=bindingSouth
    }

    fun buildWestIG(inflater: LayoutInflater,container:ViewGroup?){
        bindingWest =
            DataBindingUtil.inflate(inflater, R.layout.west_ig, container, false)
        val handAdapter = HandAdapter(Referee.westPlayer.hand,Referee.westPlayer is GraphicalPlayer,{updateIG()})
        bindingWest.rvHand.adapter = handAdapter
        val trickAdapter = HandAdapter(Referee.trick.cards,false,{})
        bindingWest.rvTrick.adapter = trickAdapter
        currentBinding=bindingWest
    }

    fun buildNorthIG(inflater: LayoutInflater,container:ViewGroup?){
        bindingNorth =
            DataBindingUtil.inflate(inflater, R.layout.north_ig, container, false)
        val handAdapter = HandAdapter(Referee.northPlayer.hand,Referee.northPlayer is GraphicalPlayer,{updateIG()})
        bindingNorth.rvHand.adapter = handAdapter
        val trickAdapter = HandAdapter(Referee.trick.cards,false,{})
        bindingNorth.rvTrick.adapter = trickAdapter
        currentBinding=bindingNorth
    }

    fun buildEastIG(inflater: LayoutInflater,container:ViewGroup?){
        bindingEast =
            DataBindingUtil.inflate(inflater, R.layout.east_ig, container, false)
        val handAdapter = HandAdapter(Referee.eastPlayer.hand,Referee.eastPlayer is GraphicalPlayer,{updateIG()})
        bindingEast.rvHand.adapter = handAdapter
        val trickAdapter = HandAdapter(Referee.trick.cards,false,{})
        bindingEast.rvTrick.adapter = trickAdapter
        currentBinding=bindingEast
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("trace","appel a IGFragment onCreateView pour la position "+position)
        when(position) {
            Position.SOUTH -> {buildSouthIG(inflater,container); }
            Position.WEST -> { buildWestIG(inflater,container); }
            Position.NORTH -> {buildNorthIG(inflater,container); }
            Position.EAST -> { buildEastIG(inflater,container); }
        }
        Log.d("pbl_IG","ajout d'un observer au trick, position = "+position)
        return currentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position=args.position
        Log.d("trace","1 FOIS Ajout de IGFragment aux obervers de trick "+position)
        Referee.trick.addObserver(this)

    }

    override fun onDestroyView() {
        trick.removeObserver(this)
        super.onDestroyView()
    }

    override fun followingPlayer() {
        position=Referee.currentPosition
        currentBinding.notifyChange()

    }

    override  fun newTrick(){
        updateIG()
    }

    override  fun endTrick(winPlayer: Player,trick:MutableSet<Card>){
        Log.d("sleep","debut de trickIsOver "+position)

        if (position==Referee.currentPosition) {
            //val action=IGFragmentDirections.actionIGFragmentSelf(position)
            //currentBinding.root.findNavController().navigate(action)
            try {
                Log.d("sleep", "lancement de sleep dans IGFrament")
                Thread.sleep(500) // Attendez 2 secondes (2000 millisecondes)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            updateIG()
        }
    }


}