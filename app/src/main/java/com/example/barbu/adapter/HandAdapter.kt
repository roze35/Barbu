package com.example.barbu.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.barbu.cardGame.Card
import com.example.barbu.R
import com.example.barbu.Referee
import com.example.barbu.utils.Utils


class HandAdapter(private val hand:MutableSet<Card>,private val interaction:Boolean,val onClickItem : (card : Card) -> Unit) : Adapter<HandAdapter.MyViewHolder>() {

    private var cardList:List<Card> = listOf()
        set(value) {
            field=value
            notifyDataSetChanged()
        }
    init {
        //Log.d("affichage","creation du handAdapter avec booleen = "+interaction)
        cardList=hand.toList()
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val cardView:ImageView=itemView.findViewById(R.id.ivCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val cardView=inflater.inflate(R.layout.card_view,parent,false)
        return MyViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Log.d("affichage","Position du joueur courant "+Referee.currentPosition)
        //if (!Referee.trick.isEmpty()) Log.d("affichage","premiere carte jouée "+Referee.trick.requiredSuit());
        val card=cardList[position]
        val cardString=card.rank.toString().lowercase()+"_of_"+card.suit.toString().lowercase()
        val context = holder.itemView.context
        val imageCardId= context.resources.getIdentifier(cardString,"drawable",context.packageName)
        holder.cardView.setImageResource(imageCardId)
        if (interaction){
            holder.cardView.setOnClickListener {
                val c = cardList.get(position)
                Log.d("affichage","Essaie de jouer la carte "+c.toString())
                if(Referee.Companion.playCard(c)){
                    Log.d("affichage","Youpee on peut jouer la carte")
                    onClickItem(c);

                }else{
                    Log.d("affichage","Argh... il est impossigle de jouer cette carte")
                }
            }
        } else{
            holder.cardView.setOnClickListener {
                Log.d(
                    "affichage",
                    "Ceci n'est pas un joueur humain, on va donc jouer une carte au hasard"
                )

                val c = Referee.playRandom()
                Log.d("affichage", "on souhater jouer " + c)
                onClickItem(c);
            }
        }
    }

}