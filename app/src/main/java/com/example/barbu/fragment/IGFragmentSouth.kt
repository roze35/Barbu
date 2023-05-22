package com.example.barbu.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbu.adapter.HandAdapter
import com.example.barbu.R
import com.example.barbu.Referee
import com.example.barbu.adapter.TrickAdapter
import com.example.barbu.databinding.SouthIgBinding

class IGFragmentSouth : Fragment() {
    private lateinit var bindingSouth: SouthIgBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Log.d("affichage","Debut de onCreateView de IGFragmentSouth")
        bindingSouth= DataBindingUtil.inflate(inflater,R.layout.south_ig,container,false)
        val handAdapter = HandAdapter(Referee.southPlayer.hand)
        //bindingSouth.rvListCardSouth.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        bindingSouth.rvListCardSouth.adapter=handAdapter

        // TODO faire deux xml différents en fonction de l'orientation : si horizontal trick horizontal
        //  si vercitale trick vertical

        val trickAdapter = TrickAdapter(Referee.trick.cards)
        bindingSouth.rvTrick.adapter=trickAdapter
        //Log.d("affichage","Fin de onCreateView de IGFragmentSouth")
        return(bindingSouth.root)

    }


}