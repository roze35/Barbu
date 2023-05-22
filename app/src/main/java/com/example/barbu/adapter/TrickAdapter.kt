package com.example.barbu.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.barbu.cardGame.Card
import com.example.barbu.R

class TrickAdapter(private val hand:MutableSet<Card>) : Adapter<TrickAdapter.MyViewHolder>() {

    var cardList:List<Card> = listOf()
        set(value) {
            field=value
            notifyDataSetChanged()
        }
    init {
        //Log.d("affichage","creation du HandAdapter")
        cardList=hand.toList()
        //Log.d("affichage","nbre de cartes : "+cardList.size)

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView:ImageView=itemView.findViewById(R.id.ivCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Log.d("affichage","appel a onCreateViewHolder de HandAdapter")
        val inflater= LayoutInflater.from(parent.context)
        val cardView=inflater.inflate(R.layout.card_view,parent,false)
        return MyViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        //Log.d("affichage","il y a "+hand.size+" cartes")
        return cardList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Log.d("affichage","traitement de la carte "+position)
        val card=cardList[position]
        val cardString=card.rank.toString().lowercase()+"_of_"+card.suit.toString().lowercase()
        //Log.d("affichage","nom de la carte "+cardString)
        val context = holder.itemView.context
        val imageCardId= context.resources.getIdentifier(cardString,"drawable",context.packageName)
        //Log.d("affichage","identifiant de la carte "+imageCardId)
        holder.cardView.setImageResource(imageCardId)
    }

}