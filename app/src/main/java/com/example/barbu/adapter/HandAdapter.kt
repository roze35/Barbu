package com.example.barbu.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.barbu.player.GraphicalPlayer
import com.example.barbu.utils.Position
import com.example.barbu.R
import com.example.barbu.RefereeHuman
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HandAdapter(player: GraphicalPlayer) : Adapter<HandAdapter.MyViewHolder>() {
    private val p: GraphicalPlayer =player


    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val cardView:ImageView=itemView.findViewById(R.id.ivCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val cardView=inflater.inflate(R.layout.card_view,parent,false)
        return MyViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return p.hand.size
    }

    @SuppressLint("NotifyDataSetChanged", "DiscouragedApi")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Log.d("affichage","Position du joueur courant "+Referee.currentPosition)
        //if (!Referee.trick.isEmpty()) Log.d("affichage","premiere carte jouée "+Referee.trick.requiredSuit());
        val card = p.hand.toList()[position]
        val cardString =
            card.rank.toString().lowercase() + "_of_" + card.suit.toString().lowercase()
        val context = holder.itemView.context
        val imageCardId =
            context.resources.getIdentifier(cardString, "drawable", context.packageName)
        holder.cardView.setImageResource(imageCardId)
        holder.cardView.setOnClickListener {
            if (RefereeHuman.currentPlayer().position== Position.SOUTH) {
                val c = p.hand.toList()[position]
                //Log.d("affichage", "Essaie de jouer la carte " + c.toString())
                if (RefereeHuman.playCard(c)) {
                    Log.d("affichage", "Carte jouee $c par ${p.name}")
                    notifyDataSetChanged()
                    CoroutineScope(Dispatchers.Main).launch{
                        p.igFragment.showCards()
                    }

                    CoroutineScope(Dispatchers.Default).launch{
                        RefereeHuman.justWait()
                        RefereeHuman.nextPlayer()
                        RefereeHuman.playIACards()
                    }

                } else {
                    Log.d("affichage", "Argh... il est impossigle de jouer cette carte")
                }
            } else {
                Log.d("affichage","ce n'est pas au joueur sud de jouer mais au joueur "+RefereeHuman.currentPlayer().position)
            }
        }
    }


}