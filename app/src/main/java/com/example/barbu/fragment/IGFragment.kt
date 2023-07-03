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
import com.example.barbu.Referee
import com.example.barbu.adapter.HandAdapter
import com.example.barbu.databinding.SouthIgBinding
import com.example.barbu.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IGFragment : Fragment() {
    private lateinit var binding: SouthIgBinding
    private val args: IGFragmentArgs by navArgs()
    private lateinit var graphicalPlayer: GraphicalPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("affichage", "appel de onCreateView de IGFragment")
        binding =
            DataBindingUtil.inflate(inflater, R.layout.south_ig, container, false)
        binding.rvHand.adapter = HandAdapter(graphicalPlayer)
        //Log.d("affichage","bindingSouth=${bindingSouth.rvTrick.adapter}")
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("affichage", "appel de onCreate de IGFragment")
        graphicalPlayer = args.joueur
        graphicalPlayer.igFragment = this
        CoroutineScope(Dispatchers.Default).launch{
                Referee.playIACards()
        }


    }

    fun trickOver(){
        CoroutineScope(Dispatchers.Default).launch{
            Referee.playIACards()
        }
    }
    fun gameOver(southScore: Int, westScore: Int, northScore: Int, eastScore: Int) {
        val action = IGFragmentDirections.actionIGFragmentToIGFragmentScore(
            southScore,
            westScore,
            northScore,
            eastScore
        )
        findNavController().navigate(action)
    }


    fun showCards() {
        if (Referee.trick.southCard != null) {
            binding.southCard.setImageResource(
                Utils.getRessourceId(
                    Referee.trick.southCard!!,
                    context
                )
            )
        } else binding.southCard.setImageResource(R.drawable.dos)

        if (Referee.trick.westCard != null) {
            binding.westCard.setImageResource(
                Utils.getRessourceId(
                    Referee.trick.westCard!!,
                    context
                )
            )
        } else {
            binding.westCard.setImageResource(R.drawable.dos)
        }

        if (Referee.trick.northCard != null) {
            binding.northCard.setImageResource(
                Utils.getRessourceId(
                    Referee.trick.northCard!!,
                    context
                )
            )
        } else {
            binding.northCard.setImageResource(R.drawable.dos)
        }

        if (Referee.trick.eastCard != null) {
            binding.eastCard.setImageResource(
                Utils.getRessourceId(
                    Referee.trick.eastCard!!,
                    context
                )
            )
        } else {
            binding.eastCard.setImageResource(R.drawable.dos)
        }
    }



}



