package com.example.barbu.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.barbu.GraphicalPlayer
import com.example.barbu.R
import com.example.barbu.adapter.HandAdapter
import com.example.barbu.adapter.TrickAdapter
import com.example.barbu.databinding.SouthIgBinding


class IGFragment : Fragment(){
    private lateinit var bindingSouth: SouthIgBinding
    private val args:IGFragmentArgs by navArgs()
    private lateinit var graphicalPlayer:GraphicalPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("affichage","appel de onCreateView de IGFragment")
        bindingSouth =
            DataBindingUtil.inflate(inflater, R.layout.south_ig, container, false)
        bindingSouth.rvHand.adapter = HandAdapter(graphicalPlayer)
        bindingSouth.rvTrick.adapter = TrickAdapter()
        //Log.d("affichage","bindingSouth=${bindingSouth.rvTrick.adapter}")
        return bindingSouth.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("affichage","appel de onCreate de IGFragment")
        graphicalPlayer=args.joueur
        graphicalPlayer.igFragment=this
    }


    fun updateTrick(){
        //Log.d("affichage","nb Carte du trick ${Referee.trick.size()}")
        //Log.d("affichage","bindingSouth=${bindingSouth.rvTrick.adapter}")
        bindingSouth.rvTrick.adapter?.notifyDataSetChanged()

    }

    fun gameOver(southScore:Int,westScore:Int,northScore:Int,eastScore:Int){
        val action = IGFragmentDirections.actionIGFragmentToIGFragmentScore(southScore, westScore, northScore, eastScore)
        findNavController().navigate(action)
    }


}

