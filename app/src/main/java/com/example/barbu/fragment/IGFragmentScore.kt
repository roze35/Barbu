package com.example.barbu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.barbu.R
import com.example.barbu.Referee
import com.example.barbu.databinding.ScoreIgBinding

class IGFragmentScore : Fragment() {
    private lateinit var bindingScore: ScoreIgBinding
    private val args:IGFragmentScoreArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingScore= DataBindingUtil.inflate(inflater, R.layout.score_ig,container,false)
        var t:String=""+args.southScore
        bindingScore.tvSouthScore.text=t
        t=""+args.westScore
        bindingScore.tvWestScore.text=t
        t=""+args.northScore
        bindingScore.tvNorthScore.text=t
        t=""+args.eastScore
        bindingScore.tvEastScore.text=t

        bindingScore.btEnd.setOnClickListener {
            val action = IGFragmentScoreDirections.actionIGFragmentScoreToIGFragmentStart()
            findNavController().navigate(action)
        }
        bindingScore.btNewGame.setOnClickListener {
            Referee.dealCards()
            val action = IGFragmentScoreDirections.actionIGFragmentScoreToIGFragment(Referee.humanPlayer)
            findNavController().navigate(action)
        }
        return(bindingScore.root)
    }
}